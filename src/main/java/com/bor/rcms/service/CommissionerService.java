package com.bor.rcms.service;

import java.util.List;

import com.bor.rcms.dto.OfficerStatusVo;
import com.bor.rcms.entity.CaseCommissioner;

public interface CommissionerService {

	String updateStatuscommsioner(OfficerStatusVo statusvo);

	List<CaseCommissioner> getAmitdatacommissioner();

}
