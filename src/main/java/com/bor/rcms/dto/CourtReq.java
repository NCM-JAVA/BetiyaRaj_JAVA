package com.bor.rcms.dto;

public class CourtReq {
	private String officeDetails;
	
	private String officeName;

	private String officerEmail;

	private String officeMobile;
	
	private String status;
	
	private String  password;
	
	private Long  userId;
	
	private String assignUSer;
	
	private String updaStatus;

	
	private Long  courtId;

	private String role;
	

	public String getAssignUSer() {
		return assignUSer;
	}

	public void setAssignUSer(String assignUSer) {
		this.assignUSer = assignUSer;
	}

	public String getOfficeDetails() {
		return officeDetails;
	}

	public void setOfficeDetails(String officeDetails) {
		this.officeDetails = officeDetails;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficerEmail() {
		return officerEmail;
	}

	public void setOfficerEmail(String officerEmail) {
		this.officerEmail = officerEmail;
	}

	public String getOfficeMobile() {
		return officeMobile;
	}

	public void setOfficeMobile(String officeMobile) {
		this.officeMobile = officeMobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUpdaStatus() {
		return updaStatus;
	}

	public void setUpdaStatus(String updaStatus) {
		this.updaStatus = updaStatus;
	}

	public CourtReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCourtId() {
		return courtId;
	}

	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}

	@Override
	public String toString() {
		return "CourtReq [officeDetails=" + officeDetails + ", officeName=" + officeName + ", officerEmail="
				+ officerEmail + ", officeMobile=" + officeMobile + ", status=" + status + ", password=" + password
				+ ", userId=" + userId + ", assignUSer=" + assignUSer + ", updaStatus=" + updaStatus + ", courtId="
				+ courtId + ", role=" + role + "]";
	}

	
	




	
	
	

}
