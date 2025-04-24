package com.bor.rcms.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.CourtReq;
import com.bor.rcms.dto.DebatorVo;
import com.bor.rcms.dto.DocumentDTO;
import com.bor.rcms.dto.FileRequeistionDTO;
import com.bor.rcms.dto.FileRequeistionVo;
import com.bor.rcms.dto.ObjectionVo;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.CertificateDebator;
import com.bor.rcms.entity.CertificateGuaranter;
import com.bor.rcms.entity.CourtAdd;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.CourtAddRepo;
import com.bor.rcms.repository.DocumentPDRRepository;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.resonse.ReqiestionResponnse;
import com.bor.rcms.response.StatusRes;
import com.bor.rcms.service.PdrService;
import com.bor.rcms.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import Validation.UserRegistrationValidator;

@RestController
@RequestMapping("api/pdr")

public class PDRController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private DocumentPDRRepository documentPDRRepository;
	
	
	@Autowired 
	private PdrService pdrService;
	
	@Autowired 
	private UserRepository repository;
	
	@GetMapping("/Hello")
	public String HelloApi() {
		return "Hello Api.";
	}
	
	
	@PostMapping("/findemail")
	public ResponseEntity<?> findMobileNumber(@RequestBody Map<String, String> request) {
	    try {
	        String email = request.get("email");  // Extract phone number from the request body
	        UserEntity entity = repository.findByEmail(email);

	        if (entity != null) {
	            // Return a JSON response
	            return ResponseEntity.ok(Map.of("message", "Try another email"));
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
			@RequestPart(value = "files", required = false) MultipartFile[] files,     @RequestPart(value = "documentTypes", required = false) String  documentTypes,
			@RequestPart("username") String username) {

//		UserRegistrationValidator.ValidationResult result = 
//			    UserRegistrationValidator.checkUser(requiestion);
//		StatusRes res = new StatusRes();
//			if (!result.passed()) {
//				res.setStatus("400");
//				res.setMessage(result.getErrors());
//			    return ResponseEntity.badRequest().body(res);
//			}
		try {
			// Debugging the received inputs
			System.out.println("Received objection JSON: " + requiestion);
			System.out.println("Received username: " + username);
			System.out.println("Number of files: " + (files != null ? files.length : 0));

			// Convert the JSON string to ObjectionEntity
			ObjectMapper objectMapper = new ObjectMapper();
			FileRequeistionVo  vo = objectMapper.readValue(requiestion, FileRequeistionVo .class);

			 List<CertificateDebator> debatorlist = new  ArrayList<CertificateDebator>();
			 
			 for(DebatorVo debatorVo:vo.getDebatorVos())
			 {
				 CertificateDebator debator=new CertificateDebator();
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
		        
		        debator.setPincode(debatorVo.getDebtorDistrict());
		        debatorlist.add(debator);
		        
			 }
		      
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
		        requisition.setCertificateGuaranter(granter);
		        requisition.setFinancialYear(vo.getFinancialYear());
		        requisition.setReason(vo.getResion());
		       
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
				String objectionId = pdrService.submitRequisition(requisition, files, username,documentTypes);
			return ResponseEntity.ok(objectionId);

				//

		        // Get user from service
//		        UserEntity user = userService.getUserByUsername(username);
//		        requisition.setUserId(user);
//
//			// Call the service to handle the objection and files
//			String objectionId = objectionService.submitObjection(entity, files, username,documentTypes);

			// Respond with the objectionId after submission
			//return ResponseEntity.ok(Collections.singletonMap("objectionId", objectionId));
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

	        return ResponseEntity.status(HttpStatus.ACCEPTED)
	                .body("No requisitions found for district: " + district);
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
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for requisition ID: " + recuisition);
	        }

	        // FileRequeistionDTO
	        FileRequeistionDTO dto = new FileRequeistionDTO();
	        dto.setRequeistionId(newObjection.getRequeistionId());
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
	        CertificateGuaranter g = newObjection.getCertificateGuaranter();
	        if (g != null) {
	            vo.setGuarantorName(g.getGranterName());
	            vo.setGuarantorAddress(g.getAddress());
	            vo.setGuarantorAddress1(g.getAddress1());
	            vo.setGuarantorAddress2(g.getAddress2());
	            vo.setGuarantorState(g.getState());
	            vo.setGuarantorCity(g.getCity());
	            vo.setGuarantorDistrict(g.getDistrict());
	            vo.setGuarantorPincode(g.getPincode());
	            vo.setGuarantorPhoneNumber(g.getPhoneNumber());
	         //   vo.setGuarantorStatePhoneNumber(g.get);
	            vo.setGuarantorEmail(g.getEmail());
	            vo.setGuarantorfatherNames(g.getFatherName());
	            vo.setGuarantorsubDivision(g.getSubDivision());
	            vo.setGuarantorcircle(g.getCircle());
	            vo.setGuarantorpolicestation(g.getPolicestation());
	            vo.setCreatedDate(g.getCreatedDate());
	            vo.setModifiedDate(g.getModifiedDate());
	        }

	        // Debtors
	        List<DebatorVo> debatorVoList = new ArrayList<>();
	        if (newObjection.getCertificateDebator() != null) {
	            for (CertificateDebator d : newObjection.getCertificateDebator()) {
	                DebatorVo dvo = new DebatorVo();
	                dvo.setDebtorName(d.getDebatorName());
	                dvo.setDebtorAddress(d.getAddress());
	                dvo.setDebtorAddress1(d.getAddress1());
	                dvo.setDebtorAddress2(d.getAddress2());
	                dvo.setDebtorState(d.getState());
	                dvo.setDebtorCity(d.getCity());
	                dvo.setDebtorDistrict(d.getDistrict());
	                dvo.setDebtorPincode(d.getPincode());
	                dvo.setDebtorPhoneNumber(d.getPhoneNumber());
	                dvo.setDebtorStatePhoneNumber(d.getState()); // Check if this is correct
	                dvo.setDebtorEmail(d.getEmail());
	                dvo.setDebtorfatherNames(d.getFatherNames());
	                dvo.setDebtorubDivision(d.getSubDivision());
	                dvo.setDebtorcircle(d.getCircle());
	                dvo.setDebtorpolicestation(d.getPolicestation());
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

	        // Response mapping
	        ReqiestionResponnse response = new ReqiestionResponnse();
	        
	        
	        
	     // Map DocumentEntityPdr to DocumentDTO
	        List<DocumentDTO> documentDTOs = newObjection.getDocuments().stream()
	            .map(doc -> {
	                DocumentDTO docDto = new DocumentDTO();
	                docDto.setId(doc.getId());
	                docDto.setDocumentName(doc.getDocumentName()); // Assuming fileName holds documentName
	                docDto.setFilePath(doc.getFilePath());     // Assuming this exists in DocumentEntityPdr
	                docDto.setFileType(doc.getFileType());     // Assuming this exists
	                docDto.setFileSize(doc.getFileSize());     // Assuming this exists
	                docDto.setDocumentType(recuisition);       // If this is meant to be a label or ID
	                return docDto;
	            })
	            .collect(Collectors.toList());

	        response.setDocumentEntityPdrs(documentDTOs);

	        response.setFileRequeistion(dto);
	        response.setEntity(newObjection.getUserId());
	      //  response.setDocumentEntityPdrs(newObjection.getDocuments());
	        response.setFileRequeistionVo(vo);
	        response.setStatus("200");
	        response.setMsg("Success");

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error retrieving requisition: " + e.getMessage());
	    }
	}

	
	//CertificateOfficer
	
	@PostMapping("officersAdmission")
	public ResponseEntity<?> Admission(@RequestBody OfficerStatusVo statusvo) {
		// TODO: process POST request

		try {
			
			String status = pdrService.upadateStatus(statusvo);
			if (!status.equals("")) {
					return ResponseEntity.ok(status);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
						.body("Error retrieving objections: "+status);
			}

			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving objections: ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
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

	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No requisitions found for district: " + district);
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
		
		StatusRes  response=new StatusRes();
		try {
			
			String res=pdrService.addCourt(courtReq);
			if(res.equals("save"))
				
			{
				response.setMessage(res);
				response.setStatus("200");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

				
			}
			response.setMessage(res);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	@PostMapping("addcourtList")
	public ResponseEntity<?> addcourtlist(@RequestParam Long userId) {
		StatusRes  response=new StatusRes();

		try {
			
			List<CourtReq> reslist=pdrService.addCourtlistShow(userId);
			if(!reslist.isEmpty())
			{
				
				return new ResponseEntity<>(reslist, HttpStatus.ACCEPTED);

				
			}
			response.setMessage("not Found");
			response.setStatus("400");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("noticeForm")
	public ResponseEntity<?> addcourtlist(@RequestParam String selectForm ,@RequestParam String reqId) {
		
		try {
			
		
			if(selectForm!=null && reqId!=null)
			{
				
				StatusRes res=pdrService.noticeGenerate(selectForm,reqId);
				
				   
	            if(res==null)
	            {
	            	res.setMessage("form  will  not be sumbitted");
	            	res.setStatus("400");
					return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

	            }
				return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

				
			}
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	///Case Transfer
	
	@PostMapping("caseTransfer")
	public ResponseEntity<?> caseTransfer(@RequestParam List<String> reqId, @RequestParam List<String> nouserId) {
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
	
	public ResponseEntity<?> getTranferFileshow(@RequestParam String userId) {
		
		 try {
		        List<FileRequeistion> fileRequeistions = pdrService.findpendingNom(userId);

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

		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .body("No requisitions found for district: " + userId);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Error retrieving objections: " + e.getMessage());
		    }
	
		
	}
	
	
	
	
	
	
}
