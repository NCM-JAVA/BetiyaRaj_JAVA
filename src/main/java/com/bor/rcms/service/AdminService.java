package com.bor.rcms.service;

import java.util.List;

import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.entity.UserEntity;

public interface AdminService {

	NewObjection savecaseDetails(CaseNotes casenotes);

	NewObjection getcaseNOtes(String objid);

	List<UserEntity> userdata(String officer);

	Admission findByCaseId(String caseId);

	List<Admission> getdismisObject(String userId);

	NewObjection savecaseDetailscollector(CaseNotes casenotes);

	List<Admission> getdismisObjectcollector(String userId);


	Admission getobjectionId(NewObjection objid);

}
