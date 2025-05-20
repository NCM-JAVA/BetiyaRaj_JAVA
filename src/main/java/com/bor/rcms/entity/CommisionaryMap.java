package com.bor.rcms.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CommisionaryMap {
	  @Id
	  @Column(name="com_id")
	    private Long comId;
	  private String commisonary;
	  private String distrct;
	  private String state;
	
	  private String status;
	  private String circle;
	  
		private Date createdDate;
		private Date modifiedDate;
	
	public String getCommisonary() {
		return commisonary;
	}
	public void setCommisonary(String commisonary) {
		this.commisonary = commisonary;
	}
	public String getDistrct() {
		return distrct;
	}
	public void setDistrct(String distrct) {
		this.distrct = distrct;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public Long getComId() {
		return comId;
	}
	public void setComId(Long comId) {
		this.comId = comId;
	}
	
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
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
	
	@Override
	public String toString() {
		return "CommisionaryMap [comId=" + comId + ", commisonary=" + commisonary + ", distrct=" + distrct + ", state="
				+ state + ", status=" + status + ", circle=" + circle + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	public CommisionaryMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	  

}
