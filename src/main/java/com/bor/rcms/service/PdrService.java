package com.bor.rcms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bor.rcms.dto.CaseNotes;
import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.CertificatOfficer;
import com.bor.rcms.entity.FileRequeistion;

public interface PdrService {

	String submitRequisition(FileRequeistion requisition, MultipartFile[] files, String username, String documentTypes);

	List<FileRequeistion> findpending(String district);

	Object findbyId(String obId);

	String upadateStatus(OfficerStatusVo statusvo);

	FileRequeistion savecaseDetails(CaseNotes casenotes);

	List<FileRequeistion> findAdmit(String district);

}
