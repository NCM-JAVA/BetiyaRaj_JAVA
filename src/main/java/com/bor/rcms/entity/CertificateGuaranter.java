package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CertificateGuaranter {

	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="granter_id")
	    private Long granterId;
	    private String granterName;
	    private String fatherNames;
	    private  String subDivision;
	    private  String circle;
	    private  String policestation;
	    private String address;
	    private String email;
	//    @Column(unique = true, nullable = false)
	    private String phoneNumber;
	    private String address1;
	    private String address2;
	    private String state;
	    private String city;
	    private String district;
	    private String pincode;
	    private String createdDate;
	    private String modifiedDate;
	    private String status;
	    
	
		
		public String getGranterName() {
			return granterName;
		}
		public void setGranterName(String granterName) {
			this.granterName = granterName;
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
		public Long getGranterId() {
			return granterId;
		}
		public void setGranterId(Long granterId) {
			this.granterId = granterId;
		}
		public String getFatherName() {
			return fatherNames;
		}
		public void setFatherName(String fatherNames) {
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
		@Override
		public String toString() {
			return "CertificateGuaranter [granterId=" + granterId + ", granterName=" + granterName + ", fatherNames="
					+ fatherNames + ", subDivision=" + subDivision + ", circle=" + circle + ", policestation="
					+ policestation + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
					+ ", address1=" + address1 + ", address2=" + address2 + ", state=" + state + ", city=" + city
					+ ", district=" + district + ", pincode=" + pincode + ", createdDate=" + createdDate
					+ ", modifiedDate=" + modifiedDate + ", status=" + status + "]";
		}
		public CertificateGuaranter() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		
}
