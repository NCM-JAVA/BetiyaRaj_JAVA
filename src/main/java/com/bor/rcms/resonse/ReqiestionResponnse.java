package com.bor.rcms.resonse;

import java.util.List;

import com.bor.rcms.dto.FileRequeistionDTO;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.UserEntity;

public class ReqiestionResponnse {
	private List<FileRequeistionDTO> listfileRequeistion;
	private UserEntity entity;
	private FileRequeistionDTO fileRequeistion;

	private String status;
	private String msg;
	
    private List<DocumentEntityPdr> documentEntityPdrs ;

	public List<FileRequeistionDTO> getListfileRequeistion() {
		return listfileRequeistion;
	}

	public void setListfileRequeistion(List<FileRequeistionDTO> listfileRequeistion) {
		this.listfileRequeistion = listfileRequeistion;
	}

	public UserEntity getEntity() {
		return entity;
	}

	public void setEntity(UserEntity entity) {
		this.entity = entity;
	}

	

	public FileRequeistionDTO getFileRequeistion() {
		return fileRequeistion;
	}

	public void setFileRequeistion(FileRequeistionDTO fileRequeistion) {
		this.fileRequeistion = fileRequeistion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DocumentEntityPdr> getDocumentEntityPdrs() {
		return documentEntityPdrs;
	}

	public void setDocumentEntityPdrs(List<DocumentEntityPdr> documentEntityPdrs) {
		this.documentEntityPdrs = documentEntityPdrs;
	}

	

	public ReqiestionResponnse(List<FileRequeistionDTO> listfileRequeistion, UserEntity entity,
			FileRequeistionDTO fileRequeistion, String status, String msg, List<DocumentEntityPdr> documentEntityPdrs) {
		super();
		this.listfileRequeistion = listfileRequeistion;
		this.entity = entity;
		this.fileRequeistion = fileRequeistion;
		this.status = status;
		this.msg = msg;
		this.documentEntityPdrs = documentEntityPdrs;
	}

	public ReqiestionResponnse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
	
