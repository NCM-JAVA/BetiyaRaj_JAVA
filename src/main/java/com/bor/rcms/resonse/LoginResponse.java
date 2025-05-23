package com.bor.rcms.resonse;

import com.bor.rcms.entity.RoleEntity;

public class LoginResponse {
	private String msg;
	private String status;
	private String token;
    private Long userId;
    private String fullName;
    private String userName;
    private String district;
    private RoleEntity role;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "LoginResponse [msg=" + msg + ", status=" + status + ", token=" + token + ", userId=" + userId
				+ ", fullName=" + fullName + ", userName=" + userName + ", district=" + district + ", role=" + role
				+ "]";
	}
	
	
	
	
    
    

}
