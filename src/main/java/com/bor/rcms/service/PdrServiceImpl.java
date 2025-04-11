package com.bor.rcms.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.CertificatOfficerRepo;
import com.bor.rcms.repository.DocumentPDRRepository;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.FileRequeistionRepo;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.ReqiestionResponnse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
@Transactional
public class PdrServiceImpl implements PdrService {
	@Autowired
	private DocumentPDRRepository documentRepository;
	private final String FILE_STORAGE_PATH = "C:/Users/Admin/file";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileRequeistionRepo fileRequeistionRepo;
	
	@Autowired
	private CertificatOfficerRepo certificatOfficerRepo;

	@Override
	public String submitRequisition(FileRequeistion requisition, MultipartFile[] files, String username,
			String documentTypes) {
		UserEntity user = userRepository.findById(Long.valueOf(username))
				.orElseThrow(() -> new RuntimeException("User not found"));
//  		userRepository.findByUserName(username)
//      .orElseThrow(() -> new RuntimeException("User not found"));
            requisition.setDistrictName(user.getDistrict());
		// Generate Unique Objection ID
		String objectionId = generateNextToken(requisition);
		// UUID.randomUUID().toString().replace("-", "").substring(0, 10);
//  objection.setTokenNo(objectionId);
		requisition.setUserId(user);
		requisition.setRequeistionId(objectionId);
		requisition.setStatus("pending");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = LocalDateTime.now().format(formatter);
		requisition.setCurrentDate(formattedDateTime);

		// Save Objection to the database
		ObjectMapper objectMapper = new ObjectMapper();
		requisition.setRole(user.getRole());

		FileRequeistion savedObjection = fileRequeistionRepo.save(requisition);
		List<Map<String, String>> documentInfo = null;
		if (documentTypes != null && !documentTypes.isEmpty()) {
			try {
				documentInfo = objectMapper.readValue(documentTypes, new TypeReference<List<Map<String, String>>>() {
				});
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Check if files are present (optional)
		if (files != null && files.length > 0) {
			List<DocumentEntityPdr> savedDocuments = new ArrayList<>();

			// Iterate over files and save them
			for (int i = 0; i < files.length; i++) { // Use i to match files with document types
				MultipartFile file = files[i];

				if (file.getSize() > 10 * 1024 * 1024) { // 10MB size limit
					return ("File size exceeds the maximum allowed size of 10MB.");
				}

				DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
				String currentMinute = LocalDateTime.now().format(fileNameFormatter);

				String fileName = currentMinute + "_" + file.getOriginalFilename();
				String filePath = FILE_STORAGE_PATH + fileName;

				// Save the file to disk
				File destinationFile = new File(filePath);
				try {
					file.transferTo(destinationFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Create DocumentEntity object and save its metadata
				DocumentEntityPdr document = new DocumentEntityPdr();
				document.setDocumentName(file.getOriginalFilename());
				// document.set(savedObjection.getRequeistionId());
				document.setFilePath(filePath);
				document.setFileType(file.getContentType());
				document.setFileSize(file.getSize());
				document.setFileRequeistion(savedObjection); // Associate document with the objection
				document.setUploadedDate(new Date());
				// Set the documentType from documentInfo if available
				if (documentInfo != null && i < documentInfo.size()) {
					Map<String, String> docInfo = documentInfo.get(i);
					document.setDocumentType(docInfo.get("documentType"));
				}
				savedDocuments.add(document);
			}

			// Save all documents to the database
			documentRepository.saveAll(savedDocuments);

			// Update the ObjectionEntity with the saved documents
			savedObjection.setDocuments(savedDocuments);
		}

		// Return the objectionId after the objection is successfully saved
		return objectionId;
	}

	public String generateNextToken(FileRequeistion objection) {
		try {
			int initial = 1; // Starting from 0001

			// Get current year in two digits (e.g., 25 for 2025)
			String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100)
					: Integer.toString(LocalDate.now().getYear() % 100);

			// Fetch last inserted NewObjection by districtName
			Optional<FileRequeistion> lastInsertedUser = fileRequeistionRepo
					.findTopByDistrictNameOrderByCurrentDateDesc(objection.getDistrictName());

			if (lastInsertedUser.isPresent()) {
				String caseId2 = lastInsertedUser.get().getRequeistionId();

				String[] parts = caseId2.split("-"); // Splitting by '-'
				int caseNumber = Integer.parseInt(parts[parts.length - 1]);
				int finalValue = caseNumber + 1;
				String formattedValue = String.format("%04d", finalValue); // Ensure the number is 4 digits

				// Set the token number with the incremented value
				objection.setRequeistionId(
						"REQ-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
			} else {
				// If no previous entries exist, start with 0001
				String formattedValue = String.format("%04d", initial);
				objection.setRequeistionId(
						"REQ-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
			}

			return objection.getRequeistionId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Handle exception properly
	}

	@Override
	public List<FileRequeistion> findpending(String district) {
        return fileRequeistionRepo.findAllPending(district);

	}

	@Override
	public Object findbyId(String obId) {
		// TODO Auto-generated method stub
        return fileRequeistionRepo.findAllByRequeistionId(obId);
	}

	@Override
	public String upadateStatus(OfficerStatusVo statusvo) {
		try {
			// Find the NewObjection entity
			Optional<FileRequeistion> objection1 = fileRequeistionRepo.findByRequeistionId(statusvo.getCaseId());
			FileRequeistion objection=objection1.get();
			
			CertificatOfficer admission = new CertificatOfficer();

			if (statusvo.getStatus().equals("Admit")) {
				admission.setAdmissionDate(statusvo.getAdmissionDate());
				admission.setAdmissionTime(statusvo.getAdmisionTime());
				admission.setAffidavitDate(statusvo.getAffedefitDate());
				admission.setOfficerName(statusvo.getOfficerName());
				
				String caseID=generateNextTokenadmision(objection);
				admission.setCertOfficerId(caseID);
				admission.setAdmisionCase(caseID);
				objection.setStatus("Admit");
				//objection.setObjectionId(objection.getObjectionId());
			//	objection.setAdmission(admission);
				admission.setFileRequeistion(objection);
				admission.setUserId(objection.getUserId());
				admission.setHearingDate(statusvo.getAdmissionDate());
				admission.setStatus("Admit");
				//admission.setNewObjection(objection);
				admission.setReequeistionId(objection.getRequeistionId());
				
			//	Mis mis = new Mis();

				if (statusvo.getUsertype().equals("CERTIFICATE_OFFICER")) {
//					mis.setSpecialOfficer("SpecialOfficer");
//					mis.setSpecialRemarks(statusvo.getStatus());
//					mis.setObjId(objection.getTokenNo());
//					mis.setNewObjection(objection);
				//	objection.setAdmission(admission);
					//objection.setMis(mis);
					CertificatOfficer savedObjection =new CertificatOfficer();
					try {
					
				//	System.out.println("dd===>" + objection);

					 savedObjection = certificatOfficerRepo.save(admission);
					 if(savedObjection!=null)
					 {
						 return savedObjection.getCertOfficerId();
					 }
					 return null;
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
			}
			
		}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		return "";
				
			
	
	}

	private String generateNextTokenadmision(FileRequeistion objection) {
		try {
			int initial = 0001;
			String caseId;
			
			Admission admission=new Admission();
		//	int currentYear = Year.now().getValue();
		    String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year

			//String caseId = user.getCaseId();
			// Fetch last inserted user
			
			Optional<Admission> lastInsertedUser = certificatOfficerRepo.findTopByDistrictOrderByCurrentdateDesc(objection.getDistrictName());
			if (lastInsertedUser.isPresent()) {
				String caseId2 = lastInsertedUser.get().getAdmisionCase();
				String[] parts = caseId2.split("-");  // Splitting by '-'
				int caseNumber = Integer.parseInt(parts[parts.length - 1]);
				int finalValue = caseNumber +1;
				String formattedValue = String.format("%04d", finalValue);
				admission.setAdmisionCase("Case-CO-"+objection.getDistrictName()+"-"+currentYear+"-"+formattedValue);
			//	System.out.println("Last inserted user: " + lastInsertedUser.get());
			} else {
				String formattedValue = String.format("%04d", initial);
				admission.setAdmisionCase("Case-CO-"+objection.getDistrictName()+"-"+currentYear+"-"+formattedValue);
			}
		//	System.out.println("last result--------->" + lastInsertedUser);

         //   User savedUser = caseIdRepository.save(user);
			caseId=admission.getAdmisionCase();
			 return caseId;  // Fixed this line
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Handle exception properly
	}

}
