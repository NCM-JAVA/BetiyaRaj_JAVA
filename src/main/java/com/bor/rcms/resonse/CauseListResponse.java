package com.bor.rcms.resonse;

public class CauseListResponse {
	
	private String caseId;
	private String holderName;

	private String debtorName;

	private String sector;

	private String demandAmount;

	private String action;
	private String filingDate;

	private String hearingDate;
	private String hearingtime;
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getDebtorName() {
		return debtorName;
	}
	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getDemandAmount() {
		return demandAmount;
	}
	public void setDemandAmount(String demandAmount) {
		this.demandAmount = demandAmount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}
	public String getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}
	public String getHearingtime() {
		return hearingtime;
	}
	public void setHearingtime(String hearingtime) {
		this.hearingtime = hearingtime;
	}
	@Override
	public String toString() {
		return "CauseListResponse [caseId=" + caseId + ", holderName=" + holderName + ", debtorName=" + debtorName
				+ ", sector=" + sector + ", demandAmount=" + demandAmount + ", action=" + action + ", filingDate="
				+ filingDate + ", hearingDate=" + hearingDate + ", hearingtime=" + hearingtime + "]";
	}

	
	


	
}
