package com.bor.rcms.dto;

import java.util.Date;
import java.util.List;

public class CaseStatusDTO {
    private String caseId;
    private String currentStatus;
    private Date lastUpdated;
    private String nextHearingDate;
    private String nextHearingTime;
    private String currentOfficer;
   // private List<CaseHistoryItem> history;
    private List<DocumentDTO> documents;
    
    public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getNextHearingDate() {
		return nextHearingDate;
	}
	public void setNextHearingDate(String nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}
	public String getNextHearingTime() {
		return nextHearingTime;
	}
	public void setNextHearingTime(String nextHearingTime) {
		this.nextHearingTime = nextHearingTime;
	}
	public String getCurrentOfficer() {
		return currentOfficer;
	}
	public void setCurrentOfficer(String currentOfficer) {
		this.currentOfficer = currentOfficer;
	}
	
	public List<DocumentDTO> getDocuments() {
		return documents;
	}
	public void setDocuments(List<DocumentDTO> documents) {
		this.documents = documents;
	}
	

    // Getters and setters
}

