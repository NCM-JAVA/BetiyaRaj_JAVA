package com.bor.rcms.dto;

public class OtpLoginRequest {
	
	private String mobileNumber;
    private String otp;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OtpLoginRequest(String mobileNumber, String otp) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
	}
	public OtpLoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
