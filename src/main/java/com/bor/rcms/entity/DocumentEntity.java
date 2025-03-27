package com.bor.rcms.entity;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;
    private String filePath; // Store file path instead of file content
    private String fileType;
    private Long fileSize;
    private String documentType;

    @ManyToOne
    @JoinColumn(name = "objection_id", nullable = true)
    @JsonBackReference
    private NewObjection objection;//Maps to related objection

    private Date uploadedDate;
    private String caseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public NewObjection getObjection() {
		return objection;
	}

	public void setObjection(NewObjection objection) {
		this.objection = objection;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public DocumentEntity(Long id, String documentName, String filePath, String fileType, Long fileSize,
			String documentType, NewObjection objection, Date uploadedDate, String caseId) {
		super();
		this.id = id;
		this.documentName = documentName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.documentType = documentType;
		this.objection = objection;
		this.uploadedDate = uploadedDate;
		this.caseId = caseId;
	}

	public DocumentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
