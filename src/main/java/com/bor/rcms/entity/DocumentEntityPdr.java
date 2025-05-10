package com.bor.rcms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class DocumentEntityPdr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	    private String documentName;
	    private String filePath; // Store file path instead of file content
	    private String fileType;
	    private Long fileSize;
	    private String documentType;

	    @ManyToOne
	    @JoinColumn(name = "requeistion_id") 
	    @JsonIgnore// foreign key column
	    private FileRequeistion requeistion;


	    @Temporal(TemporalType.TIMESTAMP)
	    private Date uploadedDate;
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

		public String getDocumentType() {
			return documentType;
		}

		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}

		public FileRequeistion getFileRequeistion() {
			return requeistion;
		}

		public void setFileRequeistion(FileRequeistion fileRequeistion) {
			this.requeistion = fileRequeistion;
		}

		public Date getUploadedDate() {
			return uploadedDate;
		}

		public void setUploadedDate(Date uploadedDate) {
			this.uploadedDate = uploadedDate;
		}

		@Override
		public String toString() {
			return "DocumentEntityPdr [id=" + id + ", documentName=" + documentName + ", filePath=" + filePath
					+ ", fileType=" + fileType + ", fileSize=" + fileSize + ", documentType=" + documentType
					+ ", fileRequeistion=" + requeistion + ", uploadedDate=" + uploadedDate + "]";
		}
	    


}
