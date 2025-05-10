package com.bor.rcms.service;

import java.beans.Beans;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.AppealCaseCollector;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.repository.AdmissionRepo;
import com.bor.rcms.repository.AppealCollectorRepo;
import com.bor.rcms.repository.DocumentRepository;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.resonse.Casesinform;
import com.bor.rcms.response.ObjectionDetails;

@Service
@Transactional
public class NoticeServiceImpl  implements NoticeService{
	
	 @Autowired
	    private DocumentRepository documentRepository;
	 @Autowired
	 private AdmissionRepo admissionRepo;
	 
	 @Autowired 
	 private   AppealCollectorRepo  AppealCaseCollectorRepo;
	 @Autowired 
	 private NewObjectionRepo newObjectionRepo;
	 

	@Override
	public String noticedgenerateformB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppealCaseCollector submitApeal(String caseId, String remark) {
	    try {
	        if (caseId == null || caseId.isEmpty()) {
	            throw new IllegalArgumentException("Case ID cannot be null or empty");
	        }
	        if (remark == null || remark.isEmpty()) {
	            throw new IllegalArgumentException("Remark cannot be null or empty");
	        }

	        Admission admission = admissionRepo.findByAdmisionCase(caseId);
	        		//findByAdmissionId(Long.valueOf(caseId));
	        if (admission == null) {
	            throw new IllegalArgumentException("No admission found with the given caseId: " + caseId);
	        }
	        NewObjection newObjection = admission.getNewObjection();
	        if (newObjection == null) {
	            throw new IllegalArgumentException("No new objection found for this admission");
	        }
	        AppealCaseCollector AppealCaseCollector = new AppealCaseCollector();
	        
	        AppealCaseCollector.setUserId(newObjection.getUserId());
	      //  String  caseid=generateCollectorCase(newObjection);
	      //  AppealCaseCollector.setCollectorCase(caseid);
	        AppealCaseCollector.setObjectionId(newObjection); 
	        AppealCaseCollector.setAppealremark(remark);
	        AppealCaseCollector.setStatus("Appeal");
	        AppealCaseCollector.setStatus_officer("collector");

	        AppealCaseCollector AppealCaseCollectorSave = AppealCaseCollectorRepo.save(AppealCaseCollector);

	        newObjection.setStatus("AppealCollector");

//	        Mis mis = newObjection.getMis();
//	        if (mis == null) {
//	            throw new IllegalArgumentException("Mis object is null for this newObjection");
//	        }
//
//	        mis.setCollector("Appeal");
//	        mis.setCaseId(String.valueOf(AppealCaseCollectorSave.getCaseId())); 
//
//	        newObjection.setMis(mis);
	        NewObjection newObjectionSave = newObjectionRepo.save(newObjection);

	        return AppealCaseCollectorSave;

	    } catch (IllegalArgumentException e) {
	        System.err.println("Validation error: " + e.getMessage());
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	///Collector

	@Override
	public Casesinform casesinform(String district) {
		// TODO Auto-generated method stub
		try {
			Casesinform casesinform =new Casesinform();
			List<Admission> admission=admissionRepo.findAlldismis(district);
			
			Long totalAdmission=(long) admission.size();
			
			List<AppealCaseCollector>  AppealCaseCollectors=AppealCaseCollectorRepo.findAllByDistrict(district);
			Long apealcase=(long) AppealCaseCollectors.size();

			
               Long totalcases=totalAdmission+apealcase;
               
               casesinform.setTotalCase(String.valueOf(totalcases));
               casesinform.setTotalAppeal(String.valueOf(apealcase));
               
               List<NewObjection> list=new ArrayList<NewObjection>();
               
               List<ObjectionDetails> finalobj=new ArrayList<ObjectionDetails>();

               list=newObjectionRepo.findcollerstatus();
               
               for(NewObjection newob:list)
               {
            	   ObjectionDetails objectonDetails=new ObjectionDetails();
                   BeanUtils.copyProperties(newob, objectonDetails);
            	   Admission  admissionresult=newob.getAdmission();
            	   objectonDetails.setTokenNo(admissionresult.getAdmisionCase());
            	   objectonDetails.setUserId(newob.getUserId());
            	   
            	   finalobj.add(objectonDetails);
               }
               
               casesinform.setListobjctdetails(finalobj);
               
               return casesinform;
		}
		catch(Exception e)
		{
			
		}
		return null;
	}

	@Override
	public Casesinform casesinformDivv() {
		// TODO Auto-generated method stub
		
		try {
			Casesinform casesinform =new Casesinform();
			List<Admission> admission=admissionRepo.findAlldivdetails();
			
			Long totaldivcase=(long) admission.size();
			
		
//			
//               Long totalcases=totalAdmission+apealcase;
//               
//               casesinform.setTotalCase(String.valueOf(totalcases));
//               casesinform.setTotalAppeal(String.valueOf(apealcase));
//               
//               List<NewObjection> list=new ArrayList<NewObjection>();
//               list=newObjectionRepo.findcollerstatus();
//               casesinform.setListobjct(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	

//	public String generateCollectorCase(NewObjection objection) {
//	    String district = objection.getDistrictName().toUpperCase();  // Assuming the district name is a field in the NewObjection entity
//	    String year = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year
//
//	    // Query the database to find the latest sequence number for this district and year
//	    String lastToken = AppealCaseCollectorRepo.findHighestCollectorCase();
//	    		//findHighestTokenForDistrictAndYear(district, year);
//	    // @Query("SELECT MAX(o.admisionCase) FROM Admission o")
//
//	    // If no token exists for this district and year, start from 1
//	    int nextSeq = 1;
//	    if (lastToken != null) {
//	        // Extract the sequence number from the last token
//	        String[] parts = lastToken.split("-");
//	        if (parts.length == 4) {
//	            try {
//	                nextSeq = Integer.parseInt(parts[3]) + 1;  // Increment the sequence number
//	            } catch (NumberFormatException e) {
//	                nextSeq = 1;  // In case the sequence part is invalid
//	            }
//	        }
//	    }
//
//	    // Return the formatted objection ID
//	    return String.format("Case-COLL-%s-%s-%05d", district, year, nextSeq);  // Format as OBJ-DIST-YY-SEQ (e.g., OBJ-GAYA-24-00001)
//	}

	
	
	@Override
	public Casesinform casesinformCollector(String userid) {

		try {
			Casesinform casesinform =new Casesinform();
			
			List<Admission> admission=admissionRepo.findAlldivdetailsById();
			
			Long totaldivcase=(long) admission.size();
			
			casesinform.setTotalCase(String.valueOf(totaldivcase));
			
			List<NewObjection> list=new ArrayList<NewObjection>();
			
			for(Admission admission2:admission)
			{
				NewObjection newObjection=admission2.getNewObjection();
				list.add(newObjection);
			}
			casesinform.setListobjct(list);
			return casesinform;
		
//			
//               Long totalcases=totalAdmission+apealcase;
//               
//               casesinform.setTotalCase(String.valueOf(totalcases));
//               casesinform.setTotalAppeal(String.valueOf(apealcase));
//               
//               List<NewObjection> list=new ArrayList<NewObjection>();
//               list=newObjectionRepo.findcollerstatus();
//               casesinform.setListobjct(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}


}
