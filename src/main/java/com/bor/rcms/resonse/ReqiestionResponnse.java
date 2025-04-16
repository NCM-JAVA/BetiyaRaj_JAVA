package com.bor.rcms.resonse;

import java.util.List;

import com.bor.rcms.dto.DocumentDTO;
import com.bor.rcms.dto.FileRequeistionDTO;
import com.bor.rcms.dto.FileRequeistionVo;
import com.bor.rcms.entity.CertificateGuaranter;
import com.bor.rcms.entity.DocumentEntityPdr;
import com.bor.rcms.entity.FileRequeistion;
import com.bor.rcms.entity.UserEntity;

public class ReqiestionResponnse {
	private List<FileRequeistionDTO> listfileRequeistion;
	private UserEntity entity;
	private FileRequeistionDTO fileRequeistion;
	
	private FileRequeistionVo fileRequeistionVo;


	private String status;
	private String msg;
	
    private List<DocumentDTO> documentEntityPdrs ;

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

	public List<DocumentDTO> getDocumentEntityPdrs() {
		return documentEntityPdrs;
	}

	public void setDocumentEntityPdrs(List<DocumentDTO> documentEntityPdrs) {
		this.documentEntityPdrs = documentEntityPdrs;
	}

	

	

	@Override
	public String toString() {
		return "ReqiestionResponnse [listfileRequeistion=" + listfileRequeistion + ", entity=" + entity
				+ ", fileRequeistion=" + fileRequeistion + ", fileRequeistionVo=" + fileRequeistionVo + ", status="
				+ status + ", msg=" + msg + ", documentEntityPdrs=" + documentEntityPdrs + "]";
	}

	public FileRequeistionVo getFileRequeistionVo() {
		return fileRequeistionVo;
	}

	public void setFileRequeistionVo(FileRequeistionVo fileRequeistionVo) {
		this.fileRequeistionVo = fileRequeistionVo;
	}

	public ReqiestionResponnse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
	
