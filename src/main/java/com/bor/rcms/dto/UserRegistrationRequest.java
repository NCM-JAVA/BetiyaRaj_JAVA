package com.bor.rcms.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserRegistrationRequest {
	
    private String userName;
    //
    private String  aadhar;
    private String category;
    private String city;
    private String gender;
    private String pincode;
    private String state;
    private String relation;
    //
    
    private String bankName;
    private String branchCode;
    
    
    private String alternatenumber;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String district;
    @JsonFormat(pattern = "dd-MM-yyyy")

    private Date dob;
    private String roleName; // Optional
    private String status;
    //add
    private String sector;
    private String department;
    
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
	
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "UserRegistrationRequest [userName=" + userName + ", aadhar=" + aadhar + ", category=" + category
				+ ", city=" + city + ", gender=" + gender + ", pincode=" + pincode + ", state=" + state + ", relation="
				+ relation + ", bankName=" + bankName + ", branchCode=" + branchCode + ", alternatenumber="
				+ alternatenumber + ", password=" + password + ", fullName=" + fullName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", district=" + district + ", dob=" + dob
				+ ", roleName=" + roleName + ", status=" + status + ", sector=" + sector + ", department=" + department
				+ "]";
	}
	public UserRegistrationRequest(String userName, String aadhar, String category, String city, String gender,
			String pincode, String state, String relation, String bankName, String branchCode, String alternatenumber,
			String password, String fullName, String email, String phoneNumber, String address, String district,
			Date dob, String roleName, String status, String sector, String department) {
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
		this.sector = sector;
		this.department = department;
	}
	
	
	

    
}

