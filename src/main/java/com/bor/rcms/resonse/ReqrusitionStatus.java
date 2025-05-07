package com.bor.rcms.resonse;

public class ReqrusitionStatus {
	private String  caseId;
	private String  reqId;

	private String  defaulterName;

	private String  totalAmmount;

	private String  demandAmmount;

	private String  action;

	private String  classe;

	private String  hiringDate;

	private String  caseStatus;

	

	@Override
	public String toString() {
		return "ReqrusitionStatus [caseId=" + caseId + ", reqId=" + reqId + ", defaulterName=" + defaulterName
				+ ", totalAmmount=" + totalAmmount + ", demandAmmount=" + demandAmmount + ", action=" + action
				+ ", classe=" + classe + ", hiringDate=" + hiringDate + ", caseStatus=" + caseStatus + "]";
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
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

	

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public ReqrusitionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
