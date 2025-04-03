package com.bor.rcms.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.NoticeRelease;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.AdmissionRepo;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.Misrepo;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.repository.NoticeRepo;
import com.bor.rcms.repository.UserRepository;
import com.bor.rcms.response.ObjectionStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
@Transactional
public class ObjectionServiceImpl implements ObjectionService {
	  @Autowired
	    private DocumentRepository documentRepository;
	    private final String FILE_STORAGE_PATH = "C:/Users/Admin/file";


	  @Autowired
	  private AdmissionRepo admissionRepo;
	  
	@Autowired
	private Misrepo misrepo;
	@Autowired
	private NewObjectionRepo newObjectionRepo;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoticeRepo noticeRepo;
	@Override
	public List<NewObjection> findAll() {
		// TODO Auto-generated method stub

        return newObjectionRepo.findAll();
	}

	@Override
	public Optional<NewObjection> findbyId(Long obId) {
		// TODO Auto-generated method stub
		return newObjectionRepo.findById(obId);
	}

	@Override
	public String upadateStatus(OfficerStatusVo statusvo) {
		try {
			// Find the NewObjection entity
			NewObjection objection = newObjectionRepo.findById(statusvo.getObjId()).orElse(null);

			if (statusvo.getStatus().equals("Admit")) {
				Admission admission = new Admission();
				admission.setAdmissionDate(statusvo.getAdmissionDate());
				admission.setAdmissionTime(statusvo.getAdmisionTime());
				admission.setAffidavitDate(statusvo.getAffedefitDate());
				admission.setOfficerName(statusvo.getOfficerName());
				
				String caseID=generateAdmisionNextToken(objection);
				admission.setAdmisionCase(caseID);
				objection.setStatus("Admit");
				objection.setObjectionId(objection.getObjectionId());
				objection.setAdmission(admission);
				admission.setNewObjection(objection);
				admission.setUserId(objection.getUserId());
				admission.setHearingDate(statusvo.getAdmissionDate());
				admission.setStatus("Admit");
				admission.setNewObjection(objection);
				admission.setObjectioId(objection.getTokenNo());
				
			//	Mis mis = new Mis();

				if (statusvo.getUsertype().equals("specialOfficer")) {
//					mis.setSpecialOfficer("SpecialOfficer");
//					mis.setSpecialRemarks(statusvo.getStatus());
//					mis.setObjId(objection.getTokenNo());
//					mis.setNewObjection(objection);
					objection.setAdmission(admission);
					//objection.setMis(mis);
					NewObjection savedObjection =new NewObjection();
					try {
					
				//	System.out.println("dd===>" + objection);

					 savedObjection = newObjectionRepo.save(objection);
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					String notice=noticedgenerate(savedObjection.getUserId().getFullName(),statusvo.getAdmissionDate(),statusvo.getAdmisionTime(),statusvo.getAffedefitDate(),savedObjection);

					if (savedObjection != null) {
						return "save";
					}

				}

			}

			// Check if objection exists
			if (objection != null && objection.getUserId() != null) {
				Mis miss = new Mis();
				if (statusvo.getUsertype().equals("specialOfficer")) {
					Admission admission1 = new Admission();
//
//					miss.setSpecialOfficer(statusvo.getUsertype());
//					miss.setSpecialRemarks(statusvo.getStatus());
//					miss.setObjId(objection.getObjectionId().toString());
//					miss.setCaseId(objection.getTokenNo());
//					miss.setNewObjection(objection);
//					objection.setMis(miss);
					objection.setStatus(statusvo.getStatus());
					admission1.setNewObjection(objection);
					objection.setStatus(statusvo.getStatus());

					admission1.setUserId(objection.getUserId());
					
					objection.setAdmission(admission1);
					NewObjection savedObjection = newObjectionRepo.save(objection);
					if (savedObjection != null) {
						return "save";
					}
				}
			}
			return "something wrong";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private String noticedgenerate(String userName, String admissionDate, String admisionTime, String affedefitDate,NewObjection newObjection) {
		// TODO Auto-generated method stub
		  Scanner scanner = new Scanner(System.in);

	       
	        String submissionDate = admissionDate;

	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDateTime = LocalDateTime.now().format(formatter);
			//entity.setCurrentDate(formattedDateTime);
	        String hearingtime = admisionTime;   // 2025-03-27
	        
	        
	        LocalDate date = LocalDate.parse(hearingDate, inputFormatter);

	        // Format the date to the desired output format
	        String formattedDate = date.format(formatter);

	        // HTML content as a string with placeholders for dynamic data
	        String htmlContent = "<!DOCTYPE html>"
	            + "<html lang=\"en\">"
	            + "<head>"
	            + "<meta charset=\"UTF-8\">"
	            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
	            + "<title>FORM A Notice</title>"
	            + "<style>"
	            + "body { font-family: Arial, sans-serif; margin:5px 20px; line-height: 1.6; text-align: justify; }"
	            + ".center { text-align: center; }"
	            + ".bold { font-weight: bold; }"
	            + ".large-text { font-size: 30px; }"
	            + ".underline { text-decoration: underline; }"
	            + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
	            + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "<p class=\"center bold\">FORM A</p>"
	            + "<p class=\"center\"><small>(Please See Rule 4)</small></p>"
	            + "<p class=\"center large-text\">Office of the Special Officer,<br> District " + newObjection.getDistrictName() + "</p>"
	            + "<p class=\"center\">Miscellaneous Case No. " +newObjection.getAdmission().getObjectioId()+"</p>"
	            + "<p>To,<br>"
	            + "<span class=\"editable\">" + userName + "</span><br>"
	            + "<span class=\"editable\">" + newObjection.getUserId().getAddress() +" "+newObjection.getUserId().getState()+" "+newObjection.getUserId().getPincode()+"</span></p>"
	            + "<p>Sir,</p>"	            
	            + "<p>In response to your objection filed under Section 7 of the Vesting of Bettiah Raj Properties Act, 2024 read with Rule 3 and Rule 4 of the Vesting of Bettiah Raj Properties Rules, 2025, it is to inform you to submit your detailed reply by way of a notarized affidavit latest by " + formattedDate + ".</p>"            
	            + "<p>In your affidavit, you must clearly indicate the following in detail:</p>"
	            + "<ol type=\"a\">"
	            + "<li>Your Name: <span class=\"name\">" + "" + "</span></li>"
	            + "<li>Correspondence Address: <span class=\"address\">" + "" + "</span></li>"
	            + "<li>Detailed address of the Property for which you have raised the objection: <span class=\"property-address\">" + "" + "</span></li>"
	            + "<li>Age - Enclose the copy of Aadhaar Card in support: <span class=\"age\">" + "" + "</span></li>"
	            + "<li>Phone Number: <span class=\"phone\">" + "" + "</span></li>"
	            + "<li>WhatsApp Number: <span class=\"whatsapp\">" + "" + "</span></li>"
	            + "<li>Email: <span class=\"email\">" + "" + "</span></li>"
	            + "<li>Detailed submissions regarding your objection: <span class=\"submission\"></span></li>"
	            + "<li>Please enclose all the documents as required under Rule 7: <span class=\"documents\"></span></li>"
	            + "</ol>"
	            + "<p>Please note that the undersigned shall hear the matter on " + formattedDate + " at " + admisionTime + ". You are requested to attend the hearing personally or through an Advocate.</p>"	            
	            + "<p class=\"signature\">Yours Sincerely,<br><br>"
	            + "<span>(Special Officer)</span></p>"
	            + "<p>Date: <span class=\"editable\">" + formattedDate + "</span></p>"
	            + "<p>Place: <span class=\"editable\">" +newObjection.getDistrictName()+ "</span></p>"   
	            + "</body>"
	            + "</html>";

	        // Convert HTML to PDF and save to a file
	        try {
	            // Specify the output file path
	       	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

             // Define the file name for the PDF, adding current time as part of the name
             String pdfFilePath = FILE_STORAGE_PATH + "FormANoticeA_" + userName + "_" + currentMinute + ".pdf";
            DocumentEntity enNoticeRelease=new DocumentEntity();
	            
	            enNoticeRelease.setDocumentType("NoticeA");
	            enNoticeRelease.setFilePath(pdfFilePath);
	            enNoticeRelease.setObjection(newObjection);
	            
	            DocumentEntity DocumentEntity=documentRepository.save(enNoticeRelease);
	            
	            
	            // Convert HTML content to PDF and save it
	            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(pdfFilePath));
	            
	            System.out.println("PDF generated and saved successfully at: " + pdfFilePath);
	        } catch (IOException e) {
	            System.out.println("Error generating PDF: " + e.getMessage());
	        }
			return htmlContent;

	        // Close the scanner
	    }	

	//
		
		

	@Override
	public NewObjection savedata(NewObjection entity) {
		// TODO Auto-generated method stub
		try {
			
			String token=generateNextToken(entity);
			entity.setTokenNo(token);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDateTime = LocalDateTime.now().format(formatter);
			entity.setCurrentDate(formattedDateTime);
			entity.setStatus("pending");
			
			NewObjection newObjection=newObjectionRepo.save(entity);
			return newObjection;
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		return null;
	}
	
	
	public String generateNextToken(NewObjection objection) {
	    try {
	        int initial = 1; // Starting from 0001

	        // Get current year in two digits (e.g., 25 for 2025)
	        String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);

	        // Fetch last inserted NewObjection by districtName
	        Optional<NewObjection> lastInsertedUser = newObjectionRepo.findTopByDistrictNameOrderByCurrentDateDesc(objection.getDistrictName());

	        if (lastInsertedUser.isPresent()) {
	            // Extract the token number and increment it
	            String caseId2 = lastInsertedUser.get().getTokenNo();
	            String[] parts = caseId2.split("-");  // Splitting by '-'
	            int caseNumber = Integer.parseInt(parts[parts.length - 1]);
	            int finalValue = caseNumber + 1;
	            String formattedValue = String.format("%04d", finalValue); // Ensure the number is 4 digits

	            // Set the token number with the incremented value
	            objection.setTokenNo("OBJ-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
	        } else {
	            // If no previous entries exist, start with 0001
	            String formattedValue = String.format("%04d", initial);
	            objection.setTokenNo("OBJ-" + objection.getDistrictName() + "-" + currentYear + "-" + formattedValue);
	        }

	        return objection.getTokenNo();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // Handle exception properly
	}


	
	public String generateAdmisionNextToken(NewObjection objection) {
		try {
			int initial = 0001;
			String caseId;
			
			Admission admission=new Admission();
		//	int currentYear = Year.now().getValue();
		    String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year

			//String caseId = user.getCaseId();
			// Fetch last inserted user
			
			Optional<Admission> lastInsertedUser = admissionRepo.findTopByDistrictOrderByCurrentdateDesc(objection.getDistrictName());
			if (lastInsertedUser.isPresent()) {
				String caseId2 = lastInsertedUser.get().getAdmisionCase();
				String[] parts = caseId2.split("-");  // Splitting by '-'
				int caseNumber = Integer.parseInt(parts[parts.length - 1]);
				int finalValue = caseNumber +1;
				String formattedValue = String.format("%04d", finalValue);
				admission.setAdmisionCase("Case-SO-"+objection.getDistrictName()+"-"+currentYear+"-"+formattedValue);
			//	System.out.println("Last inserted user: " + lastInsertedUser.get());
			} else {
				String formattedValue = String.format("%04d", initial);
				admission.setAdmisionCase("Case-SO-"+objection.getDistrictName()+"-"+currentYear+"-"+formattedValue);
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

	@Override

	public List<NewObjection> getObjectionsByUserId(Long userId) {
        return newObjectionRepo.findByUserId_UserId(userId);
    }

	@Override
	public List<NewObjection> findpending(String district) {
		// TODO Auto-generated method stub
        return newObjectionRepo.findAllPending(district);
	}

	@Override
	public ObjectionStatus getObjectionsdetials(Long userIds) {
	    try {
	        // Fetch the UserEntity object using the userId
	        UserEntity userEntity = userRepository.findById(userIds).orElse(null);
               Long userid=userEntity.getUserId();
	        if (userEntity == null) {
	            throw new IllegalArgumentException("User not found");
	        }

	        // Use the correct repository method with UserEntity
	        List<NewObjection> obj = newObjectionRepo.findByUserId(userid); // Correct method for UserEntity

	        // If you want to query just by userId:
	        // List<NewObjection> obj = newObjectionRepo.findByUserId_Id(userIds);

	        // Initialize the counts for different cases
	        int totalCase = obj.size();
	        int pendingCase = 0;
	        int closeCase = 0;
	        int rejectCase = 0;

	        // Count different statuses
	        for (NewObjection objection : obj) {
	            switch (objection.getStatus()) {
	                case "pending":
	                    pendingCase++;
	                    break;
	                case "closed":
	                    closeCase++;
	                    break;
	                case "reject":
	                    rejectCase++;
	                    break;
	                default:
	                    break;
	            }
	        }

	        // Create and return the ObjectionStatus object
	        ObjectionStatus objectionStatus = new ObjectionStatus();
	        objectionStatus.setTotalCase(String.valueOf(totalCase));
	        objectionStatus.setPendingCase(String.valueOf(pendingCase));
	        objectionStatus.setCloseCase(String.valueOf(closeCase));
	        objectionStatus.setRejectCase(String.valueOf(rejectCase));
	        objectionStatus.setObjections(obj);

	        return objectionStatus;

	    } catch (Exception e) {
	        // Handle exception
	        e.printStackTrace();
	        return null;
	    }
	}



    public String submitObjection(NewObjection objection, MultipartFile[] files, String username, String documentTypes) {
        // Fetch User
    	
        UserEntity user = userRepository.findById(Long.valueOf(username)).orElseThrow(() -> new RuntimeException("User not found"));
//        		userRepository.findByUserName(username)
//            .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate Unique Objection ID
        String objectionId = generateNextToken(objection);
        		//UUID.randomUUID().toString().replace("-", "").substring(0, 10);
      //  objection.setTokenNo(objectionId);
        objection.setUserId(user);
        objection.setTokenNo(objectionId);
        objection.setStatus("pending");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDateTime = LocalDateTime.now().format(formatter);
		objection.setCurrentDate(formattedDateTime);

        // Save Objection to the database
        ObjectMapper objectMapper = new ObjectMapper();

        NewObjection savedObjection = newObjectionRepo.save(objection);
        List<Map<String, String>> documentInfo = null;
        if (documentTypes != null && !documentTypes.isEmpty()) {
            try {
				documentInfo = objectMapper.readValue(documentTypes, new TypeReference<List<Map<String, String>>>() {});
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
            List<DocumentEntity> savedDocuments = new ArrayList<>();
            
            // Iterate over files and save them
            for (int i = 0; i < files.length; i++) {  // Use i to match files with document types
                MultipartFile file = files[i];
            	
            	
            	  if (file.getSize() > 10 * 1024 * 1024) {  // 10MB size limit
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
                DocumentEntity document = new DocumentEntity();
                document.setDocumentName(file.getOriginalFilename());
                document.setCaseId(savedObjection.getTokenNo());
                document.setFilePath(filePath);
                document.setFileType(file.getContentType());
                document.setFileSize(file.getSize());
                document.setObjection(savedObjection); // Associate document with the objection
                document.setUploadedDate(new Date());
                // Set the documentType from documentInfo if available
                if (documentInfo != null &&  i < documentInfo.size()) {
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

	@Override
	public List<NewObjection> findaproved() {
		// TODO Auto-generated method stub
        return newObjectionRepo.findAllApproved();
	}

	@Override
	public Mis findmistatus(String objId) {
		// TODO Auto-generated method stub
		return misrepo.findByObjId(objId);
	}

	@Override
	public DocumentEntity getObjectionsDocumentdetials(Long obId) {
		// TODO Auto-generated method stub
		try {
			List<DocumentEntity> documentEntity=null;
					//documentRepository.getobj
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public NewObjection getObjectionsId(Long objids) {
		// TODO Auto-generated method stub
		return newObjectionRepo.findByObjectionId(objids);
	
	}

	@Override
	public ObjectionStatus ObjectionsdetialsPending(String userid,String district) {
		try {
	        // Fetch the UserEntity object using the userId
	        UserEntity userEntity = userRepository.findById(Long.valueOf(userid)).orElse(null);

	        if (userEntity == null) {
	            throw new IllegalArgumentException("User not found");
	        }

	        // Use the correct repository method with UserEntity
	     //   List<NewObjection> obj = newObjectionRepo.findAll(); // Correct method for UserEntity

	        
	        List<NewObjection> obj = newObjectionRepo.findAlldistrict(district); // Correct method for UserEntity

	        // If you want to query just by userId:
	        // List<NewObjection> obj = newObjectionRepo.findByUserId_Id(userIds);

	        // Initialize the counts for different cases
	        int totalCase = obj.size();
	        int pendingCase = 0;
	        int closeCase = 0;
	        int rejectCase = 0;

	        // Count different statuses
	        for (NewObjection objection : obj) {
	            switch (objection.getStatus()) {
	                case "pending":
	                    pendingCase++;
	                    break;
	                case "closed":
	                    closeCase++;
	                    break;
	                case "reject":
	                    rejectCase++;
	                    break;
	                default:
	                    break;
	            }
	        }

	        // Create and return the ObjectionStatus object
	        ObjectionStatus objectionStatus = new ObjectionStatus();
	        objectionStatus.setTotalCase(String.valueOf(totalCase));
	        objectionStatus.setPendingCase(String.valueOf(pendingCase));
	        objectionStatus.setCloseCase(String.valueOf(closeCase));
	        objectionStatus.setRejectCase(String.valueOf(rejectCase));
	        objectionStatus.setObjections(obj);

	        return objectionStatus;

	    } catch (Exception e) {
	        // Handle exception
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public List<NewObjection> getdismisObject(String adId) {
	    // Fetch the dismissed Admission records by adId
	    List<Admission> admissions =null;
	    		//admissionRepo.findAllDismissedById(Long.valueOf(adId));

	    // Create a list to hold NewObjection objects
	    List<NewObjection> newObjections = new ArrayList<>();

	    // Iterate over each Admission and fetch the NewObjection associated with it
	    for (Admission admission : admissions) {
	        NewObjection newObjection = admission.getNewObjection(); // Assuming each Admission has a NewObjection object
	        if (newObjection != null) {
	            newObjections.add(newObjection);
	        }
	    }

	    // Return the list of NewObjections
	    return newObjections;
	}

	@Override
	public List<Admission> getAdmitcaseorHearing(String hearingDate) {
		// TODO Auto-generated method stub
		List<Admission> admission=admissionRepo.findByHearingDateOrStatus(hearingDate);
		return admission ;
	}

//	@Override
//	public NewObjection hearingDatechange(String hearingDate, String caseId) {
//		NewObjection objection=newObjectionRepo.findByObjectionId(Long.valueOf(caseId));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formattedDateTime = LocalDateTime.now().format(formatter);
//		objection.setUpdateDate(formattedDateTime);
//		Admission admission=objection.getAdmission();
//		admission.setHearingDate(hearingDate);
//		objection.setAdmission(admission);
//		NewObjection newObjection=newObjectionRepo.save(objection);
//		return newObjection;
//	}

	
	
	@Override
    public NewObjection hearingDatechange(String hearingDate, String caseId) {
        // Find the objection by caseId (check if it's present)
		Admission admission1=admissionRepo.findByAdmisionCasemodify(caseId);
        NewObjection optionalObjection =admission1.getNewObjection();
        		//newObjectionRepo.findByObjectionId(Long.valueOf(caseId));
        if (optionalObjection==null) {
            throw new IllegalArgumentException("Objection with caseId " + caseId + " not found.");
        }

        // Retrieve the object
        //NewObjection objection = optionalObjection.get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedHearingDate = LocalDate.parse(hearingDate, formatter);  // Parse the string into LocalDate

        String formattedDateTime = LocalDate.now().format(formatter); // Use LocalDate if you're only dealing with date
        optionalObjection.setUpdateDate(formattedDateTime);

       // optionalObjection.setStatus("Transfer");
        Admission admission = optionalObjection.getAdmission();
        if (admission != null) {
            admission.setHearingDate(hearingDate); // Set the parsed hearing date
            optionalObjection.setAdmission(admission);
        } else {
            throw new IllegalArgumentException("Admission not found for Objection with caseId " + caseId);
        }

        // Save and return the updated objection
        return newObjectionRepo.save(optionalObjection);
    }

	@Override
	public List<Admission> getAdmitcaseorupdateCasHearing(String hearingDate, String caseId) {
		// TODO Auto-generated method stub
		List<Admission> admission=admissionRepo.findByHearingDateOrStatus(hearingDate);
		
		List<Admission> admissionlist=new ArrayList<Admission>();

		for(Admission admission2 :admission)
		{
			if(admission2.getAdmisionCase().equals(caseId))
			{
				admissionlist.add(admission2);
			}
		}

		return admissionlist;
	}

	@Override
	public Admission modifycause(String caseNo, String dateOfHearing, String time, String reason, String caseClass,
			String action) {
		Admission admission1=admissionRepo.findByAdmisionCasemodify(caseNo);
        NewObjection optionalObjection = admission1.getNewObjection();
        		//newObjectionRepo.findByObjectionId(Long.valueOf(caseNo));
        if (optionalObjection==null) {
            throw new IllegalArgumentException("Objection with caseId " + caseNo + " not found.");
        }

        // Retrieve the object
        //NewObjection objection = optionalObjection.get();

   //
     //   String formattedDateTime = LocalDate.now().format(formatter); // Use LocalDate if you're only dealing with date

        Admission admission = optionalObjection.getAdmission();
        if (admission != null) {
            admission.setHearingDate(dateOfHearing); // Set the parsed hearing date
            admission.setAction(action);
            admission.setHearingTime(time);
            admission.setCaseNotes(caseNo);
            admission.setCaseClass(caseClass);

        } 
        
        
        optionalObjection.setAdmission(admission);
        NewObjection objection = newObjectionRepo.save(optionalObjection); 
        
        Admission admission2=objection.getAdmission();
	
        
		return admission2;
	}

	@Override
	public List<Admission> getfindDismis() {
		List<Admission>  admissions=admissionRepo.findAlldismispending();
		return admissions;
	}

	@Override
	public Admission findcaseForFreehold(String caseId) {
Admission admission= admissionRepo.findByAdmisionCase(caseId);
		
	
		return admission;
	}

	@Override
	public NewObjection sublitApeal(String caseId, String remark) {
		// TODO Auto-generated method stub
		return null;
	}

}
