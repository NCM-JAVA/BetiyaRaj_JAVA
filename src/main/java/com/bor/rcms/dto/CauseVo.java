package com.bor.rcms.dto;

import javax.validation.constraints.NotBlank;

public class CauseVo {

	 @NotBlank(message = "Case id is required")
	    private String caseId;
    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "Class is required")
    private String classe;

    @NotBlank(message = "Reason is required")
    private String reason;

    @NotBlank(message = "Hearing date is required")
    private String hearinDate;

    @NotBlank(message = "Hearing time is required")
    private String hearingTime;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getHearinDate() {
		return hearinDate;
	}
	public void setHearinDate(String hearinDate) {
		this.hearinDate = hearinDate;
	}
	public String getHearingTime() {
		return hearingTime;
	}
	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Override
	public String toString() {
		return "CauseVo [caseId=" + caseId + ", action=" + action + ", classe=" + classe + ", reason=" + reason
				+ ", hearinDate=" + hearinDate + ", hearingTime=" + hearingTime + "]";
	}
	
	
	
}
