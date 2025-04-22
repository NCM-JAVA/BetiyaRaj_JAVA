package com.bor.rcms.dto;

public class OTPRequest {
	
	private String userName;
	private String email;
    private String otp;
    private String act;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public OTPRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	@Override
	public String toString() {
		return "OTPRequest [userName=" + userName + ", email=" + email + ", otp=" + otp + ", act=" + act + "]";
	}
	
	

}
