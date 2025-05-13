package com.bor.rcms.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.ExceptionHand.ResponseSet;
import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.CauseVo;
import com.bor.rcms.dto.CommisionaryReq;
import com.bor.rcms.dto.CourtReq;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.DraftSaveCaseProceeding;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.resonse.CaseRecodeRes;
import com.bor.rcms.resonse.ReqrusitionStatus;
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

	String caseTransfer(List<String> reqId, String nouserId);

	List<FileRequeistion> findpendingNom(String userId);

	List<FileRequeistion> findAllByuserId(String userId);

	FileRequeistion findBydebatorId(String debtorId);

	List<String> findSlotTime(String date);

	List<CertificatOfficer> findByReqId(String caseId, String caseDate);

	String upadateCauseStatus(@Valid CauseVo causeVo);

	List<CertificatOfficer> findAllCause(String district);

	List<ReqrusitionStatus> getcaseStatus(String userId);

	StatusRes addDraft(String draft, String caseId);

	DraftSaveCaseProceeding FindDraft(String caseId);

	List<CaseRecodeRes> getcaseRecord(String userId);

	List<CaseRecodeRes> getcaseRecordFilter(String sector, String bank, String department, String branchCode);

	String addcommisionary(CommisionaryReq commisionaryReq);

	List<CommisionaryReq> showcommisionaryList(Long userId);

	StatusRes saveDraft(String draft, String caseId);

}
