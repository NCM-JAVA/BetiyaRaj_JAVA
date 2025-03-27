package com.bor.rcms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.Mis;
import com.bor.rcms.entity.NewObjection;
import com.bor.rcms.response.ObjectionStatus;

public interface ObjectionService {

	List<NewObjection> findAll();
	Optional<NewObjection> findbyId(Long obId);
	String upadateStatus(OfficerStatusVo statusvo);
	NewObjection savedata(NewObjection entity);
	List<NewObjection> getObjectionsByUserId(Long userId);
	List<NewObjection> findpending(String district);
	ObjectionStatus getObjectionsdetials(Long userIds);
	String submitObjection(NewObjection objection, MultipartFile[] files, String username, String documentTypes);
	List<NewObjection> findaproved();
	Mis findmistatus(String objId);
	DocumentEntity getObjectionsDocumentdetials(Long obId);
	NewObjection getObjectionsId(Long objids);
	ObjectionStatus ObjectionsdetialsPending(String userid, String district);
	List<NewObjection> getdismisObject(String userId);
	List<Admission> getAdmitcaseorHearing(String hearingDate);
	NewObjection hearingDatechange(String hearingDate, String caseId);
	List<Admission> getAdmitcaseorupdateCasHearing(String hearingDate, String caseId);
	Admission modifycause(String caseNo, String dateOfHearing, String time, String reason, String caseClass,
			String action);
	List<Admission> getfindDismis();
	Admission findcaseForFreehold(String caseId);
	NewObjection sublitApeal(String caseId, String remark);

}
