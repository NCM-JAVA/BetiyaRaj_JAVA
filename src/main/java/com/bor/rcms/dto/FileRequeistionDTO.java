package com.bor.rcms.dto;

public class FileRequeistionDTO {

	
	private String caseId;
	 private String requeistionId;
	    private String totalOutstandingAmmount;
	    private String totalInterestRate;
	    private String interestDueForm;
	    private String totalCourtFee;
	    private String missllenousFee;
	    private String paidCourFee;
	    private String totalDemand;
	    private String financialYear;
	    private String districtName;
	    private String currentDate;
	    private String updateDate;
	    private String status;
	    private String reason;
	    private String userName; // Optional: if you want user name
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
		@Override
		public String toString() {
			return "FileRequeistionDTO [caseId=" + caseId + ", requeistionId=" + requeistionId
					+ ", totalOutstandingAmmount=" + totalOutstandingAmmount + ", totalInterestRate="
					+ totalInterestRate + ", interestDueForm=" + interestDueForm + ", totalCourtFee=" + totalCourtFee
					+ ", missllenousFee=" + missllenousFee + ", paidCourFee=" + paidCourFee + ", totalDemand="
					+ totalDemand + ", financialYear=" + financialYear + ", districtName=" + districtName
					+ ", currentDate=" + currentDate + ", updateDate=" + updateDate + ", status=" + status + ", reason="
					+ reason + ", userName=" + userName + "]";
		}
		
}
