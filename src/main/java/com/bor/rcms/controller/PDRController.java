package com.bor.rcms.controller;

import java.io.File;
import java.io.IOException;

import com.bor.rcms.repository.FileRequeistionRepo;
import com.bor.rcms.repository.LegalRepersentativeRepo;

import org.springframework.util.StringUtils;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.config.CourtFeeSlab;
import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.CauseVo;
import com.bor.rcms.dto.CommisionaryReq;
import com.bor.rcms.dto.CourtReq;
import com.bor.rcms.dto.DebatorVo;
import com.bor.rcms.dto.DocumentDTO;
import com.bor.rcms.dto.FileRequeistionDTO;
import com.bor.rcms.dto.FileRequeistionVo;
import com.bor.rcms.dto.LegalRepersentativeVo;
import com.bor.rcms.dto.ObjectionVo;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.dto.RecoveryAmountVo;
import com.bor.rcms.entity.AddRecoveryAmmount;
import com.bor.rcms.entity.CaseNotesPdr;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.CertificateDebator;
import com.bor.rcms.entity.CertificateGuaranter;
import com.bor.rcms.entity.CommisionaryMap;
import com.bor.rcms.entity.CourtAdd;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.DraftSaveCaseProceeding;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.LegalRepresentative;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.PoliceStation;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.AddRecoveryAmmountRepo;
import com.bor.rcms.repository.CaseNotesPdrRepo;
import com.bor.rcms.repository.CertificatDebatorRepo;
import com.bor.rcms.repository.CertificatOfficerRepo;
import com.bor.rcms.repository.CommisionaryMapRepo;
import com.bor.rcms.repository.CourtAddRepo;
import com.bor.rcms.repository.DocumentPDRRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.CaseRecodeRes;
import com.bor.rcms.resonse.CauseListResponse;
import com.bor.rcms.resonse.ReqiestionResponnse;
import com.bor.rcms.resonse.ReqrusitionStatus;
import com.bor.rcms.response.StatusRes;
import com.bor.rcms.response.StatusResponse;
import com.bor.rcms.service.CourtFeeService;
import com.bor.rcms.service.PdrService;
import com.bor.rcms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/pdr")
@Transactional
public class PDRController {

	@Autowired
	private UserService userService;
	
	@Autowired
    private  CourtFeeService courtFeeService;
   
	@Autowired
	private CertificatDebatorRepo certificatDebatorRepo;

	@Autowired
	private DocumentPDRRepository documentPDRRepository;

	@Autowired
	private CertificatOfficerRepo certificatOfficerRepo;
	
	@Autowired
	private FileRequeistionRepo fileRequeistionRepo;
	
	@Autowired
	private CaseNotesPdrRepo caseNotesPdrRepo;

	@Autowired
	private PdrService pdrService;
	
	@Autowired
	private LegalRepersentativeRepo legalRepersentativeRepo;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AddRecoveryAmmountRepo addRecoveryAmmountRepo;

	@GetMapping("/Hello")
	public String HelloApi() {
		return "Hello Api.";
	}

	@PostMapping("/findemail")
	public ResponseEntity<?> findMobileNumber(@RequestBody Map<String, String> request) {
		try {
			String email = request.get("email"); // Extract phone number from the request body
			UserEntity entity = repository.findByEmail(email);

			if (entity != null) {
				// Return a JSON response

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Try another email");

				// return ResponseEntity.ok(Map.of("message", "Try another email"));
			} else {
				// Return a JSON response
				return ResponseEntity.ok(Map.of("message", "email number is available"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/fileRequiestion")
	public ResponseEntity<?> submitObsjection(@RequestPart("requiestion") String requiestion,
			@RequestPart(value = "files", required = false) MultipartFile[] files,
			@RequestPart(value = "documentTypes", required = false) String documentTypes,
			@RequestPart("username") String username) {
		try {
			// Debugging the received inputs
			System.out.println("Received objection JSON: " + requiestion);
			System.out.println("Received username: " + username);
			System.out.println("Number of files: " + (files != null ? files.length : 0));

			// Convert the JSON string to ObjectionEntity
			ObjectMapper objectMapper = new ObjectMapper();
			FileRequeistionVo vo = objectMapper.readValue(requiestion, FileRequeistionVo.class);

			List<CertificateDebator> debatorlist = new ArrayList<CertificateDebator>();

			for (DebatorVo debatorVo : vo.getDebatorVos()) {
				CertificateDebator debator = new CertificateDebator();
				debator.setDebatorName(debatorVo.getDebtorName());
				debator.setAddress(debatorVo.getDebtorAddress());
				debator.setEmail(debatorVo.getDebtorEmail());
				debator.setPhoneNumber(debatorVo.getDebtorPhoneNumber());
				debator.setAddress1(debatorVo.getDebtorAddress1());

				debator.setState(debatorVo.getDebtorState());
				debator.setCity(debatorVo.getDebtorCity());
				debator.setDistrict(debatorVo.getDebtorDistrict());

				debator.setPolicestation(debatorVo.getDebtorpolicestation());
				debator.setCircle(debatorVo.getCircle());
				debator.setFatherNames(debatorVo.getDebtorfatherNames());
				debator.setSubDivision(debatorVo.getDebtorubDivision());

				debator.setPincode(debatorVo.getDebtorPincode());
				debatorlist.add(debator);

			}

			/// end Legal

			List<LegalRepresentative> legalRepersentativelist = new ArrayList<LegalRepresentative>();

			for (LegalRepersentativeVo legalRepersentativeVo : vo.getLegalRepersentativeVo()) {

				LegalRepresentative legalRepersentative = new LegalRepresentative();

				legalRepersentative.setAddress(legalRepersentativeVo.getLegaladdress());
				legalRepersentative.setAddress(legalRepersentativeVo.getLegaladdress1());
				legalRepersentative.setAddress2(legalRepersentativeVo.getLegaladdress2());
				legalRepersentative.setApartmentNumber(legalRepersentativeVo.getLegalapartmentNumber());
				legalRepersentative.setCircle(legalRepersentativeVo.getLegalcircle());
				legalRepersentative.setCity(legalRepersentativeVo.getLegalcity());
				legalRepersentative.setDistrict(legalRepersentativeVo.getLegaldistrict());
				legalRepersentative.setEmail(legalRepersentativeVo.getLegalemail());
				legalRepersentative.setFatherNames(legalRepersentativeVo.getLegalfatherNames());
				legalRepersentative.setLegalName(legalRepersentativeVo.getLegalName());
				legalRepersentative.setPhoneNumber(legalRepersentativeVo.getLegalphoneNumber());
				legalRepersentative.setPincode(legalRepersentativeVo.getLegalpincode());
				legalRepersentative.setPolicestation(legalRepersentativeVo.getLegalpolicestation());
				legalRepersentative.setState(legalRepersentativeVo.getLegalstate());
				legalRepersentative.setSubDivision(legalRepersentativeVo.getLegalsubDivision());
				legalRepersentativelist.add(legalRepersentative);

			}

			/// end Legal

			// Set up Granter
			CertificateGuaranter granter = new CertificateGuaranter();
			granter.setGranterName(vo.getGuarantorName());

			granter.setPolicestation(vo.getGuarantorpolicestation());
			granter.setCircle(vo.getGuarantorcircle());
			granter.setFatherName(vo.getGuarantorfatherNames());
			granter.setSubDivision(vo.getGuarantorsubDivision());
			granter.setAddress(vo.getGuarantorAddress());
			granter.setEmail(vo.getGuarantorEmail());
			granter.setPhoneNumber(vo.getGuarantorPhoneNumber());
			granter.setAddress1(vo.getGuarantorAddress1());
			granter.setState(vo.getGuarantorState());
			granter.setCity(vo.getGuarantorCity());
			granter.setDistrict(vo.getGuarantorDistrict());
			granter.setPincode(vo.getGuarantorPincode());

			// Set up FileRequeistion
			FileRequeistion requisition = new FileRequeistion();
			requisition.setCertificateDebator(debatorlist);
			requisition.setLegalRepresentative(legalRepersentativelist);
			requisition.setCertificateGuaranter(granter);
			requisition.setFinancialYear(vo.getFinancialYear());
			requisition.setReason(vo.getreason());

			requisition.setInterestDueForm(vo.getInterestDueForm());
			requisition.setMissllenousFee(vo.getMissllenousFee());
			requisition.setPaidCourFee(vo.getPaidCourFee());
			requisition.setTotalCourtFee(vo.getTotalCourtFee());
			requisition.setTotalDemand(vo.getTotalDemand());
			requisition.setTotalInterestRate(vo.getTotalInterestRate());
			requisition.setTotalOutstandingAmmount(vo.getTotalOutstandingAmmount());

			requisition.setFinancialYear("2024-25"); // Example - adjust as needed

			UserEntity user = new UserEntity();
			user = userService.getUserById(Long.valueOf(vo.getUserId()));
			requisition.setUserId(user);
			username = String.valueOf(user.getUserId());
			// NewObjection objection = objectionService.savedata(entity);

			// Call the service to handle the objection and files
			String objectionId = pdrService.submitRequisition(requisition, files, username, documentTypes);
			return ResponseEntity.ok(objectionId);

			//

			// Get user from service
//		        UserEntity user = userService.getUserByUsername(username);
//		        requisition.setUserId(user);
//
//			// Call the service to handle the objection and files
//			String objectionId = objectionService.submitObjection(entity, files, username,documentTypes);

			// Respond with the objectionId after submission
			// return ResponseEntity.ok(Collections.singletonMap("objectionId",
			// objectionId));
		} catch (Exception e) {
			e.printStackTrace(); // Log the full exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}
	

	@GetMapping("/getrequiestion")
	public ResponseEntity<?> getObjections(@RequestParam String district) {
		try {
			List<FileRequeistion> fileRequeistions = pdrService.findpending(district);

			if (!fileRequeistions.isEmpty()) {
				List<FileRequeistionDTO> dtoList = fileRequeistions.stream().map(req -> {
					FileRequeistionDTO dto = new FileRequeistionDTO();
					try {
						FileRequeistion newObjection = (FileRequeistion) pdrService.findbyId(dto.getRequeistionId());
						CertificatOfficer certificatOfficer = certificatOfficerRepo.findByFileRequeistion(newObjection);
						dto.setCaseId(certificatOfficer.getCertOfficerId());
						
						
						List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(newObjection);
						CertificateDebator certificateDebator=certificateDebatorlist.get(0);                 
						dto.setDebatorName(certificateDebator.getDebatorName());
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					dto.setRequeistionId(req.getRequeistionId());

					dto.setTotalOutstandingAmmount(req.getTotalOutstandingAmmount());
					dto.setTotalInterestRate(req.getTotalInterestRate());
					dto.setInterestDueForm(req.getInterestDueForm());
					dto.setTotalCourtFee(req.getTotalCourtFee());
					dto.setMissllenousFee(req.getMissllenousFee());
					dto.setPaidCourFee(req.getPaidCourFee());
					dto.setTotalDemand(req.getTotalDemand());
					dto.setFinancialYear(req.getFinancialYear());
					dto.setDistrictName(req.getDistrictName());
					dto.setCurrentDate(req.getCurrentDate());
					dto.setUpdateDate(req.getUpdateDate());
					dto.setStatus(req.getStatus());

					dto.setReason(req.getReason());
					if (req.getUserId() != null) {
						dto.setUserName(req.getUserId().getFullName()); // or whatever field you want
					}
					return dto;
				}).collect(Collectors.toList());

				ReqiestionResponnse response = new ReqiestionResponnse();

				response.setListfileRequeistion(dtoList);
				response.setStatus("200");
				response.setMsg("Success");

				return ResponseEntity.ok(response);
			}

			return ResponseEntity.status(HttpStatus.ACCEPTED).body("No requisitions found for district: " + district);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}
	}

	@GetMapping("/viewrequiestion")
	public ResponseEntity<?> getAllObjections(@RequestParam String recuisition) {
		try {
			FileRequeistion newObjection = (FileRequeistion) pdrService.findbyId(recuisition);

			if (newObjection == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("No data found for requisition ID: " + recuisition);
			}

			// FileRequeistionDTO
			FileRequeistionDTO dto = new FileRequeistionDTO();
			dto.setRequeistionId(newObjection.getRequeistionId());

			try {
				CertificatOfficer certificatOfficer = certificatOfficerRepo.findByFileRequeistion(newObjection);

				dto.setCaseId(certificatOfficer.getCertOfficerId());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			dto.setTotalOutstandingAmmount(newObjection.getTotalOutstandingAmmount());
			dto.setTotalInterestRate(newObjection.getTotalInterestRate());
			dto.setInterestDueForm(newObjection.getInterestDueForm());
			dto.setTotalCourtFee(newObjection.getTotalCourtFee());
			dto.setMissllenousFee(newObjection.getMissllenousFee());
			dto.setPaidCourFee(newObjection.getPaidCourFee());
			dto.setTotalDemand(newObjection.getTotalDemand());
			dto.setFinancialYear(newObjection.getFinancialYear());
			dto.setDistrictName(newObjection.getDistrictName());
			dto.setCurrentDate(newObjection.getCurrentDate());
			dto.setUpdateDate(newObjection.getUpdateDate());
			dto.setStatus(newObjection.getStatus());
			dto.setReason(newObjection.getReason());

			if (newObjection.getUserId() != null) {
				dto.setUserName(newObjection.getUserId().getFullName());
			}

			// FileRequeistionVo mapping (Guarantor + Debtors)
			FileRequeistionVo vo = new FileRequeistionVo();

			// Guarantor
			CertificateGuaranter grantor = newObjection.getCertificateGuaranter();
			if (grantor != null) {
				vo.setGuarantorName(grantor.getGranterName());
				vo.setGuarantorAddress(grantor.getAddress());
				vo.setGuarantorAddress1(grantor.getAddress1());
				vo.setGuarantorAddress2(grantor.getAddress2());
				vo.setGuarantorState(grantor.getState());
				vo.setGuarantorCity(grantor.getCity());
				vo.setGuarantorDistrict(grantor.getDistrict());
				vo.setGuarantorPincode(grantor.getPincode());
				vo.setGuarantorPhoneNumber(grantor.getPhoneNumber());
				// vo.setGuarantorStatePhoneNumber(g.get);
				vo.setGuarantorEmail(grantor.getEmail());
				vo.setGuarantorfatherNames(grantor.getFatherName());
				vo.setGuarantorcircle(grantor.getCircle());
				vo.setGuarantorpolicestation(grantor.getPolicestation());
				vo.setCreatedDate(grantor.getCreatedDate());
				vo.setModifiedDate(grantor.getModifiedDate());
			}

			// Debtors
			List<DebatorVo> debatorVoList = new ArrayList<>();
			if (newObjection.getCertificateDebator() != null) {
				for (CertificateDebator debator : newObjection.getCertificateDebator()) {
					DebatorVo dvo = new DebatorVo();
					dvo.setDebtorName(debator.getDebatorName());
					dvo.setDebtorAddress(debator.getAddress());
					dvo.setDebtorAddress1(debator.getAddress1());
					dvo.setDebtorAddress2(debator.getAddress2());
					dvo.setDebtorState(debator.getState());
					dvo.setDebtorCity(debator.getCity());
					dvo.setDebtorDistrict(debator.getDistrict());
					dvo.setDebtorPincode(debator.getPincode());
					dvo.setDebtorPhoneNumber(debator.getPhoneNumber());
					dvo.setDebtorStatePhoneNumber(debator.getState()); // Check if this is correct
					dvo.setDebtorEmail(debator.getEmail());
					dvo.setDebtorfatherNames(debator.getFatherNames());
					dvo.setDebtorubDivision(debator.getSubDivision());
					dvo.setDebtorcircle(debator.getCircle());
					dvo.setDebtorpolicestation(debator.getPolicestation());
					dvo.setCircle(debator.getCircle());
					
					debatorVoList.add(dvo);
				}
			}

			vo.setDebatorVos(debatorVoList);

			// Set common financial fields in vo too
			vo.setTotalOutstandingAmmount(newObjection.getTotalOutstandingAmmount());
			vo.setTotalInterestRate(newObjection.getTotalInterestRate());
			vo.setInterestDueForm(newObjection.getInterestDueForm());
			vo.setTotalCourtFee(newObjection.getTotalCourtFee());
			vo.setMissllenousFee(newObjection.getMissllenousFee());
			vo.setPaidCourFee(newObjection.getPaidCourFee());
			vo.setTotalDemand(newObjection.getTotalDemand());
			vo.setFinancialYear(newObjection.getFinancialYear());
			
			try {
			List<LegalRepersentativeVo> legalRepersentativelistvo = new ArrayList<>();

			
			List<LegalRepresentative> legalRepersentativelist = legalRepersentativeRepo.findByFileRequeistion(newObjection);

			for (LegalRepresentative legalRepersentativeVo : legalRepersentativelist) {

				LegalRepersentativeVo legalRepersentative = new LegalRepersentativeVo();

				legalRepersentative.setLegaladdress(legalRepersentativeVo.getAddress());
				legalRepersentative.setLegaladdress1(legalRepersentativeVo.getAddress1());
				legalRepersentative.setLegaladdress2(legalRepersentativeVo.getAddress2());
				legalRepersentative.setLegalapartmentNumber(legalRepersentativeVo.getApartmentNumber());
				legalRepersentative.setLegalcircle(legalRepersentativeVo.getCircle());
				legalRepersentative.setLegalcity(legalRepersentativeVo.getCity());
				legalRepersentative.setLegaldistrict(legalRepersentativeVo.getDistrict());
				legalRepersentative.setLegalemail(legalRepersentativeVo.getEmail());
				legalRepersentative.setLegalfatherNames(legalRepersentativeVo.getFatherNames());
				legalRepersentative.setLegalName(legalRepersentativeVo.getLegalName());
				legalRepersentative.setLegalphoneNumber(legalRepersentativeVo.getPhoneNumber());
				legalRepersentative.setLegalpincode(legalRepersentativeVo.getPincode());
				legalRepersentative.setLegalpolicestation(legalRepersentativeVo.getPolicestation());
				legalRepersentative.setLegalstate(legalRepersentativeVo.getState());
				legalRepersentative.setLegalsubDivision(legalRepersentativeVo.getSubDivision());
				legalRepersentativelistvo.add(legalRepersentative);

			}
			
			vo.setLegalRepersentativeVo(legalRepersentativelistvo);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			// Response mapping
			ReqiestionResponnse response = new ReqiestionResponnse();

			// Map DocumentEntityPdr to DocumentDTO
			List<DocumentDTO> documentDTOs = newObjection.getDocuments().stream().map(doc -> {
				DocumentDTO docDto = new DocumentDTO();
				docDto.setId(doc.getId());
				docDto.setDocumentName(doc.getDocumentName()); // Assuming fileName holds documentName
				docDto.setFilePath(doc.getFilePath()); // Assuming this exists in DocumentEntityPdr
				docDto.setFileType(doc.getFileType()); // Assuming this exists
				docDto.setFileSize(doc.getFileSize()); // Assuming this exists
				docDto.setDocumentType(recuisition); // If this is meant to be a label or ID
				return docDto;
			}).collect(Collectors.toList());

			response.setDocumentEntityPdrs(documentDTOs);

			response.setFileRequeistion(dto);
			response.setEntity(newObjection.getUserId());
			// response.setDocumentEntityPdrs(newObjection.getDocuments());
			response.setFileRequeistionVo(vo);
			response.setStatus("200");
			response.setMsg("Success");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving requisition: " + e.getMessage());
		}
	}

	// CertificateOfficer

	@PostMapping("officersAdmission")
	public ResponseEntity<?> Admission(@RequestBody OfficerStatusVo statusvo) {
		// TODO: process POST request

		try {

			String status = pdrService.upadateStatus(statusvo);
			if (!status.equals("")) {
				return ResponseEntity.ok(status);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error retrieving objections: " + status);
			}

			// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error
			// retrieving objections: ");

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
			// e.printStackTrace();
		}
		// return null;
	}

	@PostMapping("caseProcessdetails")
	public ResponseEntity<?> caseprocedetailssace(@RequestBody CaseNotes casenotes) {

		try {
			System.out.println(casenotes.getSelectForm());
			if (casenotes.getAction() == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");

			}
			FileRequeistion admission = pdrService.savecaseDetails(casenotes);
			if (admission == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" some issue casenotes.");
			}

			return ResponseEntity.ok("save success");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("issue casenotes: " + e.getMessage());
		}

	}

	@GetMapping("/getrequiestionAdmit")
	public ResponseEntity<?> getObjectionsAdmit(@RequestParam String district) {
		try {
			List<FileRequeistion> fileRequeistions = pdrService.findAdmit(district);

			if (!fileRequeistions.isEmpty()) {
				List<FileRequeistionDTO> dtoList = fileRequeistions.stream().map(req -> {
					FileRequeistionDTO dto = new FileRequeistionDTO();

					dto.setRequeistionId(req.getRequeistionId());

					// FileRequeistionDTO dto = new FileRequeistionDTO();
					try {
						FileRequeistion newObjection = (FileRequeistion) pdrService.findbyId(dto.getRequeistionId());
						CertificatOfficer certificatOfficer = certificatOfficerRepo.findByFileRequeistion(newObjection);
						
						List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(newObjection);
						CertificateDebator certificateDebator=certificateDebatorlist.get(0);                 
						dto.setDebatorName(certificateDebator.getDebatorName());
						
						dto.setCaseId(certificatOfficer.getCertOfficerId());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					dto.setTotalOutstandingAmmount(req.getTotalOutstandingAmmount());
					dto.setTotalInterestRate(req.getTotalInterestRate());
					dto.setInterestDueForm(req.getInterestDueForm());
					dto.setTotalCourtFee(req.getTotalCourtFee());
					dto.setMissllenousFee(req.getMissllenousFee());
					dto.setPaidCourFee(req.getPaidCourFee());
					dto.setTotalDemand(req.getTotalDemand());
					dto.setFinancialYear(req.getFinancialYear());
					dto.setDistrictName(req.getDistrictName());
					dto.setCurrentDate(req.getCurrentDate());
					dto.setUpdateDate(req.getUpdateDate());
					dto.setStatus(req.getStatus());
					dto.setReason(req.getReason());
					if (req.getUserId() != null) {
						dto.setUserName(req.getUserId().getFullName()); // or whatever field you want
					}
					return dto;
				}).collect(Collectors.toList());

				ReqiestionResponnse response = new ReqiestionResponnse();

				response.setListfileRequeistion(dtoList);

				response.setStatus("200");
				response.setMsg("Success");

				return ResponseEntity.ok(response);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No requisitions found for district: " + district);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}
	}

	@PostMapping("/fileShow")
	public ResponseEntity<?> qrShow(@RequestParam String id) {
		String res = "";
		try {

			DocumentEntityPdr documentEntity = documentPDRRepository.findById(Long.valueOf(id)).get();
			// Hardcoded for testing

			String filePath = documentEntity.getFilePath();
			// Ensure that the file exists
			Path imagePath = Paths.get(filePath);
			if (!Files.exists(imagePath)) {
				return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
			}

			// Load the file into a byte array
			byte[] fileBytes = Files.readAllBytes(imagePath);

			String base64String = Base64.getEncoder().encodeToString(fileBytes);

			if (base64String != null && !base64String.isEmpty()) {
				return new ResponseEntity<>(base64String, HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<>("File encoding failed", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PostMapping("addcourt")
	public ResponseEntity<?> addcourt(@RequestBody CourtReq courtReq) {

		StatusRes response = new StatusRes();
		try {

			String res = pdrService.addCourt(courtReq);
			if (res.equals("save"))

			{
				response.setMessage(res);
				response.setStatus("200");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

			}
			response.setMessage(res);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}

	@PostMapping("addcourtList")
	public ResponseEntity<?> addcourtlist(@RequestParam Long userId) {
		StatusRes response = new StatusRes();

		try {

			List<CourtReq> reslist = pdrService.addCourtlistShow(userId);
			if (!reslist.isEmpty()) {

				return new ResponseEntity<>(reslist, HttpStatus.ACCEPTED);

			}
			response.setMessage("not Found");
			response.setStatus("400");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Internal Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}

	@PostMapping("noticeForm")
	public ResponseEntity<?> addcourtlist(@RequestParam String selectForm, @RequestParam String reqId) {
		StatusRes res = new StatusRes();
		try {

			if (selectForm != null && reqId != null) {

				res = pdrService.noticeGenerate(selectForm, reqId);

				if (res == null) {
					res.setMessage("form  will  not be sumbitted");
					res.setStatus("400");
					return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

				}
				return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

			}
			res.setMessage("form  will  not be sumbitted");
			res.setStatus("400");
			return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("INTERNAL_SERVER_ERROR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}

	/// Case Transfer

	@PostMapping("caseTransfer")
	public ResponseEntity<?> caseTransfer(@RequestParam List<String> reqId, @RequestParam String nouserId) {
		StatusRes response = new StatusRes();

		try {
			String res = pdrService.caseTransfer(reqId, nouserId);
			if ("save".equals(res)) {
				response.setMessage("Case Transfer Successful");
				response.setStatus("200");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			}

			response.setMessage(res);
			response.setStatus("400");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Server Error");
			response.setStatus("500");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("caseTranferFileshow")
	public ResponseEntity<?> getTranferFileshow(@RequestParam String userId,@RequestParam String role) {

		try {
			List<FileRequeistion> fileRequeistions  =new ArrayList<>();
			if(role.equals("NO_CERTIFICATE_OFFICER"))
			{
				fileRequeistions= pdrService.findpendingNom(userId);

			}
			else if(role.equals("CERTIFICATE_OFFICER"))
			{
				fileRequeistions=pdrService.findAllByuserIdcaseTranfer(userId);
			}
			if (!fileRequeistions.isEmpty()) {
				List<FileRequeistionDTO> dtoList = fileRequeistions.stream().map(req -> {
					FileRequeistionDTO dto = new FileRequeistionDTO();
					dto.setRequeistionId(req.getRequeistionId());
					dto.setTotalOutstandingAmmount(req.getTotalOutstandingAmmount());
					dto.setTotalInterestRate(req.getTotalInterestRate());
					dto.setInterestDueForm(req.getInterestDueForm());
					dto.setTotalCourtFee(req.getTotalCourtFee());
					dto.setMissllenousFee(req.getMissllenousFee());
					dto.setPaidCourFee(req.getPaidCourFee());
					dto.setTotalDemand(req.getTotalDemand());
					dto.setFinancialYear(req.getFinancialYear());
					dto.setDistrictName(req.getDistrictName());
					dto.setCurrentDate(req.getCurrentDate());
					dto.setUpdateDate(req.getUpdateDate());
					dto.setStatus(req.getStatus());
					dto.setReason(req.getReason());
					
					try {
						CertificatOfficer  certificatOfficer=certificatOfficerRepo.findByFileRequeistion(req);
						dto.setCaseId(certificatOfficer.getCertOfficerId());
						
						List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(req);
						CertificateDebator certificateDebator=certificateDebatorlist.get(0);                 
						dto.setDebatorName(certificateDebator.getDebatorName());
						dto.setSector(certificatOfficer.getUserId().getSector());
						
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					if (req.getUserId() != null) {
						dto.setUserName(req.getUserId().getFullName()); // or whatever field you want
					}
					return dto;
				}).collect(Collectors.toList());

				ReqiestionResponnse response = new ReqiestionResponnse();

				response.setListfileRequeistion(dtoList);
				response.setStatus("200");
				response.setMsg("Success");

				return ResponseEntity.ok(response);
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No requisitions found for district: " + userId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving objections: " + e.getMessage());
		}

	}

	@GetMapping("/getrequiestionById")
	public ResponseEntity<?> getrequiestionById(@RequestParam String userId) {
		ReqiestionResponnse response = new ReqiestionResponnse();

		try {
			List<FileRequeistion> fileRequeistions = pdrService.findAllByuserId(userId);

			if (!fileRequeistions.isEmpty()) {
				List<FileRequeistionDTO> dtoList = fileRequeistions.stream().map(req -> {
					FileRequeistionDTO dto = new FileRequeistionDTO();
					dto.setRequeistionId(req.getRequeistionId());
					
					try {
						dto.setTotalOutstandingAmmount(req.getTotalOutstandingAmmount());
						FileRequeistion fileRequeistion=fileRequeistionRepo.findByRequeistionId(dto.getRequeistionId()).get();
						List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(fileRequeistion);
						CertificateDebator certificateDebator=certificateDebatorlist.get(0);
						dto.setDebatorName(certificateDebator.getDebatorName());

						
					}catch (Exception e) {
						// TODO: handle exception
					}
					
					dto.setTotalOutstandingAmmount(req.getTotalOutstandingAmmount());
					dto.setTotalInterestRate(req.getTotalInterestRate());
					dto.setInterestDueForm(req.getInterestDueForm());
					dto.setTotalCourtFee(req.getTotalCourtFee());
					dto.setMissllenousFee(req.getMissllenousFee());
					dto.setPaidCourFee(req.getPaidCourFee());
					dto.setTotalDemand(req.getTotalDemand());
					dto.setFinancialYear(req.getFinancialYear());
					dto.setDistrictName(req.getDistrictName());
					dto.setCurrentDate(req.getCurrentDate());
					dto.setUpdateDate(req.getUpdateDate());
					dto.setStatus(req.getStatus());
					dto.setReason(req.getReason());
					if (req.getUserId() != null) {
						dto.setUserName(req.getUserId().getFullName()); // or whatever field you want
					}
					return dto;
				}).collect(Collectors.toList());

				response.setListfileRequeistion(dtoList);
				response.setStatus("200");
				response.setMsg("Success");

				return ResponseEntity.ok(response);
			}
			// response.setListfileRequeistion();
			response.setStatus("400");
			response.setMsg("Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)

					.body("No requisitions found for district: " + response);
		} catch (Exception e) {
			response.setMsg("Not Found");
			response.setStatus("400");

			return ResponseEntity.status(HttpStatus.NOT_FOUND)

					.body("No requisitions found for district: " + response);
		}

	}

	@PostMapping("/findMobileNumberOREmailholder")
	public ResponseEntity<?> findMobileNumberHolder(@RequestBody Map<String, String> request) {
		try {
			String phoneNumber = request.get("phoneNumber");

			String email = request.get("email");
			CertificateDebator entity = new CertificateDebator();
			if (request.get("phoneNumber") == phoneNumber && phoneNumber != null) {
				entity = certificatDebatorRepo.findByPhoneNumber(phoneNumber);

				if (entity != null) {
					return ResponseEntity.ok(Map.of("message", "Try another mobile"));

				} else {
					// Return a JSON response
					return ResponseEntity.ok(Map.of("message", "Phone number is available"));
				}

			}

			else if (email == request.get("email")) {
				entity = certificatDebatorRepo.findByEmail(email);
				if (entity != null) {
					return ResponseEntity.ok(Map.of("message", "Try another email"));

				} else {
					// Return a JSON response
					return ResponseEntity.ok(Map.of("message", "email  is available"));
				}
			}

			// if (entity != null) {
//	            // Return a JSON response
//	            return ResponseEntity.ok(Map.of("message", "Try another mobile"));
//	        } else {
//	            // Return a JSON response
//	            return ResponseEntity.ok(Map.of("message", "Phone number is available"));
//	        }

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return null;
	}

	@PostMapping("/debator-casestatus")
	public ResponseEntity<?> findMobileNumberHolder(@RequestParam String userId) {
		StatusResponse<FileRequeistionDTO> res = new StatusResponse<>();

		try {
			CertificateDebator certificateDebator = certificatDebatorRepo.findByDebatorIdNative(Long.valueOf(userId));
			// certificatDebatorRepo.findByPhoneNumber(userId);

			if (certificateDebator != null && certificateDebator.getRequeistion() != null) {
				FileRequeistion requeistion = certificateDebator.getRequeistion();
				FileRequeistion fileRequeistion = pdrService.findBydebatorId(requeistion.getRequeistionId());

				if (fileRequeistion != null && fileRequeistion.getRequeistionId() != null) {
					FileRequeistionDTO dto = new FileRequeistionDTO();
					BeanUtils.copyProperties(fileRequeistion, dto); // ✅ simple one-liner mapping

					// Set userName manually since it's a nested object
					if (fileRequeistion.getUserId() != null) {
						dto.setUserName(fileRequeistion.getUserId().getUserName());
					}

					res.setOption(dto);
					res.setMessage("success");
					res.setStatus("200");
					return ResponseEntity.ok(res);
				}
			}

			res.setMessage("User not found");
			res.setStatus("400");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

		} catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Internal Server Error");
			res.setStatus("500");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}

	}

	@PostMapping("findtimeslot")
	public ResponseEntity<?> findSlottime(@RequestParam String date) {
		try {
			List<String> slot = pdrService.findSlotTime(date);

			if (!slot.isEmpty()) {
				return ResponseEntity.ok(slot);

			}
			StatusRes res = new StatusRes();
			res.setMessage("no time slot Available");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	// causeList

	@PostMapping("/findcauselist")
	public ResponseEntity<StatusResponse<List<CauseListResponse>>> findCauseList(
			@RequestBody Map<String, String> request) {
		StatusResponse<List<CauseListResponse>> response = new StatusResponse<>();

		String caseId = request.get("caseId");
		String caseDate = request.get("caseDate");

//	    // Validate input
//	    if (caseId == null || caseDate == null || caseId.isBlank() || caseDate.isBlank()) {
//	        response.setMessage("Invalid request: caseId and caseDate are required.");
//	        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	    }

		try {
			List<CertificatOfficer> officers = pdrService.findByReqId(caseId, caseDate);
			if (officers.isEmpty()) {
				response.setMessage("No records found");
				response.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			List<CauseListResponse> causeList = officers.stream().map(officer -> {
				CauseListResponse res = new CauseListResponse();
				
				
				res.setAction(officer.getAction());
				res.setCaseId(officer.getCertOfficerId());
				res.setDemandAmount(officer.getFileRequeistion().getTotalDemand());
				res.setHearingDate(officer.getHearingDate());
				res.setHearingtime(officer.getHearingTime());
				res.setHolderName(officer.getUserId().getFullName());
				FileRequeistion fileRequeistion=fileRequeistionRepo.findByRequeistionId(officer.getFileRequeistion().getRequeistionId()).get();
				List<CertificateDebator> certificateDebatorlist=certificatDebatorRepo.findByRequeistion(fileRequeistion);
				CertificateDebator certificateDebator=certificateDebatorlist.get(0);                 
				res.setDebtorName(certificateDebator.getDebatorName());
				res.setFilingDate(fileRequeistion.getCurrentDate());
				res.setSector(officer.getUserId().getSector());
				
				return res;
			}).collect(Collectors.toList());

			response.setOption(causeList);
			response.setMessage("Success");
			response.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Server Error");
			response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/findcauselistBydistrict")
	public ResponseEntity<StatusResponse<List<CauseListResponse>>> findCauseList(@RequestParam String district) {
		StatusResponse<List<CauseListResponse>> response = new StatusResponse<>();

//	    // Validate input
//	    if (caseId == null || caseDate == null || caseId.isBlank() || caseDate.isBlank()) {
//	        response.setMessage("Invalid request: caseId and caseDate are required.");
//	        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//	    }

		try {
			List<CertificatOfficer> officers = pdrService.findAllCause(district);
			if (officers.isEmpty() || officers == null) {
				response.setMessage("No records found");
				response.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}

			List<CauseListResponse> causeList = officers.stream().map(officer -> {
				CauseListResponse res = new CauseListResponse();
				List<CertificateDebator> certificateDebator=certificatDebatorRepo.findByRequeistion(officer.getFileRequeistion());
				res.setSector(officer.getUserId().getSector());
				res.setFilingDate(officer.getFileRequeistion().getCurrentDate());
				
			    res.setDebtorName(certificateDebator.get(0).getDebatorName());
				res.setAction(officer.getAction());
				res.setCaseId(officer.getCertOfficerId());
				res.setDemandAmount(officer.getFileRequeistion().getTotalDemand());
				res.setHearingDate(officer.getHearingDate());
				res.setHearingtime(officer.getHearingTime());
				res.setHolderName(officer.getUserId().getFullName());
				return res;
			}).collect(Collectors.toList());

			response.setOption(causeList);
			response.setMessage("Success");
			response.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Internal Server Error");
			response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/causeupadate")
	public ResponseEntity<?> causeupadate(@Valid @RequestBody CauseVo causeVo, BindingResult result) {
		StatusRes res = new StatusRes();
		if (result.hasErrors()) {
			// Return first error message (you can customize this as needed)
			return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
		}
		try {
			String response = pdrService.upadateCauseStatus(causeVo);

			if (response.equals("update")) {
				res.setMessage(response);
				res.setStatus("200");
				return ResponseEntity.status(HttpStatus.OK).body(res);

			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal Server erroe");

		}

		// return null;

	}

	@PostMapping("/caseStatusReq")
	public ResponseEntity<StatusResponse<List<ReqrusitionStatus>>> caseStatusReq(@RequestParam String userId) {
		StatusResponse<List<ReqrusitionStatus>> response = new StatusResponse<>();

		try {
			List<ReqrusitionStatus> requisitionStatusList = pdrService.getcaseStatus(userId);

			if (requisitionStatusList == null || requisitionStatusList.isEmpty()) {
				response.setOption(Collections.emptyList());
				response.setMessage("No records found");
				response.setStatus(String.valueOf(HttpStatus.OK.value()));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			response.setOption(requisitionStatusList);
			response.setMessage("Success");
			response.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.setMessage("An error occurred.");
			response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/draftSave")
	public ResponseEntity<?> draftSave(@RequestParam String draft, @RequestParam String caseId) {
		try {
			StatusRes res = pdrService.addDraft(draft, caseId);

			if (res.getMessage().equals("save")) {
				res.setStatus(String.valueOf(HttpStatus.OK.value()));
				return ResponseEntity.ok(res);

			}
			res.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@PostMapping("/draftFinalSave")
	public ResponseEntity<?> draftFinalSave(@RequestParam String draft, @RequestParam String caseId) {
		try {
			StatusRes res = pdrService.saveDraft(draft, caseId);

			if (res.getMessage().equals("save")) {
				res.setStatus(String.valueOf(HttpStatus.OK.value()));
				return ResponseEntity.ok(res);

			}
			res.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	@PostMapping("/draftfind")
	public ResponseEntity<?> draftSave(@RequestParam String caseId) {
		try {
			DraftSaveCaseProceeding res = pdrService.FindDraft(caseId);

			StatusResponse<DraftSaveCaseProceeding> response = new StatusResponse<DraftSaveCaseProceeding>();
			if (res != null) {
				response.setStatus(String.valueOf(HttpStatus.OK.value()));
				response.setOption(res);
				return ResponseEntity.ok(response);

			}
			response.setStatus("404");
			response.setMessage("not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("caseRecord")
	public ResponseEntity<?> caseRecord(@RequestParam String userId) {
		StatusResponse<List<CaseRecodeRes>> response = new StatusResponse<>();

		try {
			List<CaseRecodeRes> requisitionStatusList = pdrService.getcaseRecord(userId);

			if (requisitionStatusList == null || requisitionStatusList.isEmpty()) {
				response.setOption(Collections.emptyList());
				response.setMessage("No records found");
				response.setStatus(String.valueOf(HttpStatus.OK.value()));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			response.setOption(requisitionStatusList);
			response.setMessage("Success");
			response.setStatus(String.valueOf(HttpStatus.OK.value()));
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			response.setMessage("An error occurred.");
			response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/caseRecordFilter")
	public ResponseEntity<StatusResponse<List<CaseRecodeRes>>> caseRecordList(@RequestBody Map<String, String> request) {
        StatusResponse<List<CaseRecodeRes>> response = new StatusResponse<>();

	    try {
	        String sector = request.get("sector");
	        String bank = request.get("bank");
	        String department = request.get("department");
	        String branchCode = request.get("branchCode");

	        List<CaseRecodeRes> requisitionStatusList = pdrService.getcaseRecordFilter(sector, bank, department, branchCode);

	        if(requisitionStatusList!=null)
	        {
	        response.setStatus("SUCCESS");
			response.setOption(requisitionStatusList);
	        response.setMessage("Filtered records retrieved successfully.");

	        return ResponseEntity.ok(response);

	        }
	    } catch (Exception e) {
	    	response.setStatus("ERROR");
	    	response.setMessage("Something went wrong: " + e.getMessage());
	       // errorResponse.setData(Collections.emptyList());

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	    response.setStatus("404");
	  //  response.setMessage("Something went wrong: " + e.getMessage());
       // errorResponse.setData(Collections.emptyList());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	
	//BOR
	
	@PostMapping("addcommisionary")
	public ResponseEntity<?> addcommisionary(@RequestBody CommisionaryReq commisionaryReq ) {

		StatusRes response = new StatusRes();
		try {

			String res = pdrService.addcommisionary(commisionaryReq);
			if (res.equals("save"))

			{
				response.setMessage(res);
				response.setStatus("200");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

			}
			response.setMessage(res);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}
	
	
	@PostMapping("showcommisionaryList")
	public ResponseEntity<?> showcommisionaryList(@RequestParam Long userId) {
		StatusRes response = new StatusRes();

		try {

			List<CommisionaryReq> reslist = pdrService.showcommisionaryList(userId);
			if (!reslist.isEmpty()) {

				return new ResponseEntity<>(reslist, HttpStatus.ACCEPTED);

			}
			response.setMessage("not Found");
			response.setStatus("400");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Internal Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}
	//add  Certificate Officer
	@PostMapping("addCertificateOfficer")
	public ResponseEntity<?> addCertificateOfficer(@RequestBody CourtReq courtReq) {

		StatusRes response = new StatusRes();
		try {

			String res = pdrService.addCourt(courtReq);
			if (res.equals("save"))

			{
				response.setMessage(res);
				response.setStatus("200");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

			}
			response.setMessage(res);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}
	
	@PostMapping("certificateOfficerList")
	public ResponseEntity<?> certificateOfficerList(@RequestParam Long userId) {
		StatusRes response = new StatusRes();

		try {

			List<CourtReq> reslist = pdrService.addCourtlistShow(userId);
			if (!reslist.isEmpty()) {

				return new ResponseEntity<>(reslist, HttpStatus.ACCEPTED);

			}
			response.setMessage("not Found");
			response.setStatus("400");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Internal Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// return null;
	}
	
	@PostMapping("courtFee")
	    public @ResponseBody CourtFeeSlab getFee(@RequestParam double amount) {
		 CourtFeeSlab slabamount= courtFeeService.getFeeForAmount(amount).get();
		 try {
		 if(slabamount!=null)
		 {
			 return slabamount;
		 }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			 return null;
		}
		 return null;
		 
	    }
	
	

	@PostMapping("getNotes")
	public ResponseEntity<?> getNotes(@RequestParam String caseId) {
	    StatusResponse<String> response = new StatusResponse<>();

	    try {
	        CaseNotesPdr res = caseNotesPdrRepo.findByCaseId(caseId);

	        if (res != null && res.getUserId() != null) {
	            response.setMessage(res.getCaseNotes());
	            response.setStatus("200");
	            return ResponseEntity.ok(response); 
	        }

	        response.setMessage("Case notes not found");
	        response.setStatus("404");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	    } catch (Exception e) {
	        e.printStackTrace();

	        response.setMessage("Internal server error");
	        response.setStatus("500");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	
	@PostMapping("fetchUserOnRecovery")
	public ResponseEntity<?> fetchUserOnRecovery(@RequestParam String district) {
	    try {
	        List<UserEntity> entitiesList = repository.findUsersByDistrictAndRole(district);
	        return ResponseEntity.ok(entitiesList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch users.");
	    }
	}

	
	@PostMapping("recoveryammount")
	public ResponseEntity<?> recoveryammount(@RequestBody RecoveryAmountVo recoveryAmountVo) {
		StatusResponse<AddRecoveryAmmount> response = new StatusResponse<>();
		try {
			AddRecoveryAmmount addRecoveryAmmount = pdrService.addrecoveryAmount(recoveryAmountVo);
			if (addRecoveryAmmount.getRecoveryId() != null) {
				response.setMessage("save Data");
				response.setStatus("200");
				response.setOption(addRecoveryAmmount);
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			response.setMessage("not Data");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		response.setMessage("bad credential");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("showRecoveryammount")
	public ResponseEntity<?> showRecoveryammount(@RequestParam String caseId) {
		StatusResponse<AddRecoveryAmmount> response = new StatusResponse<>();
		try {
			AddRecoveryAmmount addRecoveryAmmount = addRecoveryAmmountRepo.findBycaseId(caseId);
			if (addRecoveryAmmount.getRecoveryId() != null) {
				response.setMessage("save Data");
				response.setStatus("200");
				response.setOption(addRecoveryAmmount);
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			response.setMessage("not Data");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		response.setMessage("bad credential");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
	private final String FILE_STORAGE_PATH = "D:/Admin/PDR/User/%s/"; 


	@PostMapping("/filecaseproceding")
	public ResponseEntity<?> submitObjectionFile(
	        @RequestParam("file") MultipartFile file,
	        @RequestParam("caseId") String caseId,
	        @RequestParam("documentName") String documentName) {

	    try {
	        Optional<CertificatOfficer> optionalOfficer = certificatOfficerRepo.findByCertOfficerId(caseId);
	        if (!optionalOfficer.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Case not found: " + caseId);
	        }

	        CertificatOfficer officer = optionalOfficer.get();
	        String userStoragePath = String.format(FILE_STORAGE_PATH, officer.getUserId().getUserId());

	        File userDirectory = new File(userStoragePath);
	        if (!userDirectory.exists()) {
	            userDirectory.mkdirs();
	        }

	        String safeFileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
	        Path fullPath = Paths.get(userStoragePath, safeFileName);
	        file.transferTo(fullPath.toFile());

	        DocumentEntityPdr document = new DocumentEntityPdr();
	        document.setDocumentName(documentName); // from request
	        document.setFilePath(fullPath.toString());
	        document.setFileType(file.getContentType());
	        document.setFileSize(file.getSize());
	        document.setUploadedDate(new Date());
	        document.setFileRequeistion(officer.getFileRequeistion());

	        documentPDRRepository.save(document);

	        return ResponseEntity.ok("File uploaded successfully!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error uploading file: " + e.getMessage());
	    }
	}
	
@GetMapping("/allDistric")
  public  ResponseEntity<?> getalldistic(@RequestParam String distrct  ) {
	System.out.println("district===>"+distrct);
	try {
		List<CommisionaryMap>	result=pdrService.getalldistic(distrct);
		System.out.println("district===>"+result);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("notfound");

}}

@GetMapping("/policestation")
public ResponseEntity<?> getpolice(@RequestParam Long comId){
	try {
		List<?> result1 = pdrService.getpolice(comId);
		   System.out.println("district===>"+result1);

		return new ResponseEntity<>(result1,HttpStatus.ACCEPTED);
		   
		   //return ResponseEntity.ok("Success");
		
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("notfound");
	}
	
}









}