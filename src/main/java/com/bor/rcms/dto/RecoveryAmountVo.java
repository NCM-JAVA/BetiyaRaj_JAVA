package com.bor.rcms.dto;

public class RecoveryAmountVo {

	private double  recoveryAmmount;
	private  double remainAmount;
	private String    recoveryDate;
	private String createdByuser;
	private String userId;
	private String remarks;
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
	public String getCreatedByuser() {
		return createdByuser;
	}
	public void setCreatedByuser(String createdByuser) {
		this.createdByuser = createdByuser;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "RecoveryAmountVo [recoveryAmmount=" + recoveryAmmount + ", remainAmount=" + remainAmount
				+ ", recoveryDate=" + recoveryDate + ", createdByuser=" + createdByuser + ", userId=" + userId
				+ ", remarks=" + remarks + "]";
	}
	
	
	
}
