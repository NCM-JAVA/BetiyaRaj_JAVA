package com.bor.rcms.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DebatorVo {
	@NotBlank(message = "Name required")
	@Size(max = 50, message = "Max 50 chars")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
	private String debtorName;

	@NotBlank(message = "Address required")
	@Size(max = 100, message = "Max 100 chars")
	private String debtorAddress;

	@NotBlank(message = "Address line 1 required")
	@Size(max = 100, message = "Max 100 chars")    
	private String debtorAddress1;

	@NotBlank(message = "Address line 2 required")
	@Size(max = 100, message = "Max 100 chars")
	private String debtorAddress2;

	@NotBlank(message = "State required")
	@Size(max = 50, message = "Max 50 chars")
	private String debtorState;

	@NotBlank(message = "City required")
	@Size(max = 50, message = "Max 50 chars")
	private String debtorCity;

	@NotBlank(message = "District required")
	@Size(max = 50, message = "Max 50 chars")
	private String debtorDistrict;
	
	 @NotBlank(message = "Pincode is required")
	    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	    private String debtorPincode;
	
	    private String circle;
	    
	    @NotBlank(message = "User ID is required")
	    private String userId;
	    
	    @NotBlank(message = "Father's name is required")
	    @Size(max = 100, message = "Father's name too long")  
	    private String debtorfatherNames;
	    
	    @NotBlank(message = "Subdivision is required")
	    @Size(max = 50, message = "Subdivision too long")
	    private  String debtorubDivision;
	    
	    @NotBlank(message = "Circle is required")
	    @Size(max = 50, message = "Circle name too long")
	    private  String debtorcircle;
	    
	    @NotBlank(message = "Police station is required")
	    @Size(max = 100, message = "Police station name too long")
	    private  String debtorpolicestation;
	    
	    @Column(unique = true, nullable = false)
	    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
	    private String debtorPhoneNumber;
	    
	    private String debtorStatePhoneNumber;
	    
	    @Email(message = "Invalid email format")
	    private String debtorEmail;
		public String getDebtorName() {
			return debtorName;
		}
		public void setDebtorName(String debtorName) {
			this.debtorName = debtorName;
		}
		public String getDebtorAddress() {
			return debtorAddress;
		}
		public void setDebtorAddress(String debtorAddress) {
			this.debtorAddress = debtorAddress;
		}
		public String getDebtorAddress1() {
			return debtorAddress1;
		}
		public void setDebtorAddress1(String debtorAddress1) {
			this.debtorAddress1 = debtorAddress1;
		}
		public String getDebtorAddress2() {
			return debtorAddress2;
		}
		public void setDebtorAddress2(String debtorAddress2) {
			this.debtorAddress2 = debtorAddress2;
		}
		public String getDebtorState() {
			return debtorState;
		}
		public void setDebtorState(String debtorState) {
			this.debtorState = debtorState;
		}
		public String getDebtorCity() {
			return debtorCity;
		}
		public void setDebtorCity(String debtorCity) {
			this.debtorCity = debtorCity;
		}
		public String getDebtorDistrict() {
			return debtorDistrict;
		}
		public void setDebtorDistrict(String debtorDistrict) {
			this.debtorDistrict = debtorDistrict;
		}
		public String getDebtorPincode() {
			return debtorPincode;
		}
		public void setDebtorPincode(String debtorPincode) {
			this.debtorPincode = debtorPincode;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getDebtorPhoneNumber() {
			return debtorPhoneNumber;
		}
		public void setDebtorPhoneNumber(String debtorPhoneNumber) {
			this.debtorPhoneNumber = debtorPhoneNumber;
		}
		public String getDebtorStatePhoneNumber() {
			return debtorStatePhoneNumber;
		}
		public void setDebtorStatePhoneNumber(String debtorStatePhoneNumber) {
			this.debtorStatePhoneNumber = debtorStatePhoneNumber;
		}
		public String getDebtorEmail() {
			return debtorEmail;
		}
		public void setDebtorEmail(String debtorEmail) {
			this.debtorEmail = debtorEmail;
		}
		public String getCircle() {
			return circle;
		}
		public void setCircle(String circle) {
			this.circle = circle;
		}
		public String getDebtorfatherNames() {
			return debtorfatherNames;
		}
		public void setDebtorfatherNames(String debtorfatherNames) {
			this.debtorfatherNames = debtorfatherNames;
		}
		public String getDebtorubDivision() {
			return debtorubDivision;
		}
		public void setDebtorubDivision(String debtorubDivision) {
			this.debtorubDivision = debtorubDivision;
		}
		public String getDebtorcircle() {
			return debtorcircle;
		}
		public void setDebtorcircle(String debtorcircle) {
			this.debtorcircle = debtorcircle;
		}
		public String getDebtorpolicestation() {
			return debtorpolicestation;
		}
		public void setDebtorpolicestation(String debtorpolicestation) {
			this.debtorpolicestation = debtorpolicestation;
		}
		@Override
		public String toString() {
			return "DebatorVo [debtorName=" + debtorName + ", debtorAddress=" + debtorAddress + ", debtorAddress1="
					+ debtorAddress1 + ", debtorAddress2=" + debtorAddress2 + ", debtorState=" + debtorState
					+ ", debtorCity=" + debtorCity + ", debtorDistrict=" + debtorDistrict + ", debtorPincode="
					+ debtorPincode + ", circle=" + circle + ", userId=" + userId + ", debtorfatherNames="
					+ debtorfatherNames + ", debtorubDivision=" + debtorubDivision + ", debtorcircle=" + debtorcircle
					+ ", debtorpolicestation=" + debtorpolicestation + ", debtorPhoneNumber=" + debtorPhoneNumber
					+ ", debtorStatePhoneNumber=" + debtorStatePhoneNumber + ", debtorEmail=" + debtorEmail + "]";
		}
		public DebatorVo() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	    
	    

}
