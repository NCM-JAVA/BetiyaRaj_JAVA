package com.bor.rcms.dto;

import java.util.List;

import javax.persistence.Column;

public class FileRequeistionVo {
	 
	private List<DebatorVo> debatorVos;

	    // Guarantor Details
	    private String guarantorName;
	    private String guarantorAddress;
	    private String guarantorAddress1;
	    private String guarantorAddress2;
	    private String guarantorState;
	    private String guarantorCity;
	    private String guarantorDistrict;
	    private String guarantorPincode;
	    
	    

	    private String guarantorPhoneNumber;
	    private String guarantorStatePhoneNumber;
	    private String guarantorEmail;
	    private String userId;
	    
	    
	    private String guarantorfatherNames;
	    private  String guarantorsubDivision;
	    private  String guarantorcircle;
	    private  String guarantorpolicestation;

	    // Common Fields
	    private String createdDate;
	    private String modifiedDate;
	    private String status;
	    
	    private String totalOutstandingAmmount;
		  private String totalInterestRate;
		  private String interestDueForm;
		  private String totalCourtFee;
		  private String missllenousFee;
		  private String paidCourFee;
		  private String totalDemand;
		  private String financialYear	 ;   
		  
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
		@Override
		public String toString() {
			return "FileRequeistionVo [debatorVos=" + debatorVos + ", guarantorName=" + guarantorName
					+ ", guarantorAddress=" + guarantorAddress + ", guarantorAddress1=" + guarantorAddress1
					+ ", guarantorAddress2=" + guarantorAddress2 + ", guarantorState=" + guarantorState
					+ ", guarantorCity=" + guarantorCity + ", guarantorDistrict=" + guarantorDistrict
					+ ", guarantorPincode=" + guarantorPincode + ", guarantorPhoneNumber=" + guarantorPhoneNumber
					+ ", guarantorStatePhoneNumber=" + guarantorStatePhoneNumber + ", guarantorEmail=" + guarantorEmail
					+ ", userId=" + userId + ", guarantorfatherNames=" + guarantorfatherNames
					+ ", guarantorsubDivision=" + guarantorsubDivision + ", guarantorcircle=" + guarantorcircle
					+ ", guarantorpolicestation=" + guarantorpolicestation + ", createdDate=" + createdDate
					+ ", modifiedDate=" + modifiedDate + ", status=" + status + ", totalOutstandingAmmount="
					+ totalOutstandingAmmount + ", totalInterestRate=" + totalInterestRate + ", interestDueForm="
					+ interestDueForm + ", totalCourtFee=" + totalCourtFee + ", missllenousFee=" + missllenousFee
					+ ", paidCourFee=" + paidCourFee + ", totalDemand=" + totalDemand + ", financialYear="
					+ financialYear + "]";
		}
		
		
	
		
	    
	    
	    
}
