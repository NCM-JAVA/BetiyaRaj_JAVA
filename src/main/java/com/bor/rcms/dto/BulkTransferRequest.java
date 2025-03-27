package com.bor.rcms.dto;

import java.util.List;

public class BulkTransferRequest {
    
    private String hearingDate;
    private List<String> caseIds;
    
    private String userType;
	public String getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}
	public List<String> getCaseIds() {
		return caseIds;
	}
	public void setCaseIds(List<String> caseIds) {
		this.caseIds = caseIds;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return "BulkTransferRequest [hearingDate=" + hearingDate + ", caseIds=" + caseIds + ", userType=" + userType
				+ "]";
	}
	

    
    
}