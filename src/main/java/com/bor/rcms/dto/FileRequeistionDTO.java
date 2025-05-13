package com.bor.rcms.dto;

import javax.validation.constraints.*;

import javax.validation.constraints.Size;

public class FileRequeistionDTO {

	@NotBlank(message = "Case ID is required")
//	@Size(min = 3, max = 30, message = "Case ID must be 3-30 characters")
	private String caseId;

	@NotBlank(message = "Requisition ID is required")
	private String requeistionId;

	@NotBlank(message = "Outstanding amount is required")
	private String totalOutstandingAmmount;

	@NotBlank(message = "Interest rate is required")
	private String totalInterestRate;

	@NotBlank(message = "Interest due form is required")
	private String interestDueForm;

	@NotBlank(message = "Court fee is required")
	private String totalCourtFee;

	@NotBlank(message = "Miscellaneous fee is required")
	private String missllenousFee;

	@NotBlank(message = "Paid court fee is required")
	private String paidCourFee;

	@NotBlank(message = "Total demand is required")
	private String totalDemand;

	@NotBlank(message = "Financial year is required")
	private String financialYear;

	@NotBlank(message = "District name is required")
	private String districtName;

	@NotBlank(message = "Current date is required")
	private String currentDate;

	@NotBlank(message = "Update date is required")
	private String updateDate;

	@NotBlank(message = "Status is required")
	private String status;

	@NotBlank(message = "Reason is required")
	private String reason;

   private String debatorName;



	private String userName; //optional 
	public String getRequeistionId() {
		return requeistionId;
	}

	public void setRequeistionId(String requeistionId) {
		this.requeistionId = requeistionId;
	}

	public String getTotalOutstandingAmmount() {
		return totalOutstandingAmmount;
	}

	public void setTotalOutstandingAmmount(String totalOutstandingAmmount) {
		this.totalOutstandingAmmount = totalOutstandingAmmount;
	}

	public String getTotalInterestRate() {
		return totalInterestRate;
	}

	public void setTotalInterestRate(String totalInterestRate) {
		this.totalInterestRate = totalInterestRate;
	}

	public String getInterestDueForm() {
		return interestDueForm;
	}

	public void setInterestDueForm(String interestDueForm) {
		this.interestDueForm = interestDueForm;
	}

	public String getTotalCourtFee() {
		return totalCourtFee;
	}

	public void setTotalCourtFee(String totalCourtFee) {
		this.totalCourtFee = totalCourtFee;
	}

	public String getMissllenousFee() {
		return missllenousFee;
	}

	public void setMissllenousFee(String missllenousFee) {
		this.missllenousFee = missllenousFee;
	}

	public String getPaidCourFee() {
		return paidCourFee;
	}

	public void setPaidCourFee(String paidCourFee) {
		this.paidCourFee = paidCourFee;
	}

	public String getTotalDemand() {
		return totalDemand;
	}

	public void setTotalDemand(String totalDemand) {
		this.totalDemand = totalDemand;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	
	}
	
	
	public String getDebatorName() {
		return debatorName;
	}

	public void setDebatorName(String debatorName) {
		this.debatorName = debatorName;
	}

	@Override
	public String toString() {
		return "FileRequeistionDTO [caseId=" + caseId + ", requeistionId=" + requeistionId
				+ ", totalOutstandingAmmount=" + totalOutstandingAmmount + ", totalInterestRate=" + totalInterestRate
				+ ", interestDueForm=" + interestDueForm + ", totalCourtFee=" + totalCourtFee + ", missllenousFee="
				+ missllenousFee + ", paidCourFee=" + paidCourFee + ", totalDemand=" + totalDemand + ", financialYear="
				+ financialYear + ", districtName=" + districtName + ", currentDate=" + currentDate + ", updateDate="
				+ updateDate + ", status=" + status + ", reason=" + reason + ", debatorName=" + debatorName
				+ ", userName=" + userName + "]";
	}

	

}
