package com.bor.rcms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class AppealCaseCollector {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "case_id")
	    private Long caseId;
	 
	    private String objection;
	    
	    @Column(name = "current_date_str", length = 30)  // updated column name to avoid conflict with reserved keyword
	    private String currentDate;
		 @Column(name = "updateDate", length = 30)
	    private String updateDate;
	    private String status;
	    private String status_officer;
	    
	    private String appealremark;
	    
	    private String collectorCase;
	    private String district;
	     @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserEntity userId;
	    
	     
	     @OneToOne
		    @JoinColumn(name = "objection_id", nullable = false)
		    private NewObjection objectionId;
	     
		    
	 	public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public Long getCaseId() {
			return caseId;
		}
		public void setCaseId(Long caseId) {
			this.caseId = caseId;
		}
		public String getObjection() {
			return objection;
		}
		public void setObjection(String objection) {
			this.objection = objection;
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
		public String getStatus_officer() {
			return status_officer;
		}
		public void setStatus_officer(String status_officer) {
			this.status_officer = status_officer;
		}
		public UserEntity getUserId() {
			return userId;
		}
		public void setUserId(UserEntity userId) {
			this.userId = userId;
		}
		public NewObjection getObjectionId() {
			return objectionId;
		}
		public void setObjectionId(NewObjection objectionId) {
			this.objectionId = objectionId;
		}
		public String getAppealremark() {
			return appealremark;
		}
		public void setAppealremark(String appealremark) {
			this.appealremark = appealremark;
		}
		public String getCollectorCase() {
			return collectorCase;
		}
		public void setCollectorCase(String collectorCase) {
			this.collectorCase = collectorCase;
		}
		public AppealCaseCollector(Long caseId, String objection, String currentDate, String updateDate, String status,
				String status_officer, String appealremark, String collectorCase, String district, UserEntity userId,
				NewObjection objectionId) {
			super();
			this.caseId = caseId;
			this.objection = objection;
			this.currentDate = currentDate;
			this.updateDate = updateDate;
			this.status = status;
			this.status_officer = status_officer;
			this.appealremark = appealremark;
			this.collectorCase = collectorCase;
			this.district = district;
			this.userId = userId;
			this.objectionId = objectionId;
		}
		@Override
		public String toString() {
			return "CaseCollector [caseId=" + caseId + ", objection=" + objection + ", currentDate=" + currentDate
					+ ", updateDate=" + updateDate + ", status=" + status + ", status_officer=" + status_officer
					+ ", appealremark=" + appealremark + ", collectorCase=" + collectorCase + ", district=" + district
					+ ", userId=" + userId + ", objectionId=" + objectionId + "]";
		}
		public AppealCaseCollector() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		

	    

}
