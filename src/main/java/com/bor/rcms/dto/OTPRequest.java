package com.bor.rcms.dto;

public class OTPRequest {
	
	private String userName;
    private String otp;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OTPRequest(String userName, String otp) {
		super();
		this.userName = userName;
		this.otp = otp;
	}

}
