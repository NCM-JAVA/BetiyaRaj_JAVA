package com.bor.rcms.dto;

public class AuthRequest {
    private String username;
   
    private String password;
    private String userType;
    private int firstValue;
    private int secondValue;
    private int captchaValue;

    public AuthRequest() {
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(int firstValue) {
		this.firstValue = firstValue;
	}

	public int getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(int secondValue) {
		this.secondValue = secondValue;
	}

	public int getCaptchaValue() {
		return captchaValue;
	}

	public void setCaptchaValue(int captchaValue) {
		this.captchaValue = captchaValue;
	}

	// Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", password=" + password + ", userType=" + userType
				+ ", firstValue=" + firstValue + ", secondValue=" + secondValue + ", captchaValue=" + captchaValue
				+ "]";
	}
    
}
