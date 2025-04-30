package com.bor.rcms.dto;

import java.util.Date;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserRegistrationRequest {
	@NotBlank(message = "Name required")
	@Size(max = 50, message = "Max 50 chars")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
	private String userName;
	//
	@Pattern(regexp = "^[0-9]{12}$", message = "aadhar must be 12 digits")

	private String aadhar;
	private String category;
	private String city;
	private String gender;
	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	private String pincode;
	private String state;
	private String relation;
	//

	private String bankName;
	@NotNull(message = "Branch code cannot be null")
	@Size(min = 3, max = 20, message = "Branch code must be between 3 and 10 characters")
	@Pattern(regexp = "^[a-zA-Z]{2}[a-zA-Z0-9]*$", message = "Branch code must start with 2 letters and contain only alphanumeric characters")
	private String branchCode;

	private String alternatenumber;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must have 1 digit, 1 lowercase, 1 uppercase, 1 special char, no spaces")
	private String password;

	@NotBlank(message = "Name required")
	@Size(max = 50, message = "Max 50 chars")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
	private String fullName;
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "^[0-9]{10}$", message = "aadhar must be 10 digits")
	private String phoneNumber;
	@NotBlank
	private String address;
	private String district;
	@JsonFormat(pattern = "dd-MM-yyyy")

	private Date dob;
	private String roleName; // Optional
	private String status;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getAlternatenumber() {
		return alternatenumber;
	}

	public void setAlternatenumber(String alternatenumber) {
		this.alternatenumber = alternatenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserRegistrationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public UserRegistrationRequest(String userName, String aadhar, String category, String city, String gender,
			String pincode, String state, String relation, String bankName, String branchCode, String alternatenumber,
			String password, String fullName, String email, String phoneNumber, String address, String district,
			Date dob, String roleName, String status) {
		super();
		this.userName = userName;
		this.aadhar = aadhar;
		this.category = category;
		this.city = city;
		this.gender = gender;
		this.pincode = pincode;
		this.state = state;
		this.relation = relation;
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.alternatenumber = alternatenumber;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.district = district;
		this.dob = dob;
		this.roleName = roleName;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserRegistrationRequest [userName=" + userName + ", aadhar=" + aadhar + ", category=" + category
				+ ", city=" + city + ", gender=" + gender + ", pincode=" + pincode + ", state=" + state + ", relation="
				+ relation + ", bankName=" + bankName + ", branchCode=" + branchCode + ", alternatenumber="
				+ alternatenumber + ", password=" + password + ", fullName=" + fullName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", district=" + district + ", dob=" + dob
				+ ", roleName=" + roleName + ", status=" + status + "]";
	}

}
