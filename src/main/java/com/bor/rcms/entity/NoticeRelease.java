package com.bor.rcms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "noticedRelease")
public class NoticeRelease {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String documentName;
	    private String filePath; // Store file path instead of file content
	    private String fileType;
	    private Long fileSize;

	    @ManyToOne
	    @JoinColumn(name = "objection_id", nullable = false)
	    @JsonBackReference
	    private NewObjection objection;//Maps to related objection

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

		@Override
		public String toString() {
			return "NoticeRelease [id=" + id + ", documentName=" + documentName + ", filePath=" + filePath
					+ ", fileType=" + fileType + ", fileSize=" + fileSize + ", objection=" + objection
					+ ", uploadedDate=" + uploadedDate + "]";
		}

		public NoticeRelease(Long id, String documentName, String filePath, String fileType, Long fileSize,
				NewObjection objection, Date uploadedDate) {
			super();
			this.id = id;
			this.documentName = documentName;
			this.filePath = filePath;
			this.fileType = fileType;
			this.fileSize = fileSize;
			this.objection = objection;
			this.uploadedDate = uploadedDate;
		}

		public NoticeRelease() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
	    


}
