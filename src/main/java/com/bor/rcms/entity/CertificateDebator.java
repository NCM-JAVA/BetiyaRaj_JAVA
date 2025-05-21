package com.bor.rcms.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class CertificateDebator {
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="debator_id")
	    private Long debatorId;
	    private String debatorName;
	    private String address;
	    private String email;
	    private String fatherNames;
	    private  String subDivision;
	    private  String circle;
	    private  String policestation;
	    
	    private String phoneNumber;
	    private  String otp;

	    private String address1;
	    private String address2;
	    private String state;
	    private String city;
	    private String district;
	    private String pincode;
	    private String createdDate;
	    private String modifiedDate;
	    private String status;
	    
	    @ManyToOne
	    @JoinColumn(name = "requeistion_id")  // foreign key column
	    @JsonIgnore
	    private FileRequeistion requeistion;
	    

		@CreationTimestamp
		@Column(updatable = false)
		private LocalDateTime currentdate;

		public LocalDateTime getCurrentdate() {
			return currentdate;
		}
		public void setCurrentdate(LocalDateTime currentdate) {
			this.currentdate = currentdate;
		}
		public Long getDebatorId() {
			return debatorId;
		}
		public void setDebatorId(Long debatorId) {
			this.debatorId = debatorId;
		}
		public String getDebatorName() {
			return debatorName;
		}
		public void setDebatorName(String debatorName) {
			this.debatorName = debatorName;
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
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
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
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public String getModifiedDate() {
			return modifiedDate;
		}
		public void setModifiedDate(String modifiedDate) {
			this.modifiedDate = modifiedDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public FileRequeistion getRequeistion() {
			return requeistion;
		}
		public void setRequeistion(FileRequeistion requeistion) {
			this.requeistion = requeistion;
		}
		public String getFatherNames() {
			return fatherNames;
		}
		public void setFatherNames(String fatherNames) {
			this.fatherNames = fatherNames;
		}
		public String getSubDivision() {
			return subDivision;
		}
		public void setSubDivision(String subDivision) {
			this.subDivision = subDivision;
		}
		public String getCircle() {
			return circle;
		}
		public void setCircle(String circle) {
			this.circle = circle;
		}
		public String getPolicestation() {
			return policestation;
		}
		public void setPolicestation(String policestation) {
			this.policestation = policestation;
		}
		
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
		}
		public CertificateDebator() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "CertificateDebator [debatorId=" + debatorId + ", debatorName=" + debatorName + ", address="
					+ address + ", email=" + email + ", fatherNames=" + fatherNames + ", subDivision=" + subDivision
					+ ", circle=" + circle + ", policestation=" + policestation + ", phoneNumber=" + phoneNumber
					+ ", otp=" + otp + ", address1=" + address1 + ", address2=" + address2 + ", state=" + state
					+ ", city=" + city + ", district=" + district + ", pincode=" + pincode + ", createdDate="
					+ createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status + ", requeistion="
					+ requeistion + ", currentdate=" + currentdate + "]";
		}
		
		
		
	    
	    
}