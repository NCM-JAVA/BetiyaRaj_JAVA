package com.bor.rcms.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="file_requeistion")
public class FileRequeistion {
	

	@Id
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
	    
	    private String certificateOffstatus;
	    
	    private String nominalOffstatus;

	    
	    private String caseTransferId;
	    
	    private String transferRemark;
	    
	    private Boolean modifiable;
	  	  
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
	  
	  
	  @OneToMany(mappedBy = "fileRequeistion", cascade = CascadeType.ALL)
	  private List<LegalRepresentative> legalRepresentative;

	  @OneToOne(mappedBy = "fileRequeistion")
	  @JsonBackReference
	  private CertificatOfficer certificatOfficer;
	  
	  
	  private String   transNomId;

	  @Column(columnDefinition = "boolean default true")
	  private Boolean isTransOfficer = true;
	  
	  @Column(columnDefinition = "boolean default false")
	  private Boolean isTransNomOfficer = false;

	  @Column(columnDefinition = "boolean default false")
	  private Boolean isTransBOR = false;
	  
	  
	public Boolean getIsTransOfficer() {
		return isTransOfficer;
	}


	public void setIsTransOfficer(Boolean isTransOfficer) {
		this.isTransOfficer = isTransOfficer;
	}


	public Boolean getIsTransNomOfficer() {
		return isTransNomOfficer;
	}


	public void setIsTransNomOfficer(Boolean isTransNomOfficer) {
		this.isTransNomOfficer = isTransNomOfficer;
	}


	public Boolean getIsTransBOR() {
		return isTransBOR;
	}


	public void setIsTransBOR(Boolean isTransBOR) {
		this.isTransBOR = isTransBOR;
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


	public CertificateGuaranter getCertificateGuaranter() {
		return certificateGuaranter;
	}


	public void setCertificateGuaranter(CertificateGuaranter certificateGuaranter) {
		this.certificateGuaranter = certificateGuaranter;
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


	public List<CertificateDebator> getCertificateDebator() {
		return certificateDebator;
	}


	public void setCertificateDebator(List<CertificateDebator> certificateDebator) {
		this.certificateDebator = certificateDebator;
	}


	public String getCaseTransferId() {
		return caseTransferId;
	}


	public void setCaseTransferId(String caseTransferId) {
		this.caseTransferId = caseTransferId;
	}


	public CertificatOfficer getCertificatOfficer() {
		return certificatOfficer;
	}


	public void setCertificatOfficer(CertificatOfficer certificatOfficer) {
		this.certificatOfficer = certificatOfficer;
	}


	
	public Boolean getModifiable() {
		return modifiable;
	}


	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}


	public FileRequeistion() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getTransNomId() {
		return transNomId;
	}


	public void setTransNomId(String transNomId) {
		this.transNomId = transNomId;
	}


	public String getTransferRemark() {
		return transferRemark;
	}


	public void setTransferRemark(String transferRemark) {
		this.transferRemark = transferRemark;
	}


	public String getCertificateOffstatus() {
		return certificateOffstatus;
	}


	public void setCertificateOffstatus(String certificateOffstatus) {
		this.certificateOffstatus = certificateOffstatus;
	}


	public String getNominalOffstatus() {
		return nominalOffstatus;
	}


	public void setNominalOffstatus(String nominalOffstatus) {
		this.nominalOffstatus = nominalOffstatus;
	}


	public List<LegalRepresentative> getLegalRepresentative() {
		return legalRepresentative;
	}


	public void setLegalRepresentative(List<LegalRepresentative> legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}


	@Override
	public String toString() {
		return "FileRequeistion [requeistionId=" + requeistionId + ", totalOutstandingAmmount="
				+ totalOutstandingAmmount + ", totalInterestRate=" + totalInterestRate + ", interestDueForm="
				+ interestDueForm + ", totalCourtFee=" + totalCourtFee + ", missllenousFee=" + missllenousFee
				+ ", paidCourFee=" + paidCourFee + ", totalDemand=" + totalDemand + ", financialYear=" + financialYear
				+ ", districtName=" + districtName + ", currentDate=" + currentDate + ", updateDate=" + updateDate
				+ ", status=" + status + ", certificateOffstatus=" + certificateOffstatus + ", nominalOffstatus="
				+ nominalOffstatus + ", caseTransferId=" + caseTransferId + ", transferRemark=" + transferRemark
				+ ", modifiable=" + modifiable + ", reason=" + reason + ", documents=" + documents + ", role=" + role
				+ ", userId=" + userId + ", certificateDebator=" + certificateDebator + ", certificateGuaranter="
				+ certificateGuaranter + ", legalRepresentative=" + legalRepresentative + ", certificatOfficer="
				+ certificatOfficer + ", transNomId=" + transNomId + ", isTransOfficer=" + isTransOfficer
				+ ", isTransNomOfficer=" + isTransNomOfficer + ", isTransBOR=" + isTransBOR + "]";
	}


	



	
}
