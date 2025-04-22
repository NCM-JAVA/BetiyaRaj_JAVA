package com.bor.rcms.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.RoleEntity;
import com.bor.rcms.entity.UserEntity;
import com.bor.rcms.repository.AdmissionRepo;
import com.bor.rcms.repository.AppealCollectorRepo;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.repository.UserRepository;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
@Transactional

public class AdminServiceImpl implements AdminService {
	@Autowired
	private NewObjectionRepo newObjectionRepo;

	@Autowired
	private AdmissionRepo admissionRepo;
	@Autowired
	private  UserRepository repository;
	
	@Autowired
	private  AppealCollectorRepo caseCollectorRepo;

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public NewObjection savecaseDetails(CaseNotes casenotes) {
		try {
			// Fetch the NewObjection using the objection ID
			NewObjection newObjection = newObjectionRepo.findByObjectionId(Long.valueOf(casenotes.getObjId()));
			
			   String formType = casenotes.getSelectForm().trim();  // Trim to remove any extra spaces

		        // Check if the form is empty but hearing date is not empty
		        if(casenotes.getSelectForm().equals("") && !casenotes.getNextHearingDate().equals("")) {
		            NewObjection newObjection2 = new NewObjection();
		            newObjection2.setStatus("date will be mandatory");
		            return newObjection2;
		        }
		        if(formType.equals("Form A")) {
		            String notice = noticedgenerateA(newObjection.getUserId().getFullName(), casenotes.getNextHearingDate(), casenotes.getTime(), newObjection);
		        }

		        // Check for Form B
		        if(formType.equals("Form B")) {
		        	System.out.println("formB====");
		            String notice = noticedgenerateB(newObjection.getUserId().getFullName(), casenotes.getNextHearingDate(), casenotes.getTime(), newObjection);
		        }
		        
		        // Check for Form A (corrected comparison and removed extra semicolon)
		      
			

			if (newObjection != null && newObjection.getObjection() != null) {

				Admission admission = newObjection.getAdmission();
				admission.setAction(casenotes.getCaseClass());
				admission.setHearingDate(casenotes.getNextHearingDate());
				admission.setHearingTime(casenotes.getTime());
				admission.setCaseClass(casenotes.getCaseClass());
				admission.setStatus(casenotes.getAction());
				admission.setObjectioId(newObjection.getTokenNo());

//				byte[] notesBytes = casenotes.getCaseNotes().getBytes("UTF-8");
//				Blob notesBlob = new SerialBlob(notesBytes);
				
				if(casenotes.getAction().equals("Dismiss"))
				{
					admission.setStatusCollector("pending");
				}

				admission.setCaseNotes(casenotes.getCaseNotes());
				admission.setNewObjection(newObjection);
				// Admission savedAdmission = admissionRepo.save(admission);
				newObjection.setAdmission(admission);
				
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
				newObjection.setStatus_officer("SpecialOfficer");
				newObjection.setStatus(casenotes.getAction());
				
				if(casenotes.getAction().equals("Dismiss"))
				{
					newObjection.setStatusCollector("pending");
				}
				NewObjection savedNewObjection = newObjectionRepo.save(newObjection);
				
				

				return savedNewObjection;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
    @Transactional
    public NewObjection getcaseNOtes(String objid) {
        // Convert objid to Long and fetch the NewObjection entity
        Long objectionId = Long.valueOf(objid);
        NewObjection newObjection = newObjectionRepo.findByObjectionId(objectionId);

        // Return the NewObjection if found, otherwise null
        return newObjection;
    }

	@Override
	public List<UserEntity> userdata(String officer) {
	    List<UserEntity> user = new ArrayList<>(); // List to hold filtered users
	    try {
	        List<UserEntity> entity = repository.findAll(); // Fetch all users
	        
	        if (officer != null && !officer.isEmpty()) { // Ensure officer is not null or empty
	            for (UserEntity user1 : entity) {
	            	RoleEntity roleEntity=user1.getRole();
	                if (roleEntity.getRoleName().equals(officer)) {
	                    user.add(user1); // Add user to list if role matches
	                }
	            }
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle exception (you could log it in production)
	    }
	    
	    return user; // Return the list of matching users
	}

	@Override
	public Admission findByCaseId(String caseId) {
		// TODO Auto-generated method stub
		Admission admission=admissionRepo.findByAdmisionCase(caseId);
		return admission;
	}

	@Override
	public List<Admission> getdismisObject(String userId) {
		List<Admission> admission=admissionRepo.findDismissStatusByUserId(Long.valueOf(userId));
		return admission;
	}

	////
//	private static final String FILE_STORAGE_PATH = "D:\\FormANoticeB_";
	
    private final String FILE_STORAGE_PATHA = "C:/Users/Admin/file/NoticeA";

    private final String FILE_STORAGE_PATHB = "C:/Users/Admin/file/NoticeB/";
    
    
    private final String FILE_STORAGE_PATHC= "C:/Users/Admin/file/NoticeC";

    private final String FILE_STORAGE_PATHD= "C:/Users/Admin/file/NoticeD";
    
    private final String FILE_STORAGE_PATHE= "C:/Users/Admin/file/NoticeE";




	
	//collector//notice///Notice
	private String noticedgenerateB(String userName, String admissionDate, String admisionTime,NewObjection newObjection) {
		// TODO Auto-generated method stub
		  Scanner scanner = new Scanner(System.in);

	       
	        System.out.println("Enter Date for Submission (e.g., 15th March 2025):");
	        String submissionDate = admissionDate;

	        System.out.println("Enter Hearing Date (e.g., 20th March 2025):");
	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        String hearingtime = admisionTime;

	        // HTML content as a string with placeholders for dynamic data
	        String htmlContent = "<!DOCTYPE html>"
	                + "<html lang=\"en\">"
	                + "<head>"
	                + "<meta charset=\"UTF-8\">"
	                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
	                + "<title>FORM B Notice</title>"
	                + "<style>"
	                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
	                + ".center { text-align: center; }"
	                + ".bold { font-weight: bold; }"
	                + ".large-text { font-size: 30px; }"
	                + ".underline { text-decoration: underline; }"
	                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
	                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
	                + "</style>"
	                + "</head>"
	                + "<body>"

	                + "<p class=\"center bold\">FORM B</p>"
	                + "<p class=\"center\"><small>(Please See Rule 4)</small></p>"

	            + "<p class=\"center large-text\">Office of the Special Officer,<br> District " + newObjection.getDistrictName() + "</p>"

	            + "<p class=\"center\">Miscellaneous Case No. " +newObjection.getAdmission().getObjectioId()+"</p>"

	                + "<p>To,<br>"
	                + "The Collector,<br>"
	                + "<span class=\"editable\"></span></p>"

	                + "<p>Sir/Madam,</p>"

	                + "<p>I Shri/Smt <span class=\"editable\">" + userName + "</span>, in my capacity as the Special Officer, "
	                + "have rejected the objection petition filed by the occupant under Rule 3 of The Vesting of Bettiah Raj Properties Rules, 2025 "
	                + "in case of the following person:</p>"

	                + "<ol>"
	                + "<li>Name of the occupant: <span class=\"occupant\"></span></li>"
	                + "<li>Correspondence address of the occupant: <span class=\"occupant\"></span></li>"
	                + "<li>Phone Number and Email of the occupant: <span class=\"occupant\"></span></li>"
	                + "<li>Details of the property of the occupant whose possession is to be taken: <span class=\"occupant\"></span></li>"
	                + "</ol>"

	                + "<p>In terms of Rule 4 (7), you are requested to direct the concerned officer to take possession of the aforementioned property "
	                + "as per the prescribed procedure.</p>"

	                + "<p>I am also enclosing the copy of the detailed Order vide which the objection of the occupant has been rejected.</p>"

	                + "<p class=\"signature\">Yours Sincerely,<br><br>"
	                + "<span class=\"bold\">(Special Officer)</span></p>"

	                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
	                + "<p>Place: <span class=\"editable\">"+ newObjection.getUserId().getAddress() +" "+newObjection.getUserId().getState()+"</span></p>"

	                + "</body>"
	                + "</html>";

	        // Convert HTML to PDF and save to a file
	        try {
	        	
	        	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
	             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

	             // Define the file name for the PDF, adding current time as part of the name
	             String pdfFilePath = FILE_STORAGE_PATHB+ "FormANoticeB_" + userName + "_" + currentMinute + ".pdf";

//	            // Specify the output file path
//	        	String pdfFilePath = "D:\\FormANoticeB_" + userName + ".pdf";
     DocumentEntity enNoticeRelease=new DocumentEntity();
     
     enNoticeRelease.setDocumentType("NoticeB");

	            
	            enNoticeRelease.setDocumentName("NoticeB");
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
	
	private String noticedgenerateA(String userName, String admissionDate, String admisionTime,NewObjection newObjection) {
		// TODO Auto-generated method stub
	        String submissionDate = admissionDate;

	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        String hearingtime = admisionTime;

	        // HTML content as a string with placeholders for dynamic data
	        String htmlContent = "<!DOCTYPE html>"
		            + "<html lang=\"en\">"
		            + "<head>"
		            + "<meta charset=\"UTF-8\">"
		            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
		            + "<title>FORM A Notice</title>"
		            + "<style>"
		            + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
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

		            + "<p class=\"center\">Miscellaneous Case No. " + "" + "</p>"

		            + "<p>To,<br>"
		            + "<span class=\"editable\">" + userName + "</span><br>"
		            + "<span class=\"editable\">" + newObjection.getUserId().getAddress() +"</span></p>"

		            + "<p>Sir,</p>"
		            
		            + "<p>In response to your objection filed under Section 7 of the Vesting of Bettiah Raj Properties Act, 2024 read with Rule 3 and Rule 4 of the Vesting of Bettiah Raj Properties Rules, 2025, it is to inform you to submit your detailed reply by way of a notarized affidavit latest by " + submissionDate + ".</p>"
		            
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

		            + "<p>Please note that the undersigned shall hear the matter on " + hearingDate + " at " + "" + ". You are requested to attend the hearing personally or through an Advocate.</p>"
		            
		            + "<p class=\"signature\">Yours Sincerely,<br><br>"
		            + "<span>(Special Officer)</span></p>"

		            + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
		            + "<p>Place: <span class=\"editable\">" + "" + "</span></p>"
		            
		            + "</body>"
		            + "</html>";
	        // Convert HTML to PDF and save to a file
	        try {
	            // Specify the output file path
	        	
	        	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
	             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

	             // Define the file name for the PDF, adding current time as part of the name
	             String pdfFilePath = FILE_STORAGE_PATHA + "FormANoticeA_" + userName + "_" + currentMinute + ".pdf";
	            DocumentEntity enNoticeRelease=new DocumentEntity();
	            enNoticeRelease.setDocumentType("NoticeA");

	            enNoticeRelease.setDocumentName("NoticeA");
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

	@Override
	public NewObjection savecaseDetailscollector(CaseNotes casenotes) {
		try {
			// Fetch the NewObjection using the objection ID
			NewObjection newObjection = newObjectionRepo.findByObjectionId(Long.valueOf(casenotes.getObjId()));
			
			   String formType = casenotes.getSelectForm().trim();  // Trim to remove any extra spaces

		        // Check if the form is empty but hearing date is not empty
		        if(casenotes.getSelectForm().equals("") && !casenotes.getNextHearingDate().equals("")) {
		            NewObjection newObjection2 = new NewObjection();
		            newObjection2.setStatus("date will be mandatory");
		            return newObjection2;
		        }
		        if(formType.equals("FormC")) {
		            String notice = noticedgenerateC(newObjection.getUserId().getUserName(), casenotes.getNextHearingDate(), casenotes.getTime(), newObjection);
		        }

		        // Check for Form B
		        if(formType.equals("FormD")) {
		            String notice = noticedgenerateD(newObjection.getUserId().getUserName(), casenotes.getNextHearingDate(), casenotes.getTime(), newObjection);
		        }
		        if(formType.equals("FormE")) {
		            String notice = noticedgeneratE(newObjection.getUserId().getUserName(), casenotes.getNextHearingDate(), casenotes.getTime(), newObjection);
		        }
		        
			

			if (newObjection != null && newObjection.getObjection() != null) {

				CaseCollector admission = newObjection.getCaseCollector();
				admission.setAction(casenotes.getCaseClass());
				admission.setHearingDate(casenotes.getNextHearingDate());
				admission.setHearingTime(casenotes.getTime());
				admission.setCaseClass(casenotes.getCaseClass());
				admission.setStatus(casenotes.getAction());

//				byte[] notesBytes = casenotes.getCaseNotes().getBytes("UTF-8");
//				Blob notesBlob = new SerialBlob(notesBytes);

				admission.setCaseNotes(casenotes.getCaseNotes());
				admission.setNewObjection(newObjection);
				// Admission savedAdmission = admissionRepo.save(admission);
				newObjection.setCaseCollector(admission);
				admission.setDistrict(newObjection.getDistrictName());
				Admission admission2=new Admission();
				admission2.setStatusCollector(casenotes.getAction());
				newObjection.setAdmission(admission2);
//				Mis mis = new Mis();
//				try {
//					mis = newObjection.getMis();
//					mis.setSpecialOfficer("Collector");
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				newObjection.setMis(mis);
				newObjection.setStatusCollector(casenotes.getAction());
//				newObjection.setStatus_officer(casenotes.getAction());
//				newObjection.setStatus(casenotes.getAction());
			NewObjection savedNewObjection = newObjectionRepo.save(newObjection);
				
				

				return savedNewObjection;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String noticedgenerateC(String userName, String admissionDate, String admisionTime,NewObjection newObjection) {
		// TODO Auto-generated method stub
		  String submissionDate = admissionDate;

	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        String hearingtime = admisionTime;
	        
	        String htmlContent = "<!DOCTYPE html>"
	                + "<html lang=\"en\">"
	                + "<head>"
	                + "<meta charset=\"UTF-8\">"
	                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
	                + "<title>FORM C Notice</title>"
	                + "<style>"
	                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
	                + ".center { text-align: center; }"
	                + ".large-text { font-size: 20px; }"
	                + ".bold { font-weight: bold; }"
	                + ".underline { text-decoration: underline; }"
	                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
	                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
	                + "</style>"
	                + "</head>"
	                + "<body>"

	                + "<p class=\"center bold\">FORM C</p>"
	                + "<p class=\"center\"><small>(Please See Rule 5 (3))</small></p>"

	                + "<p class=\"center large-text\">Office of the Collector, District "+newObjection.getDistrictName() + "<span class=\"editable\"></span></p>"

	                + "<p class=\"center\">Miscellaneous Case No. <span class=\"editable\"></span></p>"

	                + "<p class=\"center bold underline\">Notice</p>"

	                + "<p>To,<br>"
	                + "(Name of the occupant)<br>"
	                + "<span class=\"editable\">" + userName + "</span><br>"
	                + "<span class=\"editable\"></span></p>"

	                + "<p><strong>Subject:</strong> Possession of the property <span class=\"editable\"></span> (mention details of the property)</p>"

	                + "<p>Sir,</p>"

	                + "<p>With respect to the aforementioned property, the undersigned is in receipt of the Order dated <span class=\"editable\"></span> "
	                + "of the Special Officer rejecting your objections filed under Section 7 of The Vesting of Bettiah Raj Properties Act, 2024 "
	                + "read with Rule 3 and Rule 4 of the Vesting of Bettiah Raj Properties Rules, 2025 and whereas he has requested the undersigned "
	                + "to take possession of the aforementioned property;</p>"

	                + "<p><strong>Or</strong></p>"

	                + "<p>Whereas no objections have been filed with regard to the aforementioned property within 60 days of the publication of the "
	                + "Notification under Section 3 of The Vesting of Bettiah Raj Properties Act, 2024 and as such the said property stands vested "
	                + "with the State Government in terms of Section 3 of The Vesting of Bettiah Raj Properties Act, 2024 read with Rule 3 and Rule 5 "
	                + "(1) of the Vesting of Bettiah Raj Properties Rules, 2025;</p>"

	                + "<p>This notice is being issued to you to submit a reply within 15 days as to why the possession of the said property be not taken.</p>"

	                + "<p>Your reply should reach the undersigned within the time frame, else the undersigned shall be free to proceed further ex-parte.</p>"

	                + "<p class=\"signature\">Yours Sincerely,<br><br>"
	                + "<span class=\"bold\">(Collector)</span></p>"

	                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
	                + "<p>Place: <span class=\"editable\"></span></p>"

	                + "</body>"
	                + "</html>";

	        try {
	            // Specify the output file path
	        	
	        	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
	             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

	             // Define the file name for the PDF, adding current time as part of the name
	             String pdfFilePath = FILE_STORAGE_PATHC + "FormANoticeC_" + userName + "_" + currentMinute + ".pdf";
	            DocumentEntity enNoticeRelease=new DocumentEntity();
	            enNoticeRelease.setDocumentType("NoticeC");

	            enNoticeRelease.setDocumentName("NoticeC");
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
	}	
	
	
	private String noticedgenerateD(String userName, String admissionDate, String admisionTime,NewObjection newObjection) {
		// TODO Auto-generated method stub
		  String submissionDate = admissionDate;

	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        String hearingtime = admisionTime;
	        
	        String htmlContent = "<!DOCTYPE html>"
	                + "<html lang=\"en\">"
	                + "<head>"
	                + "<meta charset=\"UTF-8\">"
	                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
	                + "<title>FORM D - Willingness to Convert Leasehold Property</title>"
	                + "<style>"
	                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
	                + ".center { text-align: center; }"
	                + ".large-text { font-size: 20px; }"
	                + ".bold { font-weight: bold; }"
	                + ".underline { text-decoration: underline; }"
	                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
	                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
	                + "</style>"
	                + "</head>"
	                + "<body>"

	                + "<p class=\"center bold\">FORM D</p>"
	                + "<p class=\"center\"><small>(Please See Rule 9)</small></p>"

	                + "<p>To,<br>"
	                + "The Collector,<br>"
	                + "<span class=\"editable\"></span></p>"

	                + "<p><strong>Subject:</strong> Willingness to convert leasehold property into freehold property.</p>"

	                + "<p>Sir/Madam,</p>"

	                + "<p>I am the rightful occupant of the property mentioned below and I am willing to deposit the freehold amount in such installments as may be fixed by you.</p>"

	                + "<ul>"
	                + "<li><strong>My Name:</strong> <span class=\"Name\">" + userName + "</span></li>"
	                + "<li><strong>Correspondence Address:</strong> <span class=\"Address\"></span></li>"
	                + "<li><strong>Age:</strong> (Enclose the copy of Aadhaar Card in support) <span class=\"Age\"></span></li>"
	                + "<li><strong>Phone Number:</strong> <span class=\"Number\"></span></li>"
	                + "<li><strong>WhatsApp Number:</strong> <span class=\"Number\"></span></li>"
	                + "<li><strong>Email:</strong> <span class=\"Email\"></span></li>"
	                + "<li><strong>Detailed address of the property to be converted into freehold:</strong> <span class=\"Email\"></span></li>"
	                + "</ul>"

	                + "<p>I am enclosing all the documents as required under Rule 7 of the Vesting Bettiah Raj Properties Rules, 2025.</p>"

	                + "<p>Kindly inform me the freehold amount so that I can deposit the same. I am also enclosing the affidavit to this effect.</p>"

	                + "<p class=\"signature\">Yours Sincerely,<br><br>"
	                + "<span class=\"bold\">(Name and Signature of the occupant)</span></p>"

	                + "<p><strong>Enclose:</strong> Affidavit by the occupant.</p>"

	                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
	                + "<p>Place: <span class=\"editable\"></span></p>"

	                + "</body>"
	                + "</html>";

	        try {
	            // Specify the output file path
	        	
	        	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
	             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

	             // Define the file name for the PDF, adding current time as part of the name
	             String pdfFilePath = FILE_STORAGE_PATHD + "FormANoticeD_" + userName + "_" + currentMinute + ".pdf";
	            DocumentEntity enNoticeRelease=new DocumentEntity();
	            enNoticeRelease.setDocumentType("NoticeD");
	            enNoticeRelease.setDocumentName("NoticeD");
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
	}	
	
	private String noticedgeneratE(String userName, String admissionDate, String admisionTime,NewObjection newObjection) {
		// TODO Auto-generated method stub
		  String submissionDate = admissionDate;

	        String hearingDate = admissionDate;

	   //     System.out.println("Enter Hearing Place:");
	   //     String hearingPlace = scanner.nextLine();
	        
	        String hearingtime = admisionTime;
	        
	        String htmlContent = "<!DOCTYPE html>"
	                + "<html lang=\"en\">"
	                + "<head>"
	                + "<meta charset=\"UTF-8\">"
	                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
	                + "<title>Form E & Form F</title>"
	                + "<style>"
	                + "body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; text-align: justify; }"
	                + ".center { text-align: center; }"
	                + ".large-text { font-size: 20px; }"
	                + ".bold { font-weight: bold; }"
	                + ".underline { text-decoration: underline; }"
	                + ".signature { margin-top: 50px; font-weight: bold; text-align: right; }"
	                + ".editable { border-bottom: 1px dashed #000; display: inline-block; min-width: 100px; }"
	                + "</style>"
	                + "</head>"
	                + "<body>"

	                + "<!-- FORM E -->"
	                + "<p class=\"center bold\">FORM E</p>"
	                + "<p class=\"center\"><small>(Please See Rule 9 (4))</small></p>"
	                + "<p class=\"center large-text\">Office of the Collector, District <span class=\"editable\"></span></p>"

	                + "<p class=\"center\"><strong>Miscellaneous Case No.</strong> <span class=\"editable\"></span></p>"
	                + "<p class=\"center bold underline\">Notice</p>"

	                + "<p>To,<br>"
	                + "(Name of the occupant)<br>"
	                + "<span class=\"editable\"></span><br>"
	                + "<span class=\"editable\"></span></p>"

	                + "<p><strong>Subject:</strong> Freehold Amount</p>"

	                + "<p>Sir/Madam,</p>"

	                + "<p>Please refer to your application seeking freehold conversion of your leasehold property into freehold property.</p>"

	                + "<p>As per the District Valuation Committee, the market valuation of your property has been valued at <span class=\"editable\"></span>.</p>"

	                + "<p>Therefore, in terms of Rule 10 of the Vesting of Bettiah Raj Properties Rules, 2025, you are requested to deposit an amount of <span class=\"editable\"></span> as freehold amount. The amount should be paid either in full or in <span class=\"editable\"></span> installments.</p>"

	                + "<p>The installment requested is to be submitted within <span class=\"editable\"></span> days.</p>"

	                + "<p>You will receive the updated details of the freehold amount within 30 days. Your application will be finalized and processed with you as per the policy under Bihar Public Land Encroachment Act, 1956.</p>"

	                + "<p>Once you have submitted the full payment (75% of the freehold amount), you shall be required to sign a conveyance deed and deposit the registration fee/stamp duty on the same.</p>"

	                + "<p class=\"signature\">Yours Sincerely,<br><br>"
	                + "<span class=\"bold\">(Collector)</span></p>"

	                + "<p>Date: <span class=\"editable\">" + submissionDate + "</span></p>"
	                + "<p>Place: <span class=\"editable\"></span></p>"

	                + "</body>"
	                + "</html>";

	        try {
	            // Specify the output file path
	        	
	        	 DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
	             String currentMinute = LocalDateTime.now().format(fileNameFormatter);

	             // Define the file name for the PDF, adding current time as part of the name
	             String pdfFilePath = FILE_STORAGE_PATHE + "FormANoticeE_" + userName + "_" + currentMinute + ".pdf";
	            DocumentEntity enNoticeRelease=new DocumentEntity();
	            enNoticeRelease.setDocumentType("NoticeE");

	            enNoticeRelease.setDocumentName("NoticeE");
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
	}

	@Override
	public List<Admission> getdismisObjectcollector(String userId) {
		List<Admission> admission=admissionRepo.findDismissStatusByUserIdfreeHold(Long.valueOf(userId));
		return admission;
	}

	@Override
	public Admission getobjectionId(NewObjection objid) {
		// TODO Auto-generated method stub
		Admission admission=admissionRepo.findByNewObjection(objid);
		return admission;
	}

	@Override
	public List<UserEntity> findByDistrict(String district) {
		 List<UserEntity> user = new ArrayList<>(); // List to hold filtered users
		    try {
		        List<UserEntity> entity = repository.findByDistrict(district); // Fetch all users
		        
		        return entity;
		        
		    } catch (Exception e) {
		        e.printStackTrace(); // Handle exception (you could log it in production)
		    }
			return user;
	}	
	


}
