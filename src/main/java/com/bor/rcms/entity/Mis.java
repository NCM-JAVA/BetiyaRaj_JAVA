package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "mis_track")
public class Mis {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name="misid")
	    private Long misid;

	    private String caseId;
	    private String objId;
	    private String specialOfficer;
	    private String specialRemarks;
	    private String collector;
	    private String collectorRemarks;
	    private String divcomissioner;
	    private String divcomissionerRemarks;
	    private String bor;
	    private String borRemarks;

	    @JsonBackReference
	    @OneToOne
	    @JoinColumn(name = "objection_id", referencedColumnName = "objection_id")
	    private NewObjection newObjection;
		
		public Long getMisid() {
			return misid;
		}
		public void setMisid(Long misid) {
			this.misid = misid;
		}
		public String getCaseId() {
			return caseId;
		}
		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}
		public String getObjId() {
			return objId;
		}
		public void setObjId(String objId) {
			this.objId = objId;
		}
		public String getSpecialOfficer() {
			return specialOfficer;
		}
		public void setSpecialOfficer(String specialOfficer) {
			this.specialOfficer = specialOfficer;
		}
		public String getSpecialRemarks() {
			return specialRemarks;
		}
		public void setSpecialRemarks(String specialRemarks) {
			this.specialRemarks = specialRemarks;
		}
		public String getCollector() {
			return collector;
		}
		public void setCollector(String collector) {
			this.collector = collector;
		}
		public String getCollectorRemarks() {
			return collectorRemarks;
		}
		public void setCollectorRemarks(String collectorRemarks) {
			this.collectorRemarks = collectorRemarks;
		}
		public String getDivcomissioner() {
			return divcomissioner;
		}
		public void setDivcomissioner(String divcomissioner) {
			this.divcomissioner = divcomissioner;
		}
		public String getDivcomissionerRemarks() {
			return divcomissionerRemarks;
		}
		public void setDivcomissionerRemarks(String divcomissionerRemarks) {
			this.divcomissionerRemarks = divcomissionerRemarks;
		}
		public String getBor() {
			return bor;
		}
		public void setBor(String bor) {
			this.bor = bor;
		}
		public String getBorRemarks() {
			return borRemarks;
		}
		public void setBorRemarks(String borRemarks) {
			this.borRemarks = borRemarks;
		}
		
		
		public NewObjection getNewObjection() {
			return newObjection;
		}
		public void setNewObjection(NewObjection newObjection) {
			this.newObjection = newObjection;
		}
		
		
		
		@Override
		public String toString() {
			return "Mis [misid=" + misid + ", caseId=" + caseId + ", objId=" + objId + ", specialOfficer="
					+ specialOfficer + ", specialRemarks=" + specialRemarks + ", collector=" + collector
					+ ", collectorRemarks=" + collectorRemarks + ", divcomissioner=" + divcomissioner
					+ ", divcomissionerRemarks=" + divcomissionerRemarks + ", bor=" + bor + ", borRemarks=" + borRemarks
					+ ", newObjection=" + newObjection + "]";
		}
		public Mis(Long misid, String caseId, String objId, String specialOfficer, String specialRemarks,
				String collector, String collectorRemarks, String divcomissioner, String divcomissionerRemarks,
				String bor, String borRemarks, NewObjection newObjection) {
			super();
			this.misid = misid;
			this.caseId = caseId;
			this.objId = objId;
			this.specialOfficer = specialOfficer;
			this.specialRemarks = specialRemarks;
			this.collector = collector;
			this.collectorRemarks = collectorRemarks;
			this.divcomissioner = divcomissioner;
			this.divcomissionerRemarks = divcomissionerRemarks;
			this.bor = bor;
			this.borRemarks = borRemarks;
			this.newObjection = newObjection;
		}
		public Mis() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	    
	   
}
