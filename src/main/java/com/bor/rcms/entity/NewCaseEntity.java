package com.bor.rcms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "new_cases")
public class NewCaseEntity  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;

    private String actName;
    private String districtName;
    private String subDivision;
    private String immovable;
    private String halka;
    private String mozza;
    private String khattaNumber;
    private String plotNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Date createdDate;
    private Date modifiedDate;

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileEntity file;

    private String casteStatus;
    private String casteStages;

    public NewCaseEntity() {}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
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

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public String getImmovable() {
		return immovable;
	}

	public void setImmovable(String immovable) {
		this.immovable = immovable;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
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

	public FileEntity getFile() {
		return file;
	}

	public void setFile(FileEntity file) {
		this.file = file;
	}

	public String getCasteStatus() {
		return casteStatus;
	}

	public void setCasteStatus(String casteStatus) {
		this.casteStatus = casteStatus;
	}

	public String getCasteStages() {
		return casteStages;
	}

	public void setCasteStages(String casteStages) {
		this.casteStages = casteStages;
	}

	public NewCaseEntity(Long caseId, String actName, String districtName, String subDivision, String immovable,
			String halka, String mozza, String khattaNumber, String plotNumber, UserEntity user, Date createdDate,
			Date modifiedDate, FileEntity file, String casteStatus, String casteStages) {
		super();
		this.caseId = caseId;
		this.actName = actName;
		this.districtName = districtName;
		this.subDivision = subDivision;
		this.immovable = immovable;
		this.halka = halka;
		this.mozza = mozza;
		this.khattaNumber = khattaNumber;
		this.plotNumber = plotNumber;
		this.user = user;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.file = file;
		this.casteStatus = casteStatus;
		this.casteStages = casteStages;
	}
}
