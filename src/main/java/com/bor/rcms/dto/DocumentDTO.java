package com.bor.rcms.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

import com.bor.rcms.entity.FileRequeistion;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DocumentDTO {
	
	 private Long id;

        @NotBlank
	    private String documentName;
	    private String filePath; // Store file path instead of file content
	    private String fileType;
	    
	    
	    @Max(value = 10485760, message = "File size must not exceed 10 MB")
	    private Long fileSize;
	    private String documentType;
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
		@Override
		public String toString() {
			return "DocumentDTO [id=" + id + ", documentName=" + documentName + ", filePath=" + filePath + ", fileType="
					+ fileType + ", fileSize=" + fileSize + ", documentType=" + documentType + "]";
		}
		public DocumentDTO(Long id, String documentName, String filePath, String fileType, Long fileSize,
				String documentType) {
			super();
			this.id = id;
			this.documentName = documentName;
			this.filePath = filePath;
			this.fileType = fileType;
			this.fileSize = fileSize;
			this.documentType = documentType;
		}
		public DocumentDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

	  

}
