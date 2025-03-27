package com.bor.rcms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ObjectionVo {
	private String state;
    private String district;
    private String circle;
	private String halka;
    private String mauja;
    private String khata;
	private String plot;
    private String acre;
    private String dismil;
    private String objremark;
    private String documentType;
    
    private String userId;
    private String objId;
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
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getHalka() {
		return halka;
	}
	public void setHalka(String halka) {
		this.halka = halka;
	}
	public String getMauja() {
		return mauja;
	}
	public void setMauja(String mauja) {
		this.mauja = mauja;
	}
	public String getKhata() {
		return khata;
	}
	public void setKhata(String khata) {
		this.khata = khata;
	}
	public String getPlot() {
		return plot;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getAcre() {
		return acre;
	}
	public void setAcre(String acre) {
		this.acre = acre;
	}
	public String getDismil() {
		return dismil;
	}
	public void setDismil(String dismil) {
		this.dismil = dismil;
	}
	public String getObjremark() {
		return objremark;
	}
	public void setObjremark(String objremark) {
		this.objremark = objremark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	@Override
	public String toString() {
		return "ObjectionVo [state=" + state + ", district=" + district + ", circle=" + circle + ", halka=" + halka
				+ ", mauja=" + mauja + ", khata=" + khata + ", plot=" + plot + ", acre=" + acre + ", dismil=" + dismil
				+ ", objremark=" + objremark + ", documentType=" + documentType + ", userId=" + userId + ", objId="
				+ objId + "]";
	}
	public ObjectionVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
