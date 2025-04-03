package com.bor.rcms.entity;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "admission")
public class Admission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admission_id")
	private Long admissionId;

	private String hearingDate;
	private String hearingTime;
     private String district;

	private String admissionDate;
	private String admissionTime;

	private String affidavitDate;
	private String hearingTate;
	private Date createdDate;
	private Date modifiedDate;
	private String status;
	private String admisionCase;
	private String objectioId;
	private String officerName;
	private String statusCollector;
	private String statusCommissioner;
	
    @CreationTimestamp
	@Column(updatable = false)
    private LocalDateTime currentdate;

	
	private String action;
	private String caseClass;
	 @Lob
	    @Basic(fetch = FetchType.EAGER)  // Eager fetch for LOB
	 private String caseNotes;

	@OneToOne
	@JoinColumn(name = "objection_id", referencedColumnName = "objection_id")

	private NewObjection newObjection;
	
	  @OneToOne
		@JoinColumn(name = "user_id", referencedColumnName = "user_id")

	    private UserEntity userId;
	  
    private String collectorFreehold;
	    
	    private String freehold;

	    
	public Long getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
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

	public String getAffidavitDate() {
		return affidavitDate;
	}

	public void setAffidavitDate(String affidavitDate) {
		this.affidavitDate = affidavitDate;
	}

	public String getHearingTate() {
		return hearingTate;
	}

	public void setHearingTate(String hearingTate) {
		this.hearingTate = hearingTate;
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

	public NewObjection getNewObjection() {
		return newObjection;
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

	public void setNewObjection(NewObjection newObjection) {
		this.newObjection = newObjection;
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

	 
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
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

	/**
	 * @return the statusCollector
	 */
	public String getStatusCollector() {
		return statusCollector;
	}

	/**
	 * @param statusCollector the statusCollector to set
	 */
	public void setStatusCollector(String statusCollector) {
		this.statusCollector = statusCollector;
	}

	/**
	 * @return the statusCommissioner
	 */
	public String getStatusCommissioner() {
		return statusCommissioner;
	}

	/**
	 * @param statusCommissioner the statusCommissioner to set
	 */
	public void setStatusCommissioner(String statusCommissioner) {
		this.statusCommissioner = statusCommissioner;
	}

	/**
	 * @return the currentdate
	 */
	public LocalDateTime getCurrentdate() {
		return currentdate;
	}

	/**
	 * @param currentdate the currentdate to set
	 */
	public void setCurrentdate(LocalDateTime currentdate) {
		this.currentdate = currentdate;
	}

	public String getCollectorFreehold() {
		return collectorFreehold;
	}

	public void setCollectorFreehold(String collectorFreehold) {
		this.collectorFreehold = collectorFreehold;
	}

	public String getFreehold() {
		return freehold;
	}

	public void setFreehold(String freehold) {
		this.freehold = freehold;
	}

	
	
	
	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	
	

	@Override
	public String toString() {
		return "Admission [admissionId=" + admissionId + ", hearingDate=" + hearingDate + ", hearingTime=" + hearingTime
				+ ", district=" + district + ", admissionDate=" + admissionDate + ", admissionTime=" + admissionTime
				+ ", affidavitDate=" + affidavitDate + ", hearingTate=" + hearingTate + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", status=" + status + ", admisionCase=" + admisionCase
				+ ", objectioId=" + objectioId + ", officerName=" + officerName + ", statusCollector=" + statusCollector
				+ ", statusCommissioner=" + statusCommissioner + ", currentdate=" + currentdate + ", action=" + action
				+ ", caseClass=" + caseClass + ", caseNotes=" + caseNotes + ", newObjection=" + newObjection
				+ ", userId=" + userId + ", collectorFreehold=" + collectorFreehold + ", freehold=" + freehold + "]";
	}

	public Admission() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
