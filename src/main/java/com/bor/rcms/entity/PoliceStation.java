package com.bor.rcms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PoliceStation {
	 @Id
	private Long policeId;
	private String stationName;
	  @ManyToOne
	    @JoinColumn(name = "com_id", nullable = false)
	    private CommisionaryMap commisionaryMap;
		private Date createdDate;
		private Date modifiedDate;
		private String createbyName;
	public Long getPoliceId() {
		return policeId;
	}
	public void setPoliceId(Long policeId) {
		this.policeId = policeId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	
	public CommisionaryMap getCommisionaryMap() {
		return commisionaryMap;
	}
	public void setCommisionaryMap(CommisionaryMap commisionaryMap) {
		this.commisionaryMap = commisionaryMap;
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
	public String getCreatebyName() {
		return createbyName;
	}
	public void setCreatebyName(String createbyName) {
		this.createbyName = createbyName;
	}
	
	
	@Override
	public String toString() {
		return "PoliceStation [policeId=" + policeId + ", stationName=" + stationName + ", commisionaryMap="
				+ commisionaryMap + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", createbyName=" + createbyName + "]";
	}
	public PoliceStation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}