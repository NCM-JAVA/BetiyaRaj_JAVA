package com.bor.rcms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.entity.CaseCommissioner;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.repository.CommissionerRepo;
import com.bor.rcms.repository.NewObjectionRepo;

@Service
@Transactional
public class CommissionerServiceImpl implements CommissionerService {
	  @Autowired
	    private NewObjectionRepo newObjectionRepo;
	  
	  @Autowired
	    private CommissionerRepo commissionerRepo;
	@Override
	public String updateStatuscommsioner(OfficerStatusVo statusvo) {
		 NewObjection objection = newObjectionRepo.findById(statusvo.getObjId()).orElse(null);

	        if (objection == null) {
	            return "Objection not found!";
	        }
	      

	        if ("Admit".equals(statusvo.getStatus())) {
	            // Create a new CaseCollector
	        	
	        	Admission admission1=objection.getAdmission();
	             admission1.setStatusCommissioner("Admit");;
	             objection.setAdmission(admission1);
	            CaseCommissioner admission = new CaseCommissioner();
	            admission.setAdmissionDate(admission.getAdmissionDate());
	            admission.setAdmissionTime(statusvo.getAdmisionTime());
	            admission.setAffidavitDate(statusvo.getAffedefitDate());

	         //  admission.setAction(statusvo.geta);
	            String caseID = generateCollectorCase(objection);
	            admission.setCommissonerCase(caseID);
	            objection.setCaseCommissioner(admission);
	            objection.setStatusCommissioner("Admit");;
	            admission.setNewObjection(objection);
	            admission.setUserId(objection.getUserId());
	            admission.setHearingDate(statusvo.getAdmissionDate());
	            admission.setStatus("Admit");
	            admission.setObjectioId(objection.getTokenNo());
	            //   admission.setStatus("Admit");

	            // Create a new Mis if usertype is Collector
	            if ("DivCommissioner".equals(statusvo.getUsertype())) {
	             //   Mis mis = new Mis();
	                
	                try {
	                	
	               // mis.setCollector(caseID);
	                }
	                catch (Exception e) {
						// TODO: handle exception
	                	e.printStackTrace();
					}
	                //mis.setCollectorRemarks(statusvo.getStatus());
	                //mis.setObjId(objection.getObjectionId().toString());
	            //    mis.setNewObjection(objection);
	                //objection.setMis(mis);  // Set the mis to the objection

	                // Save the objection with all updates
	                NewObjection savedObjection = newObjectionRepo.save(objection);

	                // Return response
	                if (savedObjection != null) {
	                    return "save";  // Successfully saved
	                } else {
	                    return "error";  // Save failed
	                }
	            }
	        }

	        // In case status isn't "Admit"
	        return null;
	    }


	private String generateCollectorCase(NewObjection objection) {
		  String district = objection.getDistrictName();  // Assuming district is in the entity
	  	    String year = LocalDate.now().getYear() % 100 < 10 
	  	                    ? "0" + (LocalDate.now().getYear() % 100) 
	  	                    : Integer.toString(LocalDate.now().getYear() % 100);  // Format as two-digit yeartry {
	  	    CaseCommissioner caseCollector=new CaseCommissioner();
	  	    
	  	    try {
	  		int initial = 0001;
	  	//	int currentYear = Year.now().getValue();
	  	    String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year

	  		//String caseId = user.getCaseId();
	  		// Fetch last inserted user
	  		
	  		Optional<CaseCollector> lastInsertedUser = commissionerRepo.findTopByDistrictOrderByCurrentDateDesc(district);
	  		if (lastInsertedUser.isPresent()) {
	  			String caseId2 = lastInsertedUser.get().getCollectorCase();
	  			String[] parts = caseId2.split("-");  // Splitting by '-'
	  			int caseNumber = Integer.parseInt(parts[parts.length - 1]);
	  			int finalValue = caseNumber +1;
	  			String formattedValue = String.format("%04d", finalValue);
	  			caseCollector.setCommissonerCase("Case-DC-"+district+"-"+year+"-"+formattedValue);
	  		//	System.out.println("Last inserted user: " + lastInsertedUser.get());
	  		} else {
	  			String formattedValue = String.format("%04d", initial);
	  			caseCollector.setCommissonerCase("Case-DC-"+district+"-"+year+"-"+formattedValue);
	  		}
	  	//	System.out.println("last result--------->" + lastInsertedUser);

	       //   User savedUser = caseIdRepository.save(user);
	  		 return caseCollector.getCommissonerCase();  // Fixed this line
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	  	return null; 
	}


	@Override
	public List<CaseCommissioner> getAmitdatacommissioner() {
		List<CaseCommissioner>  admissions=commissionerRepo.findAlladmit();
		return admissions;
	}
}