package com.bor.rcms.service;

import com.bor.rcms.entity.AppealCaseCollector;
import com.bor.rcms.resonse.Casesinform;

public interface NoticeService {

	String noticedgenerateformB();

	AppealCaseCollector submitApeal(String caseId, String remark);

	Casesinform casesinform();

	Casesinform casesinformDivv();

	Casesinform casesinformCollector(String userid);

}
