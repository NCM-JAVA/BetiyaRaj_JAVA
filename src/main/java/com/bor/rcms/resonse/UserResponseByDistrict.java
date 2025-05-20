package com.bor.rcms.resonse;

public class UserResponseByDistrict {
	
	
	private String userId;
	private String district;
	private String  userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public UserResponseByDistrict(String userId, String district, String userName) {
		super();
		this.userId = userId;
		this.district = district;
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserResponseByDistrict [userId=" + userId + ", district=" + district + ", userName=" + userName + "]";
	}
	public UserResponseByDistrict() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
