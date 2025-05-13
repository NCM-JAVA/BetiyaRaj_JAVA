package com.bor.rcms.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class CaseTransferPriviouseRecord {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String certOfficerId;

	private String hearingDate;
	private String hearingTime;
	private String district;

	private String admissionDate;
	private String admissionTime;

	private String affidavitDate;
	private Date createdDate;
	private Date modifiedDate;
	private String status;
	private String admisionCase;
	private String objectioId;
	private String officerName;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime currentdate;

	private String action;
	private String caseClass;
	@Lob
	@Basic(fetch = FetchType.EAGER) // Eager fetch for LOB
	private String caseNotes;

	@OneToOne
	@JoinColumn(name = "requeistion_id", referencedColumnName = "requeistion_id")
	private FileRequeistion fileRequeistion;
	
	 @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserEntity transferUser;
	  
	private String reequeistionId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCertOfficerId() {
		return certOfficerId;
	}
	public void setCertOfficerId(String certOfficerId) {
		this.certOfficerId = certOfficerId;
	}
	public String getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}
	public String getHearingTime() {
		return hearingTime;
	}
	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}
	public String getAffidavitDate() {
		return affidavitDate;
	}
	public void setAffidavitDate(String affidavitDate) {
		this.affidavitDate = affidavitDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdmisionCase() {
		return admisionCase;
	}
	public void setAdmisionCase(String admisionCase) {
		this.admisionCase = admisionCase;
	}
	public String getObjectioId() {
		return objectioId;
	}
	public void setObjectioId(String objectioId) {
		this.objectioId = objectioId;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public LocalDateTime getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(LocalDateTime currentdate) {
		this.currentdate = currentdate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCaseClass() {
		return caseClass;
	}
	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}
	public String getCaseNotes() {
		return caseNotes;
	}
	public void setCaseNotes(String caseNotes) {
		this.caseNotes = caseNotes;
	}
	public FileRequeistion getFileRequeistion() {
		return fileRequeistion;
	}
	public void setFileRequeistion(FileRequeistion fileRequeistion) {
		this.fileRequeistion = fileRequeistion;
	}
	public String getReequeistionId() {
		return reequeistionId;
	}
	public void setReequeistionId(String reequeistionId) {
		this.reequeistionId = reequeistionId;
	}
	public UserEntity getTransferUser() {
		return transferUser;
	}
	public void setTransferUser(UserEntity transferUser) {
		this.transferUser = transferUser;
	}
	@Override
	public String toString() {
		return "CaseTransferPriviouseRecord [id=" + id + ", certOfficerId=" + certOfficerId + ", hearingDate="
				+ hearingDate + ", hearingTime=" + hearingTime + ", district=" + district + ", admissionDate="
				+ admissionDate + ", admissionTime=" + admissionTime + ", affidavitDate=" + affidavitDate
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", admisionCase=" + admisionCase + ", objectioId=" + objectioId + ", officerName=" + officerName
				+ ", currentdate=" + currentdate + ", action=" + action + ", caseClass=" + caseClass + ", caseNotes="
				+ caseNotes + ", fileRequeistion=" + fileRequeistion + ", transferUser=" + transferUser
				+ ", reequeistionId=" + reequeistionId + "]";
	}
	
}
