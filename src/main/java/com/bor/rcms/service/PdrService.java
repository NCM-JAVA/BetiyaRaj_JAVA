package com.bor.rcms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.ExceptionHand.ResponseSet;
import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.CourtReq;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.response.StatusRes;

public interface PdrService {

	String submitRequisition(FileRequeistion requisition, MultipartFile[] files, String username, String documentTypes);

	List<FileRequeistion> findpending(String district);

	Object findbyId(String obId);

	String upadateStatus(OfficerStatusVo statusvo);

	FileRequeistion savecaseDetails(CaseNotes casenotes);

	List<FileRequeistion> findAdmit(String district);

	String addCourt(CourtReq courtReq);

	List<CourtReq> addCourtlistShow(Long userId);

	ResponseEntity<?> noticeGenerate(String selectForm, String reqId);

	String caseTransfer(List<String> reqId, List<String> nouserId);

	List<FileRequeistion> findpendingNom(String userId);

	List<FileRequeistion> findAllByuserId(String userId);

	FileRequeistion findBydebatorId(String debtorId);

	List<String> findSlotTime(String date);

	List<CertificatOfficer> findByReqId(String caseId, String caseDate);

}
