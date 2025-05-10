package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


public class CertificateOfficerCaseNotes {

	  @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="case_notes_id")

	  private String caseNotesId;
	  
	  
	  
	    private CertificatOfficer fileRequeistion;
	private String reequeistionId;
	  

}
