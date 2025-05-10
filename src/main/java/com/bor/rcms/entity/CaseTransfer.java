package com.bor.rcms.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class CaseTransfer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="case_transfer_id")
	private String CaseTransferId;
	
	@Column(name="requeistion_id")
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

      @Column(name = "current_date_str")
	    private String currentDate;
		 @Column(name = "updateDate", length = 30)
	    private String updateDate;
	    private String status;
	  	  
	  @Lob
	    @Basic(fetch = FetchType.EAGER)  // Eager fetch for LOB
	 private String reason;
	  @OneToMany(mappedBy = "requeistion", cascade = CascadeType.ALL)
	    private List<DocumentEntityPdr> documents;

	  @ManyToOne
	    @JoinColumn(name = "role_id", nullable = false)
	    private RoleEntity role;
	  @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserEntity userId;
	  
	  @OneToMany(mappedBy = "requeistion", cascade = CascadeType.ALL)
	    @JsonManagedReference

	    private List<CertificateDebator> certificateDebator;

	  @OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "certificate_guaranter_id", nullable = false)
	  private CertificateGuaranter certificateGuaranter;
	  
	  @OneToOne(mappedBy = "case_transfer_id")
	  @JsonBackReference
	  private CertificatOfficer certificatOfficer;

	public String getCaseTransferId() {
		return CaseTransferId;
	}

	public void setCaseTransferId(String caseTransferId) {
		CaseTransferId = caseTransferId;
	}

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

	public List<DocumentEntityPdr> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentEntityPdr> documents) {
		this.documents = documents;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public List<CertificateDebator> getCertificateDebator() {
		return certificateDebator;
	}

	public void setCertificateDebator(List<CertificateDebator> certificateDebator) {
		this.certificateDebator = certificateDebator;
	}

	public CertificateGuaranter getCertificateGuaranter() {
		return certificateGuaranter;
	}

	public void setCertificateGuaranter(CertificateGuaranter certificateGuaranter) {
		this.certificateGuaranter = certificateGuaranter;
	}

	public CertificatOfficer getCertificatOfficer() {
		return certificatOfficer;
	}

	public void setCertificatOfficer(CertificatOfficer certificatOfficer) {
		this.certificatOfficer = certificatOfficer;
	}

	public CaseTransfer(String caseTransferId, String requeistionId, String totalOutstandingAmmount,
			String totalInterestRate, String interestDueForm, String totalCourtFee, String missllenousFee,
			String paidCourFee, String totalDemand, String financialYear, String districtName, String currentDate,
			String updateDate, String status, String reason, List<DocumentEntityPdr> documents, RoleEntity role,
			UserEntity userId, List<CertificateDebator> certificateDebator, CertificateGuaranter certificateGuaranter,
			CertificatOfficer certificatOfficer) {
		super();
		CaseTransferId = caseTransferId;
		this.requeistionId = requeistionId;
		this.totalOutstandingAmmount = totalOutstandingAmmount;
		this.totalInterestRate = totalInterestRate;
		this.interestDueForm = interestDueForm;
		this.totalCourtFee = totalCourtFee;
		this.missllenousFee = missllenousFee;
		this.paidCourFee = paidCourFee;
		this.totalDemand = totalDemand;
		this.financialYear = financialYear;
		this.districtName = districtName;
		this.currentDate = currentDate;
		this.updateDate = updateDate;
		this.status = status;
		this.reason = reason;
		this.documents = documents;
		this.role = role;
		this.userId = userId;
		this.certificateDebator = certificateDebator;
		this.certificateGuaranter = certificateGuaranter;
		this.certificatOfficer = certificatOfficer;
	}

	public CaseTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  
}



