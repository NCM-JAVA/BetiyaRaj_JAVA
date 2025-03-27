package com.bor.rcms.resonse;

import com.bor.rcms.entity.RoleEntity;

public class LoginResponse {
	private String token;
    private Long userId;
    private String fullName;
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
	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", userId=" + userId + ", fullName=" + fullName + ", district="
				+ district + ", role=" + role + "]";
	}
	public LoginResponse(String token, Long userId, String fullName, String district, RoleEntity role) {
		super();
		this.token = token;
		this.userId = userId;
		this.fullName = fullName;
		this.district = district;
		this.role = role;
	}
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
