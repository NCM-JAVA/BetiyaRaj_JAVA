package com.bor.rcms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CaseNotes {
	
    private String objId;
    
    @NotBlank(message = "Notes are required")
    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String caseNotes;
    
    @NotBlank(message = "Action is required")
    private String action;
    @NotBlank(message = "field is required")
    private String nextHearingDate;
    @NotBlank(message = "field is required")
    private String time;
    private String caseClass;
    
    @NotBlank(message = "Select form is required")
	private String selectForm;

	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNextHearingDate() {
		return nextHearingDate;
	}
	public void setNextHearingDate(String nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCaseClass() {
		return caseClass;
	}
	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}
	public void setCaseNotes(String caseNotes) {
		this.caseNotes = caseNotes;
	}
	public String getCaseNotes() {
		return caseNotes;
	}
	public String getSelectForm() {
		return selectForm;
	}
	public void setSelectForm(String selectForm) {
		this.selectForm = selectForm;
	}
	@Override
	public String toString() {
		return "CaseNotes [objId=" + objId + ", caseNotes=" + caseNotes + ", action=" + action + ", nextHearingDate="
				+ nextHearingDate + ", time=" + time + ", caseClass=" + caseClass + ", selectForm=" + selectForm + "]";
	}
	public CaseNotes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CaseNotes(String objId, String caseNotes, String action, String nextHearingDate, String time,
			String caseClass, String selectForm) {
		super();
		this.objId = objId;
		this.caseNotes = caseNotes;
		this.action = action;
		this.nextHearingDate = nextHearingDate;
		this.time = time;
		this.caseClass = caseClass;
		this.selectForm = selectForm;
	}
	
	
}