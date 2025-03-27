package com.bor.rcms.dto;

public class UpdateCaseProceding {
	private String objName;
	private String nameofrespondent;
	private String action;
	private String caseclass;
	
	private String reasonUpdate;

	
	private String nextHearing;
	private String HearingTime;
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getNameofrespondent() {
		return nameofrespondent;
	}
	public void setNameofrespondent(String nameofrespondent) {
		this.nameofrespondent = nameofrespondent;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCaseclass() {
		return caseclass;
	}
	public void setCaseclass(String caseclass) {
		this.caseclass = caseclass;
	}
	public String getReasonUpdate() {
		return reasonUpdate;
	}
	public void setReasonUpdate(String reasonUpdate) {
		this.reasonUpdate = reasonUpdate;
	}
	public String getNextHearing() {
		return nextHearing;
	}
	public void setNextHearing(String nextHearing) {
		this.nextHearing = nextHearing;
	}
	public String getHearingTime() {
		return HearingTime;
	}
	public void setHearingTime(String hearingTime) {
		HearingTime = hearingTime;
	}
	@Override
	public String toString() {
		return "UpdateCaseProceding [objName=" + objName + ", nameofrespondent=" + nameofrespondent + ", action="
				+ action + ", caseclass=" + caseclass + ", reasonUpdate=" + reasonUpdate + ", nextHearing="
				+ nextHearing + ", HearingTime=" + HearingTime + "]";
	}
	public UpdateCaseProceding(String objName, String nameofrespondent, String action, String caseclass,
			String reasonUpdate, String nextHearing, String hearingTime) {
		super();
		this.objName = objName;
		this.nameofrespondent = nameofrespondent;
		this.action = action;
		this.caseclass = caseclass;
		this.reasonUpdate = reasonUpdate;
		this.nextHearing = nextHearing;
		HearingTime = hearingTime;
	}
	public UpdateCaseProceding() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
