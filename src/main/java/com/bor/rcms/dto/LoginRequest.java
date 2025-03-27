package com.bor.rcms.dto;

public class LoginRequest {
	
	private String userName;
    private String password;
    private String userType;
    private int firstValue;
    private int secondValue;
    private int captchaValue;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the firstValue
	 */
	public int getFirstValue() {
		return firstValue;
	}
	/**
	 * @param firstValue the firstValue to set
	 */
	public void setFirstValue(int firstValue) {
		this.firstValue = firstValue;
	}
	/**
	 * @return the secondValue
	 */
	public int getSecondValue() {
		return secondValue;
	}
	/**
	 * @param secondValue the secondValue to set
	 */
	public void setSecondValue(int secondValue) {
		this.secondValue = secondValue;
	}
	/**
	 * @return the captchaValue
	 */
	public int getCaptchaValue() {
		return captchaValue;
	}
	/**
	 * @param captchaValue the captchaValue to set
	 */
	public void setCaptchaValue(int captchaValue) {
		this.captchaValue = captchaValue;
	}
	@Override
	public String toString() {
		return "LoginRequest [userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", firstValue=" + firstValue + ", secondValue=" + secondValue + ", captchaValue=" + captchaValue
				+ "]";
	}
	
	
	
}
