package com.bor.rcms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FreeholdIntitalCollector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String occupantName;
    private String occupantAdress;
    private String occupantPhone;
    private String occupantGender;
    private String createdDate;
    private String modifiedDate;
    private String createdBy;

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

    // Relationship to DocumentEntity
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private DocumentEntity documentEntity;
    
    @ManyToOne(cascade = CascadeType.PERSIST) // Cascade the persist operation
    @JoinColumn(name = "caseCollectorId", referencedColumnName = "case_collector_id")
    private CaseCollector caseCollector;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
    }

	public String getOccupantName() {
		return occupantName;
	}

	public void setOccupantName(String occupantName) {
		this.occupantName = occupantName;
	}

	public String getOccupantAdress() {
		return occupantAdress;
	}

	public void setOccupantAdress(String occupantAdress) {
		this.occupantAdress = occupantAdress;
	}

	public String getOccupantPhone() {
		return occupantPhone;
	}

	public void setOccupantPhone(String occupantPhone) {
		this.occupantPhone = occupantPhone;
	}

	public String getOccupantGender() {
		return occupantGender;
	}

	public void setOccupantGender(String occupantGender) {
		this.occupantGender = occupantGender;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public CaseCollector getCaseCollector() {
		return caseCollector;
	}

	public void setCaseCollector(CaseCollector caseCollector) {
		this.caseCollector = caseCollector;
	}

	@Override
	public String toString() {
		return "FreeholdIntitalCollector [id=" + id + ", occupantName=" + occupantName + ", occupantAdress="
				+ occupantAdress + ", occupantPhone=" + occupantPhone + ", occupantGender=" + occupantGender
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy
				+ ", actName=" + actName + ", districtName=" + districtName + ", circleName=" + circleName + ", halka="
				+ halka + ", mozza=" + mozza + ", khattaNumber=" + khattaNumber + ", plotNumber=" + plotNumber
				+ ", acre=" + acre + ", state=" + state + ", dismil=" + dismil + ", objection=" + objection
				+ ", documentEntity=" + documentEntity + ", caseCollector=" + caseCollector + "]";
	}

	public FreeholdIntitalCollector() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

    // Other getters and setters...
}
