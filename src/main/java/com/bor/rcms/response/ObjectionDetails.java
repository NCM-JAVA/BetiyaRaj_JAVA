package com.bor.rcms.response;

import javax.persistence.Column;

import com.bor.rcms.entity.UserEntity;

public class ObjectionDetails {

	 private Long objectionId;
	    private String actName;
	    private String districtName;
	    private String circleName;
	    private String halka;
	    private String mozza;
	    private String khattaNumber;
	    private String plotNumber;
	    private String acre;
	    private String state;
	    
	    private String dismil;
	    private String objection;
	    private String documentType;
	    
	    private UserEntity userId;
	    private String currentDate;
	    private String updateDate;
	    private String tokenNo;

		public Long getObjectionId() {
			return objectionId;
		}

		public void setObjectionId(Long objectionId) {
			this.objectionId = objectionId;
		}

		public String getActName() {
			return actName;
		}

		public void setActName(String actName) {
			this.actName = actName;
		}

		public String getDistrictName() {
			return districtName;
		}

		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}

		public String getCircleName() {
			return circleName;
		}

		public void setCircleName(String circleName) {
			this.circleName = circleName;
		}

		public String getHalka() {
			return halka;
		}

		public void setHalka(String halka) {
			this.halka = halka;
		}

		public String getMozza() {
			return mozza;
		}

		public void setMozza(String mozza) {
			this.mozza = mozza;
		}

		public String getKhattaNumber() {
			return khattaNumber;
		}

		public void setKhattaNumber(String khattaNumber) {
			this.khattaNumber = khattaNumber;
		}

		public String getPlotNumber() {
			return plotNumber;
		}

		public void setPlotNumber(String plotNumber) {
			this.plotNumber = plotNumber;
		}

		public String getAcre() {
			return acre;
		}

		public void setAcre(String acre) {
			this.acre = acre;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getDismil() {
			return dismil;
		}

		public void setDismil(String dismil) {
			this.dismil = dismil;
		}

		public String getObjection() {
			return objection;
		}

		public void setObjection(String objection) {
			this.objection = objection;
		}

		public String getDocumentType() {
			return documentType;
		}

		public void setDocumentType(String documentType) {
			this.documentType = documentType;
		}

	
		public UserEntity getUserId() {
			return userId;
		}

		public void setUserId(UserEntity userId) {
			this.userId = userId;
		}

		public String getTokenNo() {
			return tokenNo;
		}

		public void setTokenNo(String tokenNo) {
			this.tokenNo = tokenNo;
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

		public ObjectionDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "ObjectionDetails [objectionId=" + objectionId + ", actName=" + actName + ", districtName="
					+ districtName + ", circleName=" + circleName + ", halka=" + halka + ", mozza=" + mozza
					+ ", khattaNumber=" + khattaNumber + ", plotNumber=" + plotNumber + ", acre=" + acre + ", state="
					+ state + ", dismil=" + dismil + ", objection=" + objection + ", documentType=" + documentType
					+ ", userId=" + userId + ", currentDate=" + currentDate + ", updateDate=" + updateDate
					+ ", tokenNo=" + tokenNo + "]";
		}

	

	
	    
}
