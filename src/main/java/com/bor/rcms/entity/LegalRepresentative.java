package com.bor.rcms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "legal_representative")
public class LegalRepresentative {
	  @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="legal_id")
	    private Long legalId;
	    private String legalName;
	    private String email;
	    private String apartmentNumber;


	    private String fatherNames;
	    private  String subDivision;
	    private  String circle;
	    private  String policestation;
	    
	    private String phoneNumber;
	    private String address;
	    private String address1;
	    private String address2;
	    private String state;
	    private String city;
	    private String district;
	    private String pincode;
	    @Column(name = "current_date_str")
	    private String currentDate;
		 @Column(name = "updateDate", length = 30)
	    private String updateDate;
	    private String status;
	    
	    
	    @OneToOne
	    @JoinColumn(name = "requeistion_id", referencedColumnName = "requeistion_id")
	    private FileRequeistion fileRequeistion;
		public Long getLegalId() {
			return legalId;
		}
		public void setLegalId(Long legalId) {
			this.legalId = legalId;
		}
		public String getLegalName() {
			return legalName;
		}
		public void setLegalName(String legalName) {
			this.legalName = legalName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getApartmentNumber() {
			return apartmentNumber;
		}
		public void setApartmentNumber(String apartmentNumber) {
			this.apartmentNumber = apartmentNumber;
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
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		public String getCurrentDate() {
			return currentDate;
		}
		public void setCurrentDate(String currentDate) {
			this.currentDate = currentDate;
		}
		public String getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		public FileRequeistion getFileRequeistion() {
			return fileRequeistion;
		}
		public void setFileRequeistion(FileRequeistion fileRequeistion) {
			this.fileRequeistion = fileRequeistion;
		}
		
		@Override
		public String toString() {
			return "LegalRepresentative [legalId=" + legalId + ", legalName=" + legalName + ", email=" + email
					+ ", apartmentNumber=" + apartmentNumber + ", fatherNames=" + fatherNames + ", subDivision="
					+ subDivision + ", circle=" + circle + ", policestation=" + policestation + ", phoneNumber="
					+ phoneNumber + ", address=" + address + ", address1=" + address1 + ", address2=" + address2
					+ ", state=" + state + ", city=" + city + ", district=" + district + ", pincode=" + pincode
					+ ", currentDate=" + currentDate + ", updateDate=" + updateDate + ", status=" + status
					+ ", fileRequeistion=" + fileRequeistion + "]";
		}
		public LegalRepresentative() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    

}
