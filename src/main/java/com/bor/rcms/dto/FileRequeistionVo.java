package com.bor.rcms.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FileRequeistionVo {

	private List<DebatorVo> debatorVos;
	// Guarantor Details
	@NotBlank(message = "Name required")
	@Size(max = 50, message = "Max 50 chars")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
	private String guarantorName;

	@NotBlank(message = "Address required")
	private String guarantorAddress;

	@NotBlank(message = "Address line 1 required")
	private String guarantorAddress1;
//=======
//	    private String guarantorPhoneNumber;
//	    private String guarantorStatePhoneNumber;
//	    private String guarantorEmail;
//	    private String userId;
//	    
//	    private String reason;
//	    
//	    private String guarantorfatherNames;
//	    private  String guarantorsubDivision;
//	    private  String guarantorcircle;
//	    private  String guarantorpolicestation;
//>>>>>>> main

	@NotBlank(message = "Address line 2 required")
	private String guarantorAddress2;

	@NotBlank(message = "State required")
	private String guarantorState;

	@NotBlank(message = "City required")
	private String guarantorCity;

	@NotBlank(message = "District required")
	//@Size(max = 50, message = "Max 50 chars")
	private String guarantorDistrict;

	@NotBlank(message = "Pincode required")
	@Pattern(regexp = "^[0-9]{6}$", message = "Invalid pincode (6 digits)")
	private String guarantorPincode;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Phone number required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid PhoneNumber ")
	private String guarantorPhoneNumber;

	private String guarantorStatePhoneNumber;

	//@Email(message = "Invalid email format")
	private String guarantorEmail;

	@NotBlank(message = "User ID required")
	private String userId;

	@NotBlank(message = "Reason required")
	private String reason;

	@NotBlank(message = "Father's name required")
	private String guarantorfatherNames;

	@NotBlank(message = "Subdivision required")
	private String guarantorsubDivision;

	@NotBlank(message = "Circle required")
	private String guarantorcircle;

	@NotBlank(message = "Police station required")
	private String guarantorpolicestation;

	// Common Fields
	private String createdDate;
	@NotBlank
	//@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format: YYYY-MM-DD")
	private String modifiedDate;
	@NotBlank
	private String status;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid amount format")
	private String totalOutstandingAmmount;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid rate format")
	private String totalInterestRate;
	@NotBlank
	private String interestDueForm;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid fee format")
	private String totalCourtFee;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid fee format")
	private String missllenousFee;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid fee format")
	private String paidCourFee;
	@NotBlank
	//@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Invalid amount format")
	private String totalDemand;
	@NotBlank
	//@Pattern(regexp = "^\\d{4}-\\d{4}$", message = "Format: YYYY-YYYY")
	private String financialYear;

	// legal Repersentative
	// private LegalRepersentativeVo
	private List<LegalRepersentativeVo> legalRepersentativeVo;

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

	public List<DebatorVo> getDebatorVos() {
		return debatorVos;
	}

	public void setDebatorVos(List<DebatorVo> debatorVos) {
		this.debatorVos = debatorVos;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public String getGuarantorAddress() {
		return guarantorAddress;
	}

	public void setGuarantorAddress(String guarantorAddress) {
		this.guarantorAddress = guarantorAddress;
	}

	public String getGuarantorAddress1() {
		return guarantorAddress1;
	}

	public void setGuarantorAddress1(String guarantorAddress1) {
		this.guarantorAddress1 = guarantorAddress1;
	}

	public String getGuarantorAddress2() {
		return guarantorAddress2;
	}

	public void setGuarantorAddress2(String guarantorAddress2) {
		this.guarantorAddress2 = guarantorAddress2;
	}

	public String getGuarantorState() {
		return guarantorState;
	}

	public void setGuarantorState(String guarantorState) {
		this.guarantorState = guarantorState;
	}

	public String getGuarantorCity() {
		return guarantorCity;
	}

	public void setGuarantorCity(String guarantorCity) {
		this.guarantorCity = guarantorCity;
	}

	public String getGuarantorDistrict() {
		return guarantorDistrict;
	}

	public void setGuarantorDistrict(String guarantorDistrict) {
		this.guarantorDistrict = guarantorDistrict;
	}

	public String getGuarantorPincode() {
		return guarantorPincode;
	}

	public void setGuarantorPincode(String guarantorPincode) {
		this.guarantorPincode = guarantorPincode;
	}

	public String getGuarantorPhoneNumber() {
		return guarantorPhoneNumber;
	}

	public void setGuarantorPhoneNumber(String guarantorPhoneNumber) {
		this.guarantorPhoneNumber = guarantorPhoneNumber;
	}

	public String getGuarantorStatePhoneNumber() {
		return guarantorStatePhoneNumber;
	}

	public void setGuarantorStatePhoneNumber(String guarantorStatePhoneNumber) {
		this.guarantorStatePhoneNumber = guarantorStatePhoneNumber;
	}

	public String getGuarantorEmail() {
		return guarantorEmail;
	}

	public void setGuarantorEmail(String guarantorEmail) {
		this.guarantorEmail = guarantorEmail;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGuarantorfatherNames() {
		return guarantorfatherNames;
	}

	public void setGuarantorfatherNames(String guarantorfatherNames) {
		this.guarantorfatherNames = guarantorfatherNames;
	}

	public String getGuarantorsubDivision() {
		return guarantorsubDivision;
	}

	public void setGuarantorsubDivision(String guarantorsubDivision) {
		this.guarantorsubDivision = guarantorsubDivision;
	}

	public String getGuarantorcircle() {
		return guarantorcircle;
	}

	public void setGuarantorcircle(String guarantorcircle) {
		this.guarantorcircle = guarantorcircle;
	}

	public String getGuarantorpolicestation() {
		return guarantorpolicestation;
	}

	public void setGuarantorpolicestation(String guarantorpolicestation) {
		this.guarantorpolicestation = guarantorpolicestation;
	}

	public String getreason() {
		return reason;
	}

	public void setreason(String reason) {
		this.reason = reason;
	}

	public List<LegalRepersentativeVo> getLegalRepersentativeVo() {
		return legalRepersentativeVo;
	}

	public void setLegalRepersentativeVo(List<LegalRepersentativeVo> legalRepersentativeVo) {
		this.legalRepersentativeVo = legalRepersentativeVo;
	}

	@Override
	public String toString() {
		return "FileRequeistionVo [debatorVos=" + debatorVos + ", guarantorName=" + guarantorName
				+ ", guarantorAddress=" + guarantorAddress + ", guarantorAddress1=" + guarantorAddress1
				+ ", guarantorAddress2=" + guarantorAddress2 + ", guarantorState=" + guarantorState + ", guarantorCity="
				+ guarantorCity + ", guarantorDistrict=" + guarantorDistrict + ", guarantorPincode=" + guarantorPincode
				+ ", guarantorPhoneNumber=" + guarantorPhoneNumber + ", guarantorStatePhoneNumber="
				+ guarantorStatePhoneNumber + ", guarantorEmail=" + guarantorEmail + ", userId=" + userId + ", reason="
				+ reason + ", guarantorfatherNames=" + guarantorfatherNames + ", guarantorsubDivision="
				+ guarantorsubDivision + ", guarantorcircle=" + guarantorcircle + ", guarantorpolicestation="
				+ guarantorpolicestation + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", status=" + status + ", totalOutstandingAmmount=" + totalOutstandingAmmount + ", totalInterestRate="
				+ totalInterestRate + ", interestDueForm=" + interestDueForm + ", totalCourtFee=" + totalCourtFee
				+ ", missllenousFee=" + missllenousFee + ", paidCourFee=" + paidCourFee + ", totalDemand=" + totalDemand
				+ ", financialYear=" + financialYear + ", legalRepersentativeVo=" + legalRepersentativeVo + "]";
	}

}
