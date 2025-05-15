package com.bor.rcms.service;

import java.beans.Beans;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.config.TimeSlot;
import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.CauseVo;
import com.bor.rcms.dto.CommisionaryReq;
import com.bor.rcms.dto.CourtReq;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.dto.RecoveryAmountVo;
import com.bor.rcms.entity.AddRecoveryAmmount;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseNotesPdr;
import com.bor.rcms.entity.CaseTransferPriviouseRecord;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.CertificateDebator;
import com.bor.rcms.entity.CourtAdd;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.DraftSaveCaseProceeding;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.LegalRepresentative;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.RoleEntity;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.AddRecoveryAmmountRepo;
import com.bor.rcms.repository.CaseNotesPdrRepo;
import com.bor.rcms.repository.CaseTransferPriviouseRecordRepo;
import com.bor.rcms.repository.CertificatDebatorRepo;
import com.bor.rcms.repository.CertificatOfficerRepo;
import com.bor.rcms.repository.CourtAddRepo;
import com.bor.rcms.repository.DocumentPDRRepository;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.DraftsSaveRepo;
import com.bor.rcms.repository.FileRequeistionRepo;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.repository.RoleRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.CaseRecodeRes;
import com.bor.rcms.resonse.ReqiestionResponnse;
import com.bor.rcms.resonse.ReqrusitionStatus;
import com.bor.rcms.response.StatusRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
@Transactional
public class PdrServiceImpl implements PdrService {
	@Autowired
	private DocumentPDRRepository documentRepository;
	private final String FILE_STORAGE_PATH = "C:/Users/Admin/file";
	@Autowired
	private CaseTransferPriviouseRecordRepo priviouseRecordRepo;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileRequeistionRepo fileRequeistionRepo;

	@Autowired
	private CertificatOfficerRepo certificatOfficerRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired

	private  CertificatDebatorRepo certificatDebatorRepo;
	@Autowired
	private CourtAddRepo courtAddRepo;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DraftsSaveRepo draftsSaveRepo;
	@Autowired
	private CaseNotesPdrRepo caseNotesPdrRepo;
	
	
	@Autowired
	private AddRecoveryAmmountRepo addRecoveryAmmountRepo;

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

		List<CertificateDebator> certificateDebators = new ArrayList<CertificateDebator>();

		List<CertificateDebator> certificateDebatorslist = requisition.getCertificateDebator();

		String debatorName = null;
		String addressdebator = null;

		for (CertificateDebator certificateDebator : certificateDebatorslist) {
			debatorName = certificateDebator.getDebatorName();
			addressdebator = certificateDebator.getAddress();

			certificateDebator.setRequeistion(requisition);
			certificateDebators.add(certificateDebator);

		}

		List<LegalRepresentative> legalRepresentative = requisition.getLegalRepresentative();

		List<LegalRepresentative> legalRepresentativesave = new ArrayList<LegalRepresentative>();
		
		for(LegalRepresentative legalset :legalRepresentative)
		{
			legalset.setFileRequeistion(requisition);

			legalRepresentativesave.add(legalset);
			
		}

		requisition.setLegalRepresentative(legalRepresentativesave);

		requisition.setCertificateDebator(certificateDebators);

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

			try {
				String district = requisition.getDistrictName();
				// String debtorName = ;
				String address = requisition.getUserId().getAddress();
				String amount = "200";
				String nature = "no";

				LocalDate localDate = LocalDate.parse(requisition.getCurrentDate());

				String day = String.valueOf(localDate.getDayOfMonth());
				String month = String.valueOf(localDate.getMonthValue());
				String year = String.valueOf(localDate.getYear());
				String designation = "NULL";
				;
				String notice = noticedgenerateform2(savedObjection, district, debatorName, addressdebator, nature,
						amount, month, year, day, designation);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			// Update the ObjectionEntity with the saved documents
			savedObjection.setDocuments(savedDocuments);
		}

		// Return the objectionId after the objection is successfully saved
		return objectionId;
	}

	private String noticedgenerateform2(FileRequeistion fileRequeistion, String district, String debtorName,
			String address, String nature, String amount, String month, String year, String day, String designation) {
		// TODO Auto-generated method stub
		String htmlContent = "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" + "<title>FORM No. 2</title>"
				+ "<style>" + "body { font-family: Arial, sans-serif; margin: 20px; }"
				+ ".center { text-align: center; font-weight: bold; }"
				+ "table { width: 100%; border-collapse: collapse; border: 1px solid #000; }"
				+ "th, td { border: 1px solid #000; padding: 10px; text-align: left; }"
				+ ".section { margin-bottom: 20px; }" + "</style>" + "</head>" + "<body>"

				+ "<div style='max-width: 800px; margin: 0 auto;'>"
				+ "<div class='center'><h2>FORM No. 2</h2><h3>Requisition for a certificate</h3><p style='font-style: italic;'>(See Section 5)</p></div>"

				+ "<div class='section'><p>To</p>"
				+ "<p style='margin-left: 40px;'>The Certificate Officer of the District of " + district + "</p></div>"

				+ "<hr style='border: 2px solid #000;'>"

				+ "<table>" + "<tr>" + "<th>Name of certificate debtor</th>" + "<th>Address of Certificate debtor</th>"
				+ "<th>Amount of public demand</th>" + "<th>Nature of public demand</th>" + "</tr>" + "<tr>" + "<td>"
				+ debtorName + "</td>" + "<td>" + address + "</td>" + "<td>Rs. " + amount + "</td>" + "<td>" + nature
				+ "</td>" + "</tr>" + "</table>"

				+ "<hr style='border: 2px solid #000; margin-top: 5px; margin-bottom: 30px;'>"

				+ "<div class='section'>" + "<p>I request you to recover the above-mentioned sum of Rs. " + amount
				+ ", which I am satisfied, after inquiry, is due from the said " + debtorName + " in respect of "
				+ nature + ".</p>" + "</div>"

				+ "<div class='section' style='margin-left: 40px;'>" + "<p>Verified by me on the " + day + " day of "
				+ month + " 19" + year + ".</p>" + "</div>"

				+ "<div style='text-align: right;'>" + "<p>A.B.</p>" + "<p style='font-style: italic;'>(" + designation
				+ ")</p>" + "</div>"

				+ "</div>" + "</body></html>";

		try {

			String fileName = "D:\\Form2_" + fileRequeistion.getRequeistionId() + debtorName.replaceAll(" ", "_")
					+ ".pdf";
			HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
			System.out.println("✅ PDF successfully saved to: " + fileName);

			DocumentEntityPdr enNoticeRelease = new DocumentEntityPdr();

			enNoticeRelease.setDocumentName("Form2");
			enNoticeRelease.setFilePath(fileName);
			enNoticeRelease.setFileRequeistion(fileRequeistion);

			DocumentEntityPdr DocumentEntity = documentRepository.save(enNoticeRelease);

		} catch (IOException e) {
			System.err.println("❌ PDF generation failed: " + e.getMessage());
		}

		return htmlContent;
	}

	public String generateNextToken(FileRequeistion objection) {
		try {
			int initial = 1; // Starting from 0001

			// Get current year in two digits (e.g., 25 for 2025)
			String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100)
					: Integer.toString(LocalDate.now().getYear() % 100);

			// Fetch last inserted NewObjection by districtName
			// Optional<FileRequeistion> lastInsertedUser = fileRequeistionRepo

			// //.findTopByDistrictNameOrderByCurrentDateDesc(objection.getDistrictName());

			String prefix = "REQ-" + objection.getDistrictName() + "-" + currentYear + "-";
			Optional<FileRequeistion> lastInsertedUser = fileRequeistionRepo
					.findTopByDistrictNameAndRequeistionIdStartingWithOrderByRequeistionIdDesc(
							objection.getDistrictName(), prefix);

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
			FileRequeistion objection = fileRequeistionRepo.findByRequeistionId(statusvo.getCaseId()).get();
		//	FileRequeistion objection = objection1.get();
			UserEntity entity= new UserEntity();
			try {
				 entity=userRepository.findByUserId(Long.valueOf(statusvo.getUserId()));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			CertificatOfficer admission = new CertificatOfficer();

			if (statusvo.getStatus().equals("Admit")) {
				admission.setAdmissionDate(statusvo.getAdmissionDate());
				admission.setAdmissionTime(statusvo.getAdmisionTime());
				admission.setAffidavitDate(statusvo.getAffedefitDate());
				
				admission.setOfficerName(statusvo.getOfficerName());
				
				LocalDate localDate = LocalDate.now(); // Or use LocalDate.of(2025, 4, 29)
				Date date = java.sql.Date.valueOf(localDate);
				admission.setCreatedDate(date);

				String caseID = generateNextTokenadmision(objection);
				admission.setCertOfficerId(caseID);
				admission.setAdmisionCase(caseID);
				objection.setStatus("Admit");

				// objection.setObjectionId(objection.getObjectionId());
				// objection.setAdmission(admission);
				admission.setFileRequeistion(objection);
				admission.setUserId(entity);
				admission.setHearingDate(statusvo.getAdmissionDate());
				admission.setStatus("Admit");
				// admission.setNewObjection(objection);
				admission.setReequeistionId(objection.getRequeistionId());

				// Mis mis = new Mis();

				if (statusvo.getUsertype().equals("CERTIFICATE_OFFICER")) {
//					mis.setSpecialOfficer("SpecialOfficer");
//					mis.setSpecialRemarks(statusvo.getStatus());
//					mis.setObjId(objection.getTokenNo());
//					mis.setNewObjection(objection);
					// objection.setAdmission(admission);
					// objection.setMis(mis);
					CertificatOfficer savedObjection = new CertificatOfficer();
					try {
                    
						
						CaseNotesPdr caseNotesPdr=new CaseNotesPdr();
						try {
						caseNotesPdr.setFileRequeistion(objection);
						caseNotesPdr.setUserId(entity);
						caseNotesPdr.setCreatedDate(date);
						caseNotesPdr.setCaseId(caseID);
						CaseNotesPdr savecase=caseNotesPdrRepo.save(caseNotesPdr);
						admission.setCaseNotesPdr(savecase);

						
						}
						catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
						// System.out.println("dd===>" + objection);

						savedObjection = certificatOfficerRepo.save(admission);
						if (savedObjection != null) {
							return savedObjection.getCertOfficerId();
						}
						return null;
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";

	}

	private String generateNextTokenadmision(FileRequeistion objection) {
		try {
			int initial = 0001;
			String caseId;

			Admission admission = new Admission();
			// int currentYear = Year.now().getValue();
			String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100)
					: Integer.toString(LocalDate.now().getYear() % 100); // Two-digit year

			// String caseId = user.getCaseId();
			// Fetch last inserted user

			Optional<CertificatOfficer> lastInsertedUser = certificatOfficerRepo
					.findTopByDistrictOrderByCurrentdateDesc(objection.getDistrictName());
			if (lastInsertedUser.isPresent()) {
				String caseId2 = lastInsertedUser.get().getAdmisionCase();
				String[] parts = caseId2.split("-"); // Splitting by '-'
				int caseNumber = Integer.parseInt(parts[parts.length - 1]);
				int finalValue = caseNumber + 1;
				String formattedValue = String.format("%04d", finalValue);
				admission.setAdmisionCase(
						"PDR-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
				// System.out.println("Last inserted user: " + lastInsertedUser.get());
			} else {
				String formattedValue = String.format("%04d", initial);
				admission.setAdmisionCase(
						"PDR-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
			}
			// System.out.println("last result--------->" + lastInsertedUser);

			// User savedUser = caseIdRepository.save(user);
			caseId = admission.getAdmisionCase();
			return caseId; // Fixed this line
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Handle exception properly
	}

	@Override
	public FileRequeistion savecaseDetails(CaseNotes casenotes) {
		try {
			// Fetch the NewObjection using the objection ID
			FileRequeistion newObjection = fileRequeistionRepo.findByRequeistionId(casenotes.getObjId()).get();

			CertificateDebator certificateDebatorslist = newObjection.getCertificateDebator().get(0);

			String formType = casenotes.getSelectForm().trim(); // Trim to remove any extra spaces

			// Check if the form is empty but hearing date is not empty
			if (casenotes.getSelectForm().equals("") && !casenotes.getNextHearingDate().equals("")) {
				FileRequeistion newObjection2 = new FileRequeistion();
				newObjection2.setStatus("date will be mandatory");
				return newObjection2;
			}

			if (formType.equals("Form1")) {

				String district = newObjection.getDistrictName();
				String certificateHolder = certificateDebatorslist.getDebatorName();
				String certificateDebtor = null;
				String demandDetails = null;
				String furtherDetails = null;
				LocalDate currentDate = LocalDate.now();

				String day = String.valueOf(currentDate.getDayOfMonth());
				String month = String.valueOf(currentDate.getMonthValue());
				String year = String.valueOf(currentDate.getYear());
				String officerDesignation = null;
				// String notice =
				// noticedgenerateform1(newObjection,district,certificateHolder,certificateDebtor,demandDetails,furtherDetails,day,month,year,officerDesignation);
			}
//
//		        // Check for Form B
			if (formType.equals("Form3")) {
				String debtorName = certificateDebatorslist.getDebatorName();
				String reason = null;
				String section = null;
				LocalDate currentDate = LocalDate.now();

				String day = String.valueOf(currentDate.getDayOfMonth());
				String month = String.valueOf(currentDate.getMonthValue());
				String year = String.valueOf(currentDate.getYear());
				String officerLocation = null;
				System.out.println("form2");
				// String notice =
				// noticedgenerateform3(newObjection,debtorName,reason,section,day,month,year,officerLocation);
			}

			// Check for Form A (corrected comparison and removed extra semicolon)

			if (newObjection != null && newObjection.getRequeistionId() != null) {

				CertificatOfficer admission = newObjection.getCertificatOfficer();
				if (admission != null) {

					admission.setAction(casenotes.getCaseClass());
					admission.setHearingDate(casenotes.getNextHearingDate());
					admission.setHearingTime(casenotes.getTime());
					admission.setCaseClass(casenotes.getCaseClass());
					admission.setStatus(casenotes.getAction());
					admission.setReequeistionId(newObjection.getRequeistionId());

//				byte[] notesBytes = casenotes.getCaseNotes().getBytes("UTF-8");
//				Blob notesBlob = new SerialBlob(notesBytes);

//				if(casenotes.getAction().equals("Dismiss"))
//				{
//					admission.setStatusCollector("pending");
//				}

					//admission.setCaseNotes(casenotes.getCaseNotes());
					
					CaseNotesPdr casepdr=caseNotesPdrRepo.findByFileRequeistion(newObjection); 
					casepdr.setModifiedDate(new Date());
					casenotes.setCaseNotes(casenotes.getCaseNotes());
					admission.setCaseNotesPdr(casepdr);
					//addCaseNit
					admission.setFileRequeistion(newObjection);
					// Admission savedAdmission = admissionRepo.save(admission);
					newObjection.setCertificatOfficer(admission);

					admission.setDistrict(newObjection.getDistrictName());
//				Mis mis = new Mis();
//				try {
//					mis = newObjection.getMis();
//					mis.setSpecialOfficer("SpecialOfficer");
//					mis.setCaseId(newObjection.getTokenNo());
//					mis.setNewObjection(newObjection);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				newObjection.setMis(mis);
					// newObjection.setStatus_officer("SpecialOfficer");
					newObjection.setStatus(casenotes.getAction());

//				if(casenotes.getAction().equals("Dismiss"))
//				{
//					newObjection.setStatusCollector("pending");
//				}
					FileRequeistion savedNewObjection = fileRequeistionRepo.save(newObjection);

					return savedNewObjection;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String noticedgenerateform3(FileRequeistion fileRequeistion, String debtorName, String reason,
			String section, String day, String month, String year, String officerLocation) {
		String htmlContent = "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" + "<title>FORM No. 3</title>"
				+ "<style>" + "body { font-family: Arial, sans-serif; margin: 20px; }"
				+ ".center { text-align: center; font-weight: bold; }" + ".italic { font-style: italic; }"
				+ ".container { max-width: 800px; margin: 0 auto; padding: 20px; }"
				+ "p { text-align: justify; line-height: 1.6; }" + "table { width: 100%; margin-top: 40px; }"
				+ "td { vertical-align: top; }" + "</style>" + "</head>" + "<body>"

				+ "<div class='container'>"
				+ "<div class='center'><h2>FORM No. 3</h2><h3>Nature of Certificate-debtor</h3><p class='italic'>(See Section 7)</p></div>"

				+ "<p>To</p>" + "<p style='margin-left: 40px;' class='italic'>" + debtorName + "</p>"

				+ "<p>You are hereby informed that a certificate against you for Rs............................ due from you on account of "
				+ reason + ", has this day been filed in my office, under section " + section
				+ " of the Bihar and Orissa Public Demands Recovery Act, 1914. If you deny your liability to pay the said sum of Rs............................................... you may, within thirty days from the service of this notice, file in my office a petition denying liability, in whole or in part. If, within the said thirty days, you fail to file such a petition, or if you fail to show cause, or do not show sufficient cause, why such certificate should not be executed, it will be executed, under the provisions of the said Act, unless you pay Rs................................ (Rs......................... on account of the demand and Rs.................................... on account of costs of realization) into my office. Until the said amount is so paid you are hereby prohibited from alienating your immovable property, or any part of it, by sale, gift, mortgage or otherwise. If you in the meantime conceal, remove or dispose of any part of your movable property, the certificate will be executed immediately.</p>"

				+ "<p>A copy of the certificate above-mentioned is hereto annexed.</p>"
				+ "<p>You may remit the amount by money order, quoting the number and year of the certificate.</p>"

				+ "<p>Dated this " + day + " Day of " + month + " 19" + year + ".</p>"

				+ "<table>" + "<tr>" + "<td></td>" + "<td style='text-align: right;'>" + "<p>A.B.</p>"
				+ "<p class='italic'>(Certificate Officer of " + officerLocation + ")</p>" + "</td>" + "</tr>"
				+ "</table>"

				+ "</div>" + "</body></html>";

		try {
			DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
			String currentMinute = LocalDateTime.now().format(fileNameFormatter);
			String fileName = "D:\\Form3_" + fileRequeistion.getRequeistionId() + currentMinute + ".pdf";

			DocumentEntityPdr enNoticeRelease = new DocumentEntityPdr();

			enNoticeRelease.setDocumentName("Form3");
			enNoticeRelease.setFilePath(fileName);
			enNoticeRelease.setFileRequeistion(fileRequeistion);

			DocumentEntityPdr DocumentEntity = documentRepository.save(enNoticeRelease);

			HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
			System.out.println("✅ PDF successfully saved to: " + fileName);
		} catch (IOException e) {
			System.out.println("❌ Error generating PDF: " + e.getMessage());
		}

		return htmlContent;
	}

	// TODO Auto-generated method stub

	private String noticedgenerateform1(FileRequeistion fileRequeistion, String district, String certificateHolder,
			String certificateDebtor, String demandDetails, String furtherDetails, String day, String month,
			String year, String officerDesignation) {

		String htmlContent = "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + "<meta charset='UTF-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" + "<title>FORM No. 1</title>"
				+ "<style>" + "body { font-family: Arial, sans-serif; margin: 20px; }"
				+ ".center { text-align: center; }" + ".bold { font-weight: bold; }"
				+ "table { width: 100%; border-collapse: collapse; border: 1px solid #000; margin-top: 20px; }"
				+ "th, td { border: 1px solid #000; padding: 10px; vertical-align: top; }"
				+ ".signature { margin-top: 50px; font-weight: bold; display: flex; justify-content: space-between; }"
				+ "</style>" + "</head>" + "<body>"

				+ "<div style='max-width: 800px; margin: 0 auto;'>" + "<div class='center'>"
				+ "<h2 class='bold'>FORM No. 1</h2>" + "<h3 class='bold'>Certificate of Public Demands</h3>"
				+ "<p>(See Section 4 and 6)</p>"
				+ "<p style='font-style: italic;'>Filed in the Office of the Certificate Officer of " + district
				+ ".</p>" + "</div>"

				+ "<hr style='border-top: 2px solid #000;'>"

				+ "<table>" + "<tr>" + "<th>Number of Certificate</th>"
				+ "<th>Name and address of certificate holder</th>" + "<th>Name and address of certificate debtor</th>"
				+ "<th>Amount of public demand</th>" + "<th>Further particulars of the demand</th>" + "</tr>" + "<tr>"
				+ "<td></td>" + "<td>" + certificateHolder + "</td>" + "<td>" + certificateDebtor + "</td>" + "<td>"
				+ demandDetails + "</td>" + "<td>" + furtherDetails + "</td>" + "</tr>" + "</table>"

				+ "<hr style='border-top: 2px solid #000;'>"

				+ "<p>I hereby certify that the above-mentioned sum of Rs ....................................... is due from the above-named person.</p>"
				+ "<p><i>[If the certificate is signed or represented under section 5, add-]</i></p>"
				+ "<p>I further certify that the above-mentioned sum is justly recoverable, and that its recovery by suit is not barred by law.</p>"
				+ "<p>Dated this " + day + " day of " + month + " 19" + year + ".</p>"

				+ "<div class='signature'>" + "<div><p>!Certificate Holder</p></div>"
				+ "<div style='text-align: right;'>" + "<p>A.B.</p>" + "<p>" + officerDesignation + "</p>" + "</div>"
				+ "</div>"

				+ "</div>" + "</body></html>";

		try {

			DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
			String currentMinute = LocalDateTime.now().format(fileNameFormatter);

			String fileName = "D:\\Form1_" + fileRequeistion.getRequeistionId() + currentMinute + ".pdf";
			HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(fileName));
			System.out.println("✅ PDF successfully saved to: " + fileName);

			DocumentEntityPdr enNoticeRelease = new DocumentEntityPdr();

			enNoticeRelease.setDocumentName("Form1");
			enNoticeRelease.setFilePath(fileName);
			enNoticeRelease.setFileRequeistion(fileRequeistion);

			DocumentEntityPdr DocumentEntity = documentRepository.save(enNoticeRelease);

		} catch (IOException e) {
			System.out.println("❌ Error generating PDF: " + e.getMessage());
		}

		return htmlContent;
	}

	@Override
	public List<FileRequeistion> findAdmit(String district) {
		return fileRequeistionRepo.findAllAdmit(district);

	}

	@Override
	public String addCourt(CourtReq courtReq) {

		try {
			UserEntity courtAdd = new UserEntity();

			courtAdd.setAddress(courtReq.getOfficeDetails());

			courtAdd.setPhoneNumber(courtReq.getOfficeMobile());
			courtAdd.setFullName(courtReq.getOfficeName());

			courtAdd.setEmail(courtReq.getOfficerEmail());

			UserEntity entity1 = new UserEntity();
			try {
				entity1 = userRepository.findByEmail(courtReq.getOfficerEmail());

				if (entity1.getUserId() != null) {
					return "try another email";
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			try {
				courtAdd.setPassword(passwordEncoder.encode(courtReq.getPassword()));

			} catch (Exception e) {
				// TODO: handle exception
			}

//			courtReq.setOfficeDetails(courtAdd.getAddress());
//			courtReq.setOfficeMobile(courtAdd.getPanNumber());
//			courtReq.setOfficeName(courtAdd.getFullName());
//			courtReq.setOfficerEmail(courtAdd.getFullName());
//		
//		
			// courtReq.setAssignUSer(entity.getUserName());

			UserEntity entity = userRepository.findById(courtReq.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));

			UserEntity courtfindMobile = userRepository.findByPhoneNumber(courtReq.getOfficeMobile());

			courtAdd.setCreatedByuser(entity.getUserId());
			if (entity != null) {
				courtAdd.setCreatedByuser(entity.getUserId());
				courtAdd.setDistrict(entity.getDistrict());
				// courtAdd.setUserId(entity);
				RoleEntity role = new RoleEntity();

				String roleName = null;
				try {
					roleName = courtfindMobile.getRole().getRoleName();

					role = roleRepository.findByRoleName(roleName);
					if (role == null) {
						role = new RoleEntity(roleName, roleName);
						role = roleRepository.save(role);
					}

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				UserEntity courtAddsave = new UserEntity();

				if (courtReq.getUpdaStatus() == null && courtfindMobile != null) {
					return "try another number";
				}
				try {

					// courtReq.setRole(entity.getRole().getRoleName());
					if (courtReq.getUpdaStatus().equals("update")) {
						courtAdd.setRole(role);
						courtAdd.setUserId(courtfindMobile.getUserId());
						courtAdd.setStatus(courtReq.getStatus());

						courtAddsave = userRepository.save(courtAdd);
						if (courtAddsave != null) {
							return "save";
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// courtAdd.setr;
				role = roleRepository.findByRoleName(courtReq.getRole());
				if (role == null) {
					role = new RoleEntity(roleName, roleName);
					role = roleRepository.save(role);
				}
				courtAdd.setRole(role);
				courtAdd.setStatus(courtReq.getStatus());
				courtAddsave = userRepository.save(courtAdd);
				if (courtAddsave != null) {
					return "save";
				}
				return "something issue";

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourtReq> addCourtlistShow(Long userId) {
		// TODO Auto-generated method stub
		try {
			UserEntity entity = userRepository.findById(userId).get();
			if (entity != null) {
				List<UserEntity> courtAdds = userRepository.findByCreatedByuser(entity.getUserId());

				List<CourtReq> courtAddslist = new ArrayList<CourtReq>();

				for (UserEntity userEntity : courtAdds) {
					CourtReq courtReq = new CourtReq();

					courtReq.setOfficeDetails(userEntity.getAddress());
					courtReq.setOfficeMobile(userEntity.getPhoneNumber());
					courtReq.setOfficeName(userEntity.getFullName());
					courtReq.setOfficerEmail(userEntity.getEmail());

					courtReq.setAssignUSer(entity.getFullName());
					courtReq.setStatus(userEntity.getStatus());
					courtReq.setCourtId(userEntity.getUserId());
					courtAddslist.add(courtReq);

				}

				return courtAddslist;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public StatusRes noticeGenerate(String selectForm, String reqId) {
		StatusRes statusRes = new StatusRes();

		FileRequeistion newObjection = new FileRequeistion();
		try {
			newObjection = fileRequeistionRepo.findByRequeistionId(reqId).get();

			if (newObjection.getRequeistionId() == null) {
				statusRes.setMessage("reqId not found");
				statusRes.setStatus("404");
				return statusRes;
			}

		} catch (Exception e) {
			// TODO: handle exception
			statusRes.setMessage("reqId not found");
			statusRes.setStatus("404");
			e.printStackTrace();
			return statusRes;

		}
		CertificateDebator certificateDebatorslist = newObjection.getCertificateDebator().get(0);

		String formType = selectForm.trim(); // Trim to remove any extra spaces

		// Check if the form is empty but hearing date is not empty

		if (formType.equals("Form1")) {

			String district = newObjection.getDistrictName();
			String certificateHolder = certificateDebatorslist.getDebatorName();
			String certificateDebtor = null;
			String demandDetails = null;
			String furtherDetails = null;
			LocalDate currentDate = LocalDate.now();

			String day = String.valueOf(currentDate.getDayOfMonth());
			String month = String.valueOf(currentDate.getMonthValue());
			String year = String.valueOf(currentDate.getYear());
			String officerDesignation = null;
			String notice = noticedgenerateform1(newObjection, district, certificateHolder, certificateDebtor,
					demandDetails, furtherDetails, day, month, year, officerDesignation);

			if (notice != null) {
				statusRes.setMessage("form1 will be sumbitted");
				statusRes.setStatus("200");
			}
			return statusRes;

		}
//
//	        // Check for Form B
		if (formType.equals("Form3")) {
			String debtorName = certificateDebatorslist.getDebatorName();
			String reason = null;
			String section = null;
			LocalDate currentDate = LocalDate.now();

			String day = String.valueOf(currentDate.getDayOfMonth());
			String month = String.valueOf(currentDate.getMonthValue());
			String year = String.valueOf(currentDate.getYear());
			String officerLocation = null;
			System.out.println("form2");
			String notice = noticedgenerateform3(newObjection, debtorName, reason, section, day, month, year,
					officerLocation);

			if (notice != null) {
				statusRes.setMessage("form3 will be sumbitted");
				statusRes.setStatus("200");
			}
			return statusRes;
		}
		statusRes.setMessage("check credentiall");
		statusRes.setStatus("404");

		return statusRes;
	}

	@Override
	public String caseTransfer(List<String> reqId, String nouserId) {
		try {
			

			for (int i = 0; i < reqId.size(); i++) {
				Optional<FileRequeistion> optionalRequest = fileRequeistionRepo.findByRequeistionId(reqId.get(i));
				if (optionalRequest.isPresent()) {
					FileRequeistion newObjection = optionalRequest.get();
					newObjection.setIsTransOfficer(false);
					newObjection.setNominalOffstatus(newObjection.getStatus());
					newObjection.setIsTransNomOfficer(true);
					newObjection.setTransNomId(nouserId);
					fileRequeistionRepo.save(newObjection);
					
					
					//case Transfer save Preivious saved
					
					try{
						UserEntity entity = userRepository.findById(Long.valueOf(nouserId)).get();
                        CertificatOfficer certificatOfficer=certificatOfficerRepo.findByFileRequeistion(newObjection);
						
						CaseTransferPriviouseRecord  priviouseRecord=new CaseTransferPriviouseRecord(); 
						
						BeanUtils.copyProperties(certificatOfficer, priviouseRecord);
						priviouseRecord.setTransferUser(entity);
						priviouseRecordRepo.save(priviouseRecord);
					}
					catch (Exception e) {
						// TODO: handle exception
					}
					
					
				} else {
					return "Request ID not found: " + reqId.get(i);
				}
			}

			return "save";

		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong during case transfer";
		}
	}

	@Override
	public List<FileRequeistion> findpendingNom(String userId) {
		// TODO Auto-generated method stub
		return fileRequeistionRepo.findByTransNomId(userId);

	}

	@Override
	public List<FileRequeistion> findAllByuserId(String userId) {
		// TODO Auto-generated method stub

		UserEntity entity = userRepository.findById(Long.valueOf(userId)).get();

		return fileRequeistionRepo.findByUserId(entity);
	}

	@Override
	public FileRequeistion findBydebatorId(String userId) {
		// TODO Auto-generated method stub

		FileRequeistion optionalRequest = fileRequeistionRepo.findByRequeistionId(userId).get();

		return optionalRequest;
	}

	@Override
	public List<String> findSlotTime(String date) {
	    List<CertificatOfficer> listslot = certificatOfficerRepo.findByAdmissionTime(date);

	    Set<String> bookedTimes = new HashSet();
	    for (CertificatOfficer officer : listslot) {
	        bookedTimes.add(officer.getAdmissionTime());
	    }

	    TimeSlot timeSlot = new TimeSlot();
	    List<String> allValidSlots = timeSlot.timeslote(); 

	    List<String> availableSlots = new ArrayList<>();
	    for (String slot : allValidSlots) {
	        if (!bookedTimes.contains(slot)) {
	            availableSlots.add(slot);
	        }
	    }

	    return availableSlots;
	}
	@Override
	public List<CertificatOfficer> findByReqId(String caseId, String caseDate) {
	    
	    List<CertificatOfficer> result=new ArrayList<CertificatOfficer>();

	    try {
	        boolean hasCaseId = caseId != null && !caseId.isEmpty();
	        boolean hasCaseDate = caseDate != null && !caseDate.isEmpty();

	        if (hasCaseId && hasCaseDate) {
	        	
	          //  fileRequeistionRepo.findByRequeistionIdAndCurrentDate(caseId, caseDate);
	            
	            //    .ifPresent(result::add);
	            
	        	certificatOfficerRepo.findByCertOfficerIdAndHearingDate(caseId, caseDate)
		                .ifPresent(result::add);

	            
	        } else if (hasCaseId) {
	        	certificatOfficerRepo.findByCertOfficerId(caseId)
	                .ifPresent(result::add);
	        } else if (hasCaseDate) {
	        	certificatOfficerRepo.findByadmissionDate(caseDate)
	                .ifPresent(result::add);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Consider proper logging in production
	    }

	    return result;
	}

	@Override
	public String upadateCauseStatus(@Valid CauseVo causeVo) {
		
    	CertificatOfficer certificatOfficer= certificatOfficerRepo.findByCertOfficerId(causeVo.getCaseId()).get();
    	
    	if(certificatOfficer!=null)
    	{
    		certificatOfficer.setAction(causeVo.getAction());
    		certificatOfficer.setHearingTime(causeVo.getHearingTime());
    		certificatOfficer.setHearingDate(causeVo.getHearinDate());
    		certificatOfficer.setCaseClass(causeVo.getClasse());
    		certificatOfficer.setReason(causeVo.getReason());
    		
    		CertificatOfficer certificatOfficer2=certificatOfficerRepo.save(certificatOfficer);
    		if(certificatOfficer2.getCertOfficerId()!=null)
    		{
    			return "update";
    		}
			return "data not update";

    	}

		
		return "data not available";
	}

	
	@Override
	public List<CertificatOfficer> findAllCause(String district) {
	    List<CertificatOfficer> result = new ArrayList<>();

	    try {
	        Optional<CertificatOfficer> optionalOfficer =
	            certificatOfficerRepo.findTopByDistrictOrderByCurrentdateDesc(district);

	        optionalOfficer.ifPresent(result::add);

	    } catch (Exception e) {
	        e.printStackTrace(); // Prefer logging in production
	    }

	    return result;
	}

	@Override
	public List<ReqrusitionStatus>  getcaseStatus(String userId) {
		// TODO Auto-generated method stub
		List<ReqrusitionStatus> reqrusitionStatuslist=new  ArrayList<ReqrusitionStatus>();
		UserEntity entity=userRepository.findByUserId(Long.valueOf(userId));
		if(entity!=null)
		{
			List<FileRequeistion> fileRequeistion=findAllByuserId(userId);
			
			for(FileRequeistion fileRequeistion2:fileRequeistion)
			{
				ReqrusitionStatus reqrusitionStatus2=new ReqrusitionStatus();
				try {
				CertificatOfficer certificatOfficer=certificatOfficerRepo.findByFileRequeistion(fileRequeistion2);
				
				reqrusitionStatus2.setAction(certificatOfficer.getAction());
				reqrusitionStatus2.setCaseId(certificatOfficer.getCertOfficerId());
				reqrusitionStatus2.setHiringDate(certificatOfficer.getHearingDate());

				
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				reqrusitionStatus2.setReqId(fileRequeistion2.getRequeistionId());
				reqrusitionStatus2.setCaseStatus(fileRequeistion2.getStatus());
				reqrusitionStatus2.setDemandAmmount(fileRequeistion2.getTotalDemand());
				reqrusitionStatus2.setTotalAmmount(fileRequeistion2.getTotalOutstandingAmmount());
				reqrusitionStatus2.setDefaulterName(fileRequeistion2.getUserId().getFullName());
				
				reqrusitionStatuslist.add(reqrusitionStatus2);
			}
			return reqrusitionStatuslist;
			
		}
		return null;
	}

	@Override
	public StatusRes addDraft(String draft, String caseId) {
           try {
        	   StatusRes res=new StatusRes();
        	   DraftSaveCaseProceeding draftSaveCaseProceeding=new DraftSaveCaseProceeding();
        	   draftSaveCaseProceeding.setCaseId(caseId);
        	   draftSaveCaseProceeding.setDraft(draft);
        	   
        	   DraftSaveCaseProceeding saveDraft=draftsSaveRepo.save(draftSaveCaseProceeding);
        	   
        	   if(saveDraft!=null)
        	   {
        		   res.setMessage("save");
        		   return res;
        	   }
        	   
        	   
           }catch (Exception e) {
			
        	   e.printStackTrace();
        	   // TODO: handle exception
		}
		return null;
	}

	@Override
	public DraftSaveCaseProceeding FindDraft(String caseId) {
		// TODO Auto-generated method stub
		
		try {
			
			DraftSaveCaseProceeding caseProceedingdraft=draftsSaveRepo.findLatestDraftByCaseId(caseId);
			
			if(caseProceedingdraft!=null)
			{
				return caseProceedingdraft;
			}
			return null;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<CaseRecodeRes> getcaseRecord(String userId) {
		List<CaseRecodeRes> reqrusitionStatuslist=new  ArrayList<CaseRecodeRes>();
		UserEntity entity=userRepository.findByUserId(Long.valueOf(userId));
		try {
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(entity!=null)
		{
			List<CertificatOfficer> certificatOfficers=certificatOfficerRepo.findAllByuserId(entity);

			List<FileRequeistion> fileRequeistion=findAllByuserId(userId);
			
			for(CertificatOfficer certificatOfficer2:certificatOfficers)
			{
				CaseRecodeRes reqrusitionStatus2=new CaseRecodeRes();
				FileRequeistion fileRequeistion2=certificatOfficer2.getFileRequeistion();
				
				CertificatOfficer certificatOfficer=certificatOfficerRepo.findByFileRequeistion(fileRequeistion2);
				if(certificatOfficer!=null)
				{
				reqrusitionStatus2.setAction(certificatOfficer.getAction());
				reqrusitionStatus2.setCaseId(certificatOfficer.getCertOfficerId());
				reqrusitionStatus2.setHiringDate(certificatOfficer.getHearingDate());

				reqrusitionStatus2.setHearingTime(certificatOfficer.getHearingTime());

				
				
				reqrusitionStatus2.setGranterName(fileRequeistion2.getCertificateGuaranter().getGranterName());
				reqrusitionStatus2.setReqId(fileRequeistion2.getRequeistionId());
				reqrusitionStatus2.setCaseStatus(fileRequeistion2.getStatus());
				reqrusitionStatus2.setDemandAmmount(fileRequeistion2.getTotalDemand());
				reqrusitionStatus2.setTotalAmmount(fileRequeistion2.getTotalOutstandingAmmount());
				
				try {
					List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(fileRequeistion2);
					CertificateDebator certificateDebator=certificateDebatorlist.get(0);
					reqrusitionStatus2.setDefaulterName(certificateDebator.getDebatorName());

					
				}catch (Exception e) {
					// TODO: handle exception
				}
				
			//	reqrusitionStatus2.setDefaulterName(fileRequeistion2.getUserId().getFullName());
				
				
				reqrusitionStatuslist.add(reqrusitionStatus2);
				}
			}
			return reqrusitionStatuslist;
			
		}
		return null;
	}

	@Override
	public List<CaseRecodeRes> getcaseRecordFilter(String sector, String bank, String department, String branchCode) {
		// TODO Auto-generated method stub
		try {
			List<CaseRecodeRes> reqrusitionStatuslist=new  ArrayList<CaseRecodeRes>();

			List<UserEntity> entitieslist=userRepository.findByOptionalFields(bank, branchCode, sector, department);
			System.out.println("list===>"+entitieslist);
			
			for(UserEntity entity: entitieslist)
			{
				List<FileRequeistion> fileRequeistion=findAllByuserId(String.valueOf(entity.getUserId()));
				for(FileRequeistion fileRequeistion2:fileRequeistion)
				{
					CaseRecodeRes reqrusitionStatus2=new CaseRecodeRes();
					
					CertificatOfficer certificatOfficer=certificatOfficerRepo.findByFileRequeistion(fileRequeistion2);
					if(certificatOfficer!=null)
					{
					reqrusitionStatus2.setAction(certificatOfficer.getAction());
					reqrusitionStatus2.setCaseId(certificatOfficer.getCertOfficerId());
					reqrusitionStatus2.setHiringDate(certificatOfficer.getHearingDate());

					reqrusitionStatus2.setHearingTime(certificatOfficer.getHearingTime());

					
					
					reqrusitionStatus2.setGranterName(fileRequeistion2.getCertificateGuaranter().getGranterName());
					reqrusitionStatus2.setReqId(fileRequeistion2.getRequeistionId());
					reqrusitionStatus2.setCaseStatus(fileRequeistion2.getStatus());
					reqrusitionStatus2.setDemandAmmount(fileRequeistion2.getTotalDemand());
					reqrusitionStatus2.setTotalAmmount(fileRequeistion2.getTotalOutstandingAmmount());
					reqrusitionStatus2.setDefaulterName(fileRequeistion2.getUserId().getFullName());
					
					
					reqrusitionStatuslist.add(reqrusitionStatus2);
					}
				}
				return reqrusitionStatuslist;
				
			}
			
		
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addcommisionary(CommisionaryReq courtReq) {
		try {
			try {
				UserEntity entity2 = new UserEntity();
				entity2=userRepository.findByUserId(Long.valueOf(courtReq.getUserId()));
				
				RoleEntity roleEntity=entity2.getRole();
				if(!roleEntity.getRoleName().equals("BOR"))
				{
					return "You are Not BOR";
					
	    		}
		
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			UserEntity courtAdd = new UserEntity();

			courtAdd.setAddress(courtReq.getOfficeDetails());
			courtAdd.setCommisionary(courtReq.getCommisoner());
			//System.out.println("district===>"+courtReq.getDisrict());
			courtAdd.setDistrict(courtReq.getDistrict());
			
			courtAdd.setPhoneNumber(courtReq.getOfficeMobile());
			courtAdd.setFullName(courtReq.getOfficeName());
			courtAdd.setEmail(courtReq.getOfficerEmail());
			UserEntity entity1 = new UserEntity();
			try {
				entity1 = userRepository.findByEmail(courtReq.getOfficerEmail());
				if (entity1.getUserId() != null) {
					return "try another email";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				courtAdd.setPassword(passwordEncoder.encode(courtReq.getPassword()));
			} catch (Exception e) {
				// TODO: handle exception
			}

//			courtReq.setOfficeDetails(courtAdd.getAddress());
//			courtReq.setOfficeMobile(courtAdd.getPanNumber());
//			courtReq.setOfficeName(courtAdd.getFullName());
//			courtReq.setOfficerEmail(courtAdd.getFullName());
//		
//		
			// courtReq.setAssignUSer(entity.getUserName());

			UserEntity entity = userRepository.findById(courtReq.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));

			UserEntity courtfindMobile = userRepository.findByPhoneNumber(courtReq.getOfficeMobile());

			courtAdd.setCreatedByuser(entity.getUserId());
			if (entity != null) {
				courtAdd.setCreatedByuser(entity.getUserId());
				courtAdd.setDistrict(courtReq.getDistrict());
				// courtAdd.setUserId(entity);
				RoleEntity role = new RoleEntity();

				String roleName = null;
				try {
					roleName = courtfindMobile.getRole().getRoleName();

					role = roleRepository.findByRoleName(roleName);
					if (role == null) {
						role = new RoleEntity(roleName, roleName);
						role = roleRepository.save(role);
					}

				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}

				UserEntity courtAddsave = new UserEntity();

				if (courtReq.getUpdaStatus() == null && courtfindMobile != null) {
					return "try another number";
				}
				try {

					// courtReq.setRole(entity.getRole().getRoleName());
					if (courtReq.getUpdaStatus().equals("update")) {
						courtAdd.setRole(role);
						courtAdd.setUserId(courtfindMobile.getUserId());
						courtAdd.setStatus(courtReq.getStatus());

						courtAddsave = userRepository.save(courtAdd);
						if (courtAddsave != null) {
							return "save";
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// courtAdd.setr;
				role = roleRepository.findByRoleName(courtReq.getRole());
				if (role == null) {
					role = new RoleEntity(roleName, roleName);
					role = roleRepository.save(role);
				}
				courtAdd.setRole(role);
				courtAdd.setStatus(courtReq.getStatus());
				courtAddsave = userRepository.save(courtAdd);
				if (courtAddsave != null) {
					return "save";
				}
				return "something issue";

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CommisionaryReq> showcommisionaryList(Long userId) {
		try {
			UserEntity entity = userRepository.findById(userId).get();
			if (entity != null) {
				List<UserEntity> courtAdds = userRepository.findByCreatedByuser(entity.getUserId());

				List<CommisionaryReq> courtAddslist = new ArrayList<CommisionaryReq>();

				for (UserEntity userEntity : courtAdds) {
					CommisionaryReq courtReq = new CommisionaryReq();

					courtReq.setOfficeDetails(userEntity.getAddress());
					courtReq.setOfficeMobile(userEntity.getPhoneNumber());
					courtReq.setOfficeName(userEntity.getFullName());
					courtReq.setOfficerEmail(userEntity.getEmail());
					courtReq.setCommisoner(userEntity.getCommisionary());
					courtReq.setDistrict(userEntity.getDistrict());

					courtReq.setAssignUSer(entity.getFullName());
					courtReq.setStatus(userEntity.getStatus());
					//courtReq.setCourtId(entity.getUserId());
					courtAddslist.add(courtReq);

				}

				return courtAddslist;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	@Override
	public StatusRes saveDraft(String draft, String caseId) {
		  try {
       	   StatusRes res=new StatusRes();
       	   DraftSaveCaseProceeding draftSaveCaseProceeding=new DraftSaveCaseProceeding();
       	   draftSaveCaseProceeding.setCaseId(caseId);
       	   draftSaveCaseProceeding.setDraft(null);
       	   
       	   DraftSaveCaseProceeding saveDraft=draftsSaveRepo.save(draftSaveCaseProceeding);
       	CaseNotesPdr casesaveNotes=new CaseNotesPdr();
       	   CaseNotesPdr caseNotesPdr=caseNotesPdrRepo.findByCaseId(caseId);
       	
       	//caseNotesPdr.setCaseNotes(caseNotesPdr.getCaseNotes()+draft);
       	
        if (caseNotesPdr != null) {	
            String existingNotes = caseNotesPdr.getCaseNotes();
            if (existingNotes == null) existingNotes = ""; 

            caseNotesPdr.setCaseNotes(existingNotes + draft);
            caseNotesPdr.setModifiedDate(new Date());
            casesaveNotes=  caseNotesPdrRepo.save(caseNotesPdr);
            
        }
       	   
       	   
       	   if(casesaveNotes!=null)
       	   {
       		   res.setMessage("save");
       		   return res;
       	   }
       	   
       	   
          }catch (Exception e) {
			
       	   e.printStackTrace();
       	   // TODO: handle exception
		}
		return null;
	}

	@Override
	public AddRecoveryAmmount addrecoveryAmount(RecoveryAmountVo recoveryAmountVo) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDateTime = LocalDateTime.now().format(formatter);
			
			UserEntity user = userRepository.findById(Long.valueOf(recoveryAmountVo.getUserId()))
					.orElseThrow(() -> new RuntimeException("User not found"));			

			AddRecoveryAmmount recoveramount=new AddRecoveryAmmount();
			BeanUtils.copyProperties(recoveramount, recoveryAmountVo);
			recoveramount.setCreatedDate(formattedDateTime);
			recoveramount.setUserId(user);
			recoveramount.setCreatedByuser(String.valueOf(user.getUserId()));
			
			AddRecoveryAmmount savedata=addRecoveryAmmountRepo.save(recoveramount);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
