package com.bor.rcms.entity;

import javax.persistence.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

  //  @Column(unique = true, nullable = false)
    private String userName;

    private String password;

    private String fullName;
    private String address;
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String alternateNumber;
    private String relationName;
    private String adhar;
    
    private String panNumber;
    private String address1;
    private String address2;
    private String state;
    private String city;
    private String district;
    private String pincode;
    private String gender;
    private String category;
    private String otp;
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    private Date createdDate;
    private Date modifiedDate;
    private String status;
    //pdr ADD
    private String bankName;
    private String branchCode;
    
   
    @ManyToMany
    @JoinTable(
        name = "user_group_mapping", // Change table name to avoid conflicts
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<UserGroupEntity> groups;

    public UserEntity() {}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getAdhar() {
		return adhar;
	}

	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<UserGroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(Set<UserGroupEntity> groups) {
		this.groups = groups;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", password=" + password + ", fullName="
				+ fullName + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", alternateNumber=" + alternateNumber + ", relationName=" + relationName + ", adhar=" + adhar
				+ ", panNumber=" + panNumber + ", address1=" + address1 + ", address2=" + address2 + ", state=" + state
				+ ", city=" + city + ", district=" + district + ", pincode=" + pincode + ", gender=" + gender
				+ ", category=" + category + ", otp=" + otp + ", dob=" + dob + ", role=" + role + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status + ", bankName=" + bankName
				+ ", branchCode=" + branchCode + ", groups=" + groups + "]";
	}

	public UserEntity(Long userId, String userName, String password, String fullName, String address, String email,
			String phoneNumber, String alternateNumber, String relationName, String adhar, String panNumber,
			String address1, String address2, String state, String city, String district, String pincode, String gender,
			String category, String otp, Date dob, RoleEntity role, Date createdDate, Date modifiedDate, String status,
			String bankName, String branchCode, Set<UserGroupEntity> groups) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.alternateNumber = alternateNumber;
		this.relationName = relationName;
		this.adhar = adhar;
		this.panNumber = panNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.state = state;
		this.city = city;
		this.district = district;
		this.pincode = pincode;
		this.gender = gender;
		this.category = category;
		this.otp = otp;
		this.dob = dob;
		this.role = role;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.groups = groups;
	}

	

	
	
	
	

	
}
