package com.bor.rcms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "new_objectioner")
public class NewObjection  implements Serializable {
    private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "objection_id")
	    private Long objectionId;
	    private String actName;
	    private String districtName;
	    private String circleName;
	    private String halka;
	    private String mozza;
	    private String khattaNumber;
	    private String plotNumber;
	    private String acre;
	    private String state;
	    
	    private String dismil;
	    private String objection;
	    private String documentType;
	    
	    @Column(name = "current_date_str", length = 30)  // updated column name to avoid conflict with reserved keyword
	    private String currentDate;
		 @Column(name = "updateDate", length = 30)
	    private String updateDate;
	    private String status;
	    private String status_officer;
	    private String tokenNo;
	    

          @CreationTimestamp
    	@Column(updatable = false)
	    private LocalDateTime currentdate;
	    
	    private String statusCollector;
	    
	    private String statusCommissioner;
	    
        private String collectorFreehold;
	    
	    private String freehold;

	    private String statusDivComm;
	    @OneToMany(mappedBy = "newObjection", cascade = CascadeType.ALL)
	    private List<FileEntity> filesystem;
	    
	    @OneToMany(mappedBy = "objection")
	    private List<DocumentEntity> documents;
	    @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private UserEntity userId;
	    
	    @OneToOne(fetch = FetchType.LAZY, mappedBy = "newObjection", cascade = CascadeType.ALL)
	    private Mis mis;
	    @JsonBackReference
	    @OneToOne(fetch = FetchType.EAGER, mappedBy = "newObjection", cascade = CascadeType.ALL)
	    private Admission admission;
	    
	    @JsonBackReference
	    @OneToOne(fetch = FetchType.EAGER, mappedBy = "newObjection", cascade = CascadeType.ALL)
	    private CaseCollector caseCollector;
	    
	    
	    @JsonBackReference
	    @OneToOne(fetch = FetchType.EAGER, mappedBy = "newObjection", cascade = CascadeType.ALL)
	    private CaseCommissioner caseCommissioner;
	    

		public Long getObjectionId() {
			return objectionId;
		}

		public void setObjectionId(Long objectionId) {
			this.objectionId = objectionId;
		}

		public String getActName() {
			return actName;
		}

		public void setActName(String actName) {
			this.actName = actName;
		}

		public String getDistrictName() {
			return districtName;
		}

		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}

		public String getCircleName() {
			return circleName;
		}

		public void setCircleName(String circleName) {
			this.circleName = circleName;
		}

		public String getHalka() {
			return halka;
		}

		public void setHalka(String halka) {
			this.halka = halka;
		}

		public String getMozza() {
			return mozza;
		}

		public void setMozza(String mozza) {
			this.mozza = mozza;
		}

		public String getKhattaNumber() {
			return khattaNumber;
		}

		public void setKhattaNumber(String khattaNumber) {
			this.khattaNumber = khattaNumber;
		}

		public String getPlotNumber() {
			return plotNumber;
		}

		public void setPlotNumber(String plotNumber) {
			this.plotNumber = plotNumber;
		}

		public String getAcre() {
			return acre;
		}

		public void setAcre(String acre) {
			this.acre = acre;
		}

		public String getDismil() {
			return dismil;
		}

		public void setDismil(String dismil) {
			this.dismil = dismil;
		}

		public String getObjection() {
			return objection;
		}

		public void setObjection(String objection) {
			this.objection = objection;
		}

		
		public String getDocumentType() {
			return documentType;
		}

		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
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

		public String getTokenNo() {
			return tokenNo;
		}

		public void setTokenNo(String tokenNo) {
			this.tokenNo = tokenNo;
		}

		public List<FileEntity> getFilesystem() {
			return filesystem;
		}

		public void setFilesystem(List<FileEntity> filesystem) {
			this.filesystem = filesystem;
		}

		public UserEntity getUserId() {
			return userId;
		}

		public void setUserId(UserEntity userId) {
			this.userId = userId;
		}

		public Mis getMis() {
			return mis;
		}

		public void setMis(Mis mis) {
			this.mis = mis;
		}

		public Admission getAdmission() {
			return admission;
		}

		public void setAdmission(Admission admission) {
			this.admission = admission;
		}

	
		public NewObjection() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public List<DocumentEntity> getDocuments() {
			return documents;
		}

		public void setDocuments(List<DocumentEntity> documents) {
			this.documents = documents;
		}

		public CaseCollector getCaseCollector() {
			return caseCollector;
		}

		public void setCaseCollector(CaseCollector caseCollector) {
			this.caseCollector = caseCollector;
		}

		public String getStatusCollector() {
			return statusCollector;
		}

		public void setStatusCollector(String statusCollector) {
			this.statusCollector = statusCollector;
		}

		public String getStatusDivComm() {
			return statusDivComm;
		}

		public void setStatusDivComm(String statusDivComm) {
			this.statusDivComm = statusDivComm;
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

		public CaseCommissioner getCaseCommissioner() {
			return caseCommissioner;
		}

		public void setCaseCommissioner(CaseCommissioner caseCommissioner) {
			this.caseCommissioner = caseCommissioner;
		}

		@Override
		public String toString() {
			return "NewObjection [objectionId=" + objectionId + ", actName=" + actName + ", districtName="
					+ districtName + ", circleName=" + circleName + ", halka=" + halka + ", mozza=" + mozza
					+ ", khattaNumber=" + khattaNumber + ", plotNumber=" + plotNumber + ", acre=" + acre + ", state="
					+ state + ", dismil=" + dismil + ", objection=" + objection + ", documentType=" + documentType
					+ ", currentDate=" + currentDate + ", updateDate=" + updateDate + ", status=" + status
					+ ", status_officer=" + status_officer + ", tokenNo=" + tokenNo + ", currentdate=" + currentdate
					+ ", statusCollector=" + statusCollector + ", statusCommissioner=" + statusCommissioner
					+ ", collectorFreehold=" + collectorFreehold + ", freehold=" + freehold + ", statusDivComm="
					+ statusDivComm + ", filesystem=" + filesystem + ", documents=" + documents + ", userId=" + userId
					+ ", mis=" + mis + ", admission=" + admission + ", caseCollector=" + caseCollector
					+ ", caseCommissioner=" + caseCommissioner + "]";
		}

		

		
		
	    
}