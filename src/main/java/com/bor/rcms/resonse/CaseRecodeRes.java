package com.bor.rcms.resonse;

public class CaseRecodeRes {
	
	private String  caseId;
	private String  reqId;

	private String  defaulterName;

	private String  totalAmmount;

	private String  demandAmmount;

	private String  action;

	private String  classe;

	private String  hiringDate;

	private String  caseStatus;
	
	private String holderName;
	private String granterName;
	
	private String fillingDate;
	private String dismisNdDisposesd;
	private String hearingTime;
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getDefaulterName() {
		return defaulterName;
	}
	public void setDefaulterName(String defaulterName) {
		this.defaulterName = defaulterName;
	}
	public String getTotalAmmount() {
		return totalAmmount;
	}
	public void setTotalAmmount(String totalAmmount) {
		this.totalAmmount = totalAmmount;
	}
	public String getDemandAmmount() {
		return demandAmmount;
	}
	public void setDemandAmmount(String demandAmmount) {
		this.demandAmmount = demandAmmount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getHiringDate() {
		return hiringDate;
	}
	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getGranterName() {
		return granterName;
	}
	public void setGranterName(String granterName) {
		this.granterName = granterName;
	}
	public String getFillingDate() {
		return fillingDate;
	}
	public void setFillingDate(String fillingDate) {
		this.fillingDate = fillingDate;
	}
	public String getDismisNdDisposesd() {
		return dismisNdDisposesd;
	}
	public void setDismisNdDisposesd(String dismisNdDisposesd) {
		this.dismisNdDisposesd = dismisNdDisposesd;
	}
	public String getHearingTime() {
		return hearingTime;
	}
	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}
	@Override
	public String toString() {
		return "CaseRecodeRes [caseId=" + caseId + ", reqId=" + reqId + ", defaulterName=" + defaulterName
				+ ", totalAmmount=" + totalAmmount + ", demandAmmount=" + demandAmmount + ", action=" + action
				+ ", classe=" + classe + ", hiringDate=" + hiringDate + ", caseStatus=" + caseStatus + ", holderName="
				+ holderName + ", granterName=" + granterName + ", fillingDate=" + fillingDate + ", dismisNdDisposesd="
				+ dismisNdDisposesd + ", hearingTime=" + hearingTime + "]";
	}
	public CaseRecodeRes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	

}
