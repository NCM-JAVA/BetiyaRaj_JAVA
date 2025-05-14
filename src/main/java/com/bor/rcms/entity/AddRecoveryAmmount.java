package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class AddRecoveryAmmount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = " recovery_id")
	private Long recoveryId;
	private double  recoveryAmmount;
	private  double remainAmount;
	private String    recoveryDate;
	private String createdByuser;
	private String createdDate;
	private String updatedDate;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserEntity userId;
	
	private String remarks;
	private String caseId;

	public Long getRecoveryId() {
		return recoveryId;
	}

	public void setRecoveryId(Long recoveryId) {
		this.recoveryId = recoveryId;
	}

	public double getRecoveryAmmount() {
		return recoveryAmmount;
	}

	public void setRecoveryAmmount(double recoveryAmmount) {
		this.recoveryAmmount = recoveryAmmount;
	}

	public double getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(double remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getRecoveryDate() {
		return recoveryDate;
	}

	public void setRecoveryDate(String recoveryDate) {
		this.recoveryDate = recoveryDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedByuser() {
		return createdByuser;
	}

	public void setCreatedByuser(String createdByuser) {
		this.createdByuser = createdByuser;
	}


	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Override
	public String toString() {
		return "AddRecoveryAmmount [recoveryId=" + recoveryId + ", recoveryAmmount=" + recoveryAmmount
				+ ", remainAmount=" + remainAmount + ", recoveryDate=" + recoveryDate + ", createdByuser="
				+ createdByuser + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", userId=" + userId
				+ ", remarks=" + remarks + ", caseId=" + caseId + "]";
	}


	

	

}
