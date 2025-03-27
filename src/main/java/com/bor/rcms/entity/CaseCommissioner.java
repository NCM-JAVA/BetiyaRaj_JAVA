package com.bor.rcms.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class CaseCommissioner {
	
	 @Id
	   
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "case_commissoner_id")
	    private Long caseCommissonerId;

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

	    @Column(name = "commissoner_case")
	    private String commissonerCase;

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

//	    @Column(name = "created_date")
//	    private Date createdDate;  // Use LocalDateTime for timestamps
	    private Integer sequence; // This is used for ordering the cases

		public Long getCaseCommissonerId() {
			return caseCommissonerId;
		}

		public void setCaseCommissonerId(Long caseCommissonerId) {
			this.caseCommissonerId = caseCommissonerId;
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

		public String getCommissonerCase() {
			return commissonerCase;
		}

		public void setCommissonerCase(String commissonerCase) {
			this.commissonerCase = commissonerCase;
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

		public LocalDateTime getCurrentDate() {
			return currentDate;
		}

		public void setCurrentDate(LocalDateTime currentDate) {
			this.currentDate = currentDate;
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

		public Integer getSequence() {
			return sequence;
		}

		public void setSequence(Integer sequence) {
			this.sequence = sequence;
		}

		@Override
		public String toString() {
			return "CaseCommissioner [caseCommissonerId=" + caseCommissonerId + ", hearingDate=" + hearingDate
					+ ", hearingTime=" + hearingTime + ", district=" + district + ", admissionDate=" + admissionDate
					+ ", admissionTime=" + admissionTime + ", affidavitDate=" + affidavitDate + ", status=" + status
					+ ", commissonerCase=" + commissonerCase + ", objectioId=" + objectioId + ", action=" + action
					+ ", caseClass=" + caseClass + ", currentDate=" + currentDate + ", caseNotes=" + caseNotes
					+ ", newObjection=" + newObjection + ", userId=" + userId + ", admission=" + admission
					+ ", sequence=" + sequence + "]";
		}

		public CaseCommissioner(Long caseCommissonerId, String hearingDate, String hearingTime, String district,
				String admissionDate, String admissionTime, String affidavitDate, String status, String commissonerCase,
				String objectioId, String action, String caseClass, LocalDateTime currentDate, String caseNotes,
				NewObjection newObjection, UserEntity userId, Admission admission, Integer sequence) {
			super();
			this.caseCommissonerId = caseCommissonerId;
			this.hearingDate = hearingDate;
			this.hearingTime = hearingTime;
			this.district = district;
			this.admissionDate = admissionDate;
			this.admissionTime = admissionTime;
			this.affidavitDate = affidavitDate;
			this.status = status;
			this.commissonerCase = commissonerCase;
			this.objectioId = objectioId;
			this.action = action;
			this.caseClass = caseClass;
			this.currentDate = currentDate;
			this.caseNotes = caseNotes;
			this.newObjection = newObjection;
			this.userId = userId;
			this.admission = admission;
			this.sequence = sequence;
		}

		public CaseCommissioner() {
			super();
			// TODO Auto-generated constructor stub
		}

	    

}
