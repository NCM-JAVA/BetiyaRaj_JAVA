package com.bor.rcms.entity;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.CreationTimestamp;
@Entity
public class DraftSaveCaseProceeding {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; 
	  @Lob
	  @Basic(fetch = FetchType.EAGER)  
	 private String draft;
	  
	  private String caseId;

	    @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public DraftSaveCaseProceeding() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DraftSaveCaseProceeding [id=" + id + ", draft=" + draft + ", caseId=" + caseId + ", createdDate="
				+ createdDate + "]";
	}
	  
	  
	  
	  


}
