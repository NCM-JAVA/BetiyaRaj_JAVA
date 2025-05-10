package com.bor.rcms.entity;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CaseCollector {
    @Id
   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_collector_id")
    private Long caseCollectorId;

    @Column(name = "hearing_date")
    private String hearingDate;

    @Column(name = "hearing_time")
    private String hearingTime;

    @Column(name = "district")
    private String district;

    @Column(name = "admission_date")
    private String admissionDate;

    @Column(name = "admission_time")
    private String admissionTime;

    @Column(name = "affidavit_date")
    private String affidavitDate;

    @Column(name = "status")
    private String status;
    
    @Column(name = "officer_name")
    private String officerName;

    @Column(name = "collector_case")
    private String collectorCase;

    @Column(name = "objectio_id")
    private String objectioId;

    @Column(name = "action")
    private String action;

    @Column(name = "case_class")
    private String caseClass;
    
    // Renaming current_date to created_at
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime currentDate; 
    @Lob
    @Basic(fetch = FetchType.LAZY)  // Use LAZY fetch for better performance
    @Column(name = "case_notes")
    private String caseNotes;

    @ManyToOne
    @JoinColumn(name = "objection_id", referencedColumnName = "objection_id")
    private NewObjection newObjection;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "admission_id", referencedColumnName = "admission_id")
    private Admission admission;

//    @Column(name = "created_date")
//    private Date createdDate;  // Use LocalDateTime for timestamps
    private Integer sequence; // This is used for ordering the cases

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;  // Use LocalDateTime for timestamps

	public Long getCaseCollectorId() {
		return caseCollectorId;
	}

	public void setCaseCollectorId(Long caseCollectorId) {
		this.caseCollectorId = caseCollectorId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCollectorCase() {
		return collectorCase;
	}

	public void setCollectorCase(String collectorCase) {
		this.collectorCase = collectorCase;
	}

	public String getObjectioId() {
		return objectioId;
	}

	public void setObjectioId(String objectioId) {
		this.objectioId = objectioId;
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

	public NewObjection getNewObjection() {
		return newObjection;
	}

	public void setNewObjection(NewObjection newObjection) {
		this.newObjection = newObjection;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public Admission getAdmission() {
		return admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the currentdate
	 */
	public LocalDateTime getCurrentdate() {
		return currentDate;
	}

	/**
	 * @param currentdate the currentdate to set
	 */
	public void setCurrentdate(LocalDateTime currentdate) {
		this.currentDate = currentdate;
	}

	/**
	 * @return the currentDate
	 */
	public LocalDateTime getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate the currentDate to set
	 */
	public void setCurrentDate(LocalDateTime currentDate) {
		this.currentDate = currentDate;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	@Override
	public String toString() {
		return "CaseCollector [caseCollectorId=" + caseCollectorId + ", hearingDate=" + hearingDate + ", hearingTime="
				+ hearingTime + ", district=" + district + ", admissionDate=" + admissionDate + ", admissionTime="
				+ admissionTime + ", affidavitDate=" + affidavitDate + ", status=" + status + ", officerName="
				+ officerName + ", collectorCase=" + collectorCase + ", objectioId=" + objectioId + ", action=" + action
				+ ", caseClass=" + caseClass + ", currentDate=" + currentDate + ", caseNotes=" + caseNotes
				+ ", newObjection=" + newObjection + ", userId=" + userId + ", admission=" + admission + ", sequence="
				+ sequence + ", modifiedDate=" + modifiedDate + "]";
	}


	

	

	

	
	
}