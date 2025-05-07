package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommisionaryMap {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	  private String commisonary;
	  private String distrct;
	  private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "CommisionaryMap [id=" + id + ", commisonary=" + commisonary + ", distrct=" + distrct + ", status="
				+ status + "]";
	}
	public CommisionaryMap() {
		super();
		// TODO Auto-generated constructor stub
	}
	  

}
