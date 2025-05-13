package com.bor.rcms.entity;

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
@Entity
public class CaseNotesPdr {
	 @Id  
	    @GeneratedValue(strategy = GenerationType.AUTO)
	     @Column(name="pdrnotes_id")
	     private Long id; 
     	 @Lob
		@Basic(fetch = FetchType.EAGER) // Eager fetch for LOB
		private String caseNotes;
		@OneToOne
		@JoinColumn(name = "requeistion_id", referencedColumnName = "requeistion_id")
		private FileRequeistion fileRequeistion;
		
		private String caseId;

		@OneToOne
		@JoinColumn(name = "user_id", referencedColumnName = "user_id")
		private UserEntity userId;
		
		 private Date createdDate;
		    private Date modifiedDate;
		    private String status;
		    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
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
		public UserEntity getUserId() {
			return userId;
		}
		public void setUserId(UserEntity userId) {
			this.userId = userId;
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
		
		public CaseNotesPdr() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getCaseId() {
			return caseId;
		}
		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}
		@Override
		public String toString() {
			return "CaseNotesPdr [id=" + id + ", caseNotes=" + caseNotes + ", fileRequeistion=" + fileRequeistion
					+ ", caseId=" + caseId + ", userId=" + userId + ", createdDate=" + createdDate + ", modifiedDate="
					+ modifiedDate + ", status=" + status + "]";
		}
		
		
		
}
