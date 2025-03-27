package com.bor.rcms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.repository.CaseCollectorRepo;
import com.bor.rcms.repository.NewObjectionRepo;
import com.bor.rcms.resonse.CauselistSpecialOfficer;

@Service
@Transactional
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private NewObjectionRepo newObjectionRepo;
    
    @Autowired
    private CaseCollectorRepo caseCollectorRepo;

    @Override
    public String updateStatuscollector(OfficerStatusVo statusvo) {
        // Find the NewObjection entity
        NewObjection objection = newObjectionRepo.findById(statusvo.getObjId()).orElse(null);

        if (objection == null) {
            return "Objection not found!";
        }
        if ("Freehold".equals(statusvo.getStatus()))
        {

        	Admission admission1=objection.getAdmission();
             admission1.setStatusCollector("Admit");
          //   admission1.setFreehold(statusvo.getStatus());
             objection.setAdmission(admission1);
            CaseCollector admission = new CaseCollector();
            admission.setAdmissionDate(admission.getAdmissionDate());
            admission.setAdmissionTime(statusvo.getAdmisionTime());
            admission.setAffidavitDate(statusvo.getAffedefitDate());

         //  admission.setAction(statusvo.geta);
            String caseID = generateCollectorCase(objection);
            admission.setCollectorCase(caseID);

            // Update Objection details
           // objection.setStatus("CollectorAdmit");
            objection.setCaseCollector(admission);
            //objection.setStatus_officer("Collector");
            objection.setStatusCollector("Admit");
            objection.setCollectorFreehold("Freehold");
            admission.setNewObjection(objection);
            admission.setUserId(objection.getUserId());
            admission.setHearingDate(statusvo.getAdmissionDate());
         //   admission.setStatus("Admit");
            admission.setObjectioId(objection.getTokenNo());
            //   admission.setStatus("Admit");

            // Create a new Mis if usertype is Collector
            if ("Collector".equals(statusvo.getUsertype())) {
              //  Mis mis = new Mis();
                
                try {
                	
                //mis.setCollector(caseID);
                }
                catch (Exception e) {
					// TODO: handle exception
                	e.printStackTrace();
				}
                //mis.setCollectorRemarks(statusvo.getStatus());
                //mis.setObjId(objection.getObjectionId().toString());
            //    mis.setNewObjection(objection);
              //  objection.setMis(mis);  // Set the mis to the objection

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

        if ("Admit".equals(statusvo.getStatus())) {
            // Create a new CaseCollector
        	
        	Admission admission1=objection.getAdmission();
             admission1.setStatusCollector("Admit");
             objection.setAdmission(admission1);
            CaseCollector admission = new CaseCollector();
            admission.setAdmissionDate(admission.getAdmissionDate());
            admission.setAdmissionTime(statusvo.getAdmisionTime());
            admission.setAffidavitDate(statusvo.getAffedefitDate());

         //  admission.setAction(statusvo.geta);
            String caseID = generateCollectorCase(objection);
            admission.setCollectorCase(caseID);

            // Update Objection details
           // objection.setStatus("CollectorAdmit");
            objection.setCaseCollector(admission);
            //objection.setStatus_officer("Collector");
            objection.setStatusCollector("Admit");;
            admission.setNewObjection(objection);
            admission.setUserId(objection.getUserId());
            admission.setHearingDate(statusvo.getAdmissionDate());
            admission.setStatus("Admit");
            admission.setObjectioId(objection.getTokenNo());
            //   admission.setStatus("Admit");

            // Create a new Mis if usertype is Collector
            if ("Collector".equals(statusvo.getUsertype())) {
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

    // Method to generate a collector case ID
    public String generateCollectorCase(NewObjection objection) {
    	  String district = objection.getDistrictName();  // Assuming district is in the entity
  	    String year = LocalDate.now().getYear() % 100 < 10 
  	                    ? "0" + (LocalDate.now().getYear() % 100) 
  	                    : Integer.toString(LocalDate.now().getYear() % 100);  // Format as two-digit yeartry {
  	    CaseCollector caseCollector=new CaseCollector();
  	    
  	    try {
  		int initial = 0001;
  	//	int currentYear = Year.now().getValue();
  	    String currentYear = LocalDate.now().getYear() % 100 < 10 ? "0" + (LocalDate.now().getYear() % 100) : Integer.toString(LocalDate.now().getYear() % 100);  // Two-digit year

  		//String caseId = user.getCaseId();
  		// Fetch last inserted user
  		
  		Optional<CaseCollector> lastInsertedUser = caseCollectorRepo.findTopByDistrictOrderByCurrentDateDesc(district);
  		if (lastInsertedUser.isPresent()) {
  			String caseId2 = lastInsertedUser.get().getCollectorCase();
  			String[] parts = caseId2.split("-");  // Splitting by '-'
  			int caseNumber = Integer.parseInt(parts[parts.length - 1]);
  			int finalValue = caseNumber +1;
  			String formattedValue = String.format("%04d", finalValue);
  			caseCollector.setCollectorCase("Case-COLL-"+district+"-"+year+"-"+formattedValue);
  		//	System.out.println("Last inserted user: " + lastInsertedUser.get());
  		} else {
  			String formattedValue = String.format("%04d", initial);
  			caseCollector.setCollectorCase("Case-COLL-"+district+"-"+year+"-"+formattedValue);
  		}
  	//	System.out.println("last result--------->" + lastInsertedUser);

       //   User savedUser = caseIdRepository.save(user);
  		 return caseCollector.getCollectorCase();  // Fixed this line
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
  	return null; // Handle exception properlying.format("Case-COLL-%s-%s-%05d", district, year, nextSeq);  // Example format: Case-COLL-GAYA-24-00001
    }

	@Override
	public List<CaseCollector> getAmitdatacollector() {
		List<CaseCollector>  admissions=caseCollectorRepo.findAlladmit();
		return admissions;
	}

	@Override
	public List<CaseCollector> getAdmitcaseorHearingCaseCollector(String hearingDate) {
		List<CaseCollector> admission=caseCollectorRepo.findByHearingDateAndStatusNotRejectOrTransfer(hearingDate);
		return admission ;
	}

	@Override
	public List<CaseCollector> getAdmitcaseorupdateCasHearingColector(String hearingDate, String caseId) {
		List<CaseCollector> admission=caseCollectorRepo.findByHearingDateAndStatusNotRejectOrTransfer(hearingDate);
		
		
		
		List<CaseCollector> admissionlist=new ArrayList<CaseCollector>();

		for(CaseCollector admission2 :admission)
		{
			if(admission2.getCaseCollectorId().equals(caseId))
			{
				admissionlist.add(admission2);
			}
		}

		return admissionlist;
		
	}

	@Override
	public CauselistSpecialOfficer modifycauseCOllector(String caseNo, String dateOfHearing, String time, String reason,
			String caseClass, String action) {
		CaseCollector admission1=caseCollectorRepo.findByCaseCollectorId(caseNo);
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
	
        
		return null;
	}

}
