package com.bor.rcms.service;

import java.util.List;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.Admission;
import com.bor.rcms.entity.CaseCollector;
import com.bor.rcms.resonse.CauselistSpecialOfficer;

public interface CollectorService {

	String updateStatuscollector(OfficerStatusVo statusvo);

	List<CaseCollector> getAmitdatacollector(String district);

	List<CaseCollector> getAdmitcaseorHearingCaseCollector(String hearingDate);

	List<CaseCollector> getAdmitcaseorupdateCasHearingColector(String hearingDate, String caseId);

	CauselistSpecialOfficer modifycauseCOllector(String caseNo, String dateOfHearing, String time, String reason,
			String caseClass, String action);

}
