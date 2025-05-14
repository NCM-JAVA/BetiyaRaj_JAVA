package com.bor.rcms.dto;

import javax.validation.constraints.*;

public class LegalRepersentativeVo {
	@NotBlank(message = "Name required")
    private String legalName;
	@Email
    private String legalemail;
	@NotBlank
    private String legalapartmentNumber;
    @NotBlank
    private String legalfatherNames;
    @NotBlank
    private  String legalsubDivision;
    @NotBlank
    private  String legalcircle;
    @NotBlank
    private  String legalpolicestation;
    @NotBlank(message = "Mobile No required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile No must be 10 digits")   
    private String legalphoneNumber;
    @NotBlank
    private String legaladdress;
    @NotBlank
    private String legaladdress1;
    @NotBlank
    private String legaladdress2;
    private String legalstate;
    @NotBlank
    private String legalcity;
    @NotBlank
    private String legaldistrict;
    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")   
    private String legalpincode;
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getLegalemail() {
		return legalemail;
	}
	public void setLegalemail(String legalemail) {
		this.legalemail = legalemail;
	}
	public String getLegalapartmentNumber() {
		return legalapartmentNumber;
	}
	public void setLegalapartmentNumber(String legalapartmentNumber) {
		this.legalapartmentNumber = legalapartmentNumber;
	}
	public String getLegalfatherNames() {
		return legalfatherNames;
	}
	public void setLegalfatherNames(String legalfatherNames) {
		this.legalfatherNames = legalfatherNames;
	}
	public String getLegalsubDivision() {
		return legalsubDivision;
	}
	public void setLegalsubDivision(String legalsubDivision) {
		this.legalsubDivision = legalsubDivision;
	}
	public String getLegalcircle() {
		return legalcircle;
	}
	public void setLegalcircle(String legalcircle) {
		this.legalcircle = legalcircle;
	}
	public String getLegalpolicestation() {
		return legalpolicestation;
	}
	public void setLegalpolicestation(String legalpolicestation) {
		this.legalpolicestation = legalpolicestation;
	}
	public String getLegalphoneNumber() {
		return legalphoneNumber;
	}
	public void setLegalphoneNumber(String legalphoneNumber) {
		this.legalphoneNumber = legalphoneNumber;
	}
	public String getLegaladdress() {
		return legaladdress;
	}
	public void setLegaladdress(String legaladdress) {
		this.legaladdress = legaladdress;
	}
	public String getLegaladdress1() {
		return legaladdress1;
	}
	public void setLegaladdress1(String legaladdress1) {
		this.legaladdress1 = legaladdress1;
	}
	public String getLegaladdress2() {
		return legaladdress2;
	}
	public void setLegaladdress2(String legaladdress2) {
		this.legaladdress2 = legaladdress2;
	}
	public String getLegalstate() {
		return legalstate;
	}
	public void setLegalstate(String legalstate) {
		this.legalstate = legalstate;
	}
	public String getLegalcity() {
		return legalcity;
	}
	public void setLegalcity(String legalcity) {
		this.legalcity = legalcity;
	}
	public String getLegaldistrict() {
		return legaldistrict;
	}
	public void setLegaldistrict(String legaldistrict) {
		this.legaldistrict = legaldistrict;
	}
	public String getLegalpincode() {
		return legalpincode;
	}
	public void setLegalpincode(String legalpincode) {
		this.legalpincode = legalpincode;
	}
	@Override
	public String toString() {
		return "LegalRepersentativeVo [legalName=" + legalName + ", legalemail=" + legalemail
				+ ", legalapartmentNumber=" + legalapartmentNumber + ", legalfatherNames=" + legalfatherNames
				+ ", legalsubDivision=" + legalsubDivision + ", legalcircle=" + legalcircle + ", legalpolicestation="
				+ legalpolicestation + ", legalphoneNumber=" + legalphoneNumber + ", legaladdress=" + legaladdress
				+ ", legaladdress1=" + legaladdress1 + ", legaladdress2=" + legaladdress2 + ", legalstate=" + legalstate
				+ ", legalcity=" + legalcity + ", legaldistrict=" + legaldistrict + ", legalpincode=" + legalpincode
				+ "]";
	}
  

}
