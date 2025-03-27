package com.bor.rcms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class LocationDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "loc_id")
    private Long locId;
    private String state;
    private String district;
    private String circle;
    private String halka;
    private String mauja;
    private String khatano;
    private String plotno;
    private String acre;
    private String dismil;
	public Long getLocId() {
		return locId;
	}
	public void setLocId(Long locId) {
		this.locId = locId;
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
	public String getKhatano() {
		return khatano;
	}
	public void setKhatano(String khatano) {
		this.khatano = khatano;
	}
	public String getPlotno() {
		return plotno;
	}
	public void setPlotno(String plotno) {
		this.plotno = plotno;
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
	@Override
	public String toString() {
		return "LocationDetails [locId=" + locId + ", state=" + state + ", district=" + district + ", circle=" + circle
				+ ", halka=" + halka + ", mauja=" + mauja + ", khatano=" + khatano + ", plotno=" + plotno + ", acre="
				+ acre + ", dismil=" + dismil + "]";
	}
	public LocationDetails(Long locId, String state, String district, String circle, String halka, String mauja,
			String khatano, String plotno, String acre, String dismil) {
		super();
		this.locId = locId;
		this.state = state;
		this.district = district;
		this.circle = circle;
		this.halka = halka;
		this.mauja = mauja;
		this.khatano = khatano;
		this.plotno = plotno;
		this.acre = acre;
		this.dismil = dismil;
	}
	public LocationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
   
    
    

}
