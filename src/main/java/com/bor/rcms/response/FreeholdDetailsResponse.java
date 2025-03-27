package com.bor.rcms.response;

import java.util.List;

import com.bor.rcms.entity.DocumentEntity;
import com.bor.rcms.entity.NewObjection;

public class FreeholdDetailsResponse {
	private String objectionerName;
	private String specialOfficerName;
	private String phoneNumber;
	private String  email;
	private String address;
	private String alternativePhone;
	private String  relation;
	private NewObjection  newObjection;
	private List<DocumentEntity>  documentEntity;
	private String objection;
	
	
    private String propertyValue;
    private String freeholdAmmount;
    private String noInstallment;
    private String lastDatepayment;
	public String getObjectionerName() {
		return objectionerName;
	}
	public void setObjectionerName(String objectionerName) {
		this.objectionerName = objectionerName;
	}
	public String getSpecialOfficerName() {
		return specialOfficerName;
	}
	public void setSpecialOfficerName(String specialOfficerName) {
		this.specialOfficerName = specialOfficerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAlternativePhone() {
		return alternativePhone;
	}
	public void setAlternativePhone(String alternativePhone) {
		this.alternativePhone = alternativePhone;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public NewObjection getNewObjection() {
		return newObjection;
	}
	public void setNewObjection(NewObjection newObjection) {
		this.newObjection = newObjection;
	}
	public  List<DocumentEntity>   getDocumentEntity() {
		return documentEntity;
	}
	public void setDocumentEntity( List<DocumentEntity>   documentEntity) {
		this.documentEntity = documentEntity;
	}
	public String getObjection() {
		return objection;
	}
	public void setObjection(String objection) {
		this.objection = objection;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getFreeholdAmmount() {
		return freeholdAmmount;
	}
	public void setFreeholdAmmount(String freeholdAmmount) {
		this.freeholdAmmount = freeholdAmmount;
	}
	public String getNoInstallment() {
		return noInstallment;
	}
	public void setNoInstallment(String noInstallment) {
		this.noInstallment = noInstallment;
	}
	public String getLastDatepayment() {
		return lastDatepayment;
	}
	public void setLastDatepayment(String lastDatepayment) {
		this.lastDatepayment = lastDatepayment;
	}
	@Override
	public String toString() {
		return "FreeholdDetailsResponse [objectionerName=" + objectionerName + ", specialOfficerName="
				+ specialOfficerName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address
				+ ", alternativePhone=" + alternativePhone + ", relation=" + relation + ", newObjection=" + newObjection
				+ ", documentEntity=" + documentEntity + ", objection=" + objection + ", propertyValue=" + propertyValue
				+ ", freeholdAmmount=" + freeholdAmmount + ", noInstallment=" + noInstallment + ", lastDatepayment="
				+ lastDatepayment + "]";
	}
	public FreeholdDetailsResponse(String objectionerName, String specialOfficerName, String phoneNumber, String email,
			String address, String alternativePhone, String relation, NewObjection newObjection,
			 List<DocumentEntity>   documentEntity, String objection, String propertyValue, String freeholdAmmount,
			String noInstallment, String lastDatepayment) {
		super();
		this.objectionerName = objectionerName;
		this.specialOfficerName = specialOfficerName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.alternativePhone = alternativePhone;
		this.relation = relation;
		this.newObjection = newObjection;
		this.documentEntity = documentEntity;
		this.objection = objection;
		this.propertyValue = propertyValue;
		this.freeholdAmmount = freeholdAmmount;
		this.noInstallment = noInstallment;
		this.lastDatepayment = lastDatepayment;
	}
	public FreeholdDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
