package com.bor.rcms.entity;

import javax.persistence.*;

@Entity
@Table(name = "case_status")
public class CaseStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseStatusId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private NewCaseEntity caseEntity;

    private String specialOfficerStatus;
    private String specialOfficerRemark;
    private String collectorOfficerStatus;
    private String collectorOfficerRemark;
    private String divisionalCommissionerStatus;
    private String divisionalCommissionerRemark;
    private String borStatus;
    private String borRemark;

    public CaseStatusEntity() {}

	public Long getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Long caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public NewCaseEntity getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(NewCaseEntity caseEntity) {
		this.caseEntity = caseEntity;
	}

	public String getSpecialOfficerStatus() {
		return specialOfficerStatus;
	}

	public void setSpecialOfficerStatus(String specialOfficerStatus) {
		this.specialOfficerStatus = specialOfficerStatus;
	}

	public String getSpecialOfficerRemark() {
		return specialOfficerRemark;
	}

	public void setSpecialOfficerRemark(String specialOfficerRemark) {
		this.specialOfficerRemark = specialOfficerRemark;
	}

	public String getCollectorOfficerStatus() {
		return collectorOfficerStatus;
	}

	public void setCollectorOfficerStatus(String collectorOfficerStatus) {
		this.collectorOfficerStatus = collectorOfficerStatus;
	}

	public String getCollectorOfficerRemark() {
		return collectorOfficerRemark;
	}

	public void setCollectorOfficerRemark(String collectorOfficerRemark) {
		this.collectorOfficerRemark = collectorOfficerRemark;
	}

	public String getDivisionalCommissionerStatus() {
		return divisionalCommissionerStatus;
	}

	public void setDivisionalCommissionerStatus(String divisionalCommissionerStatus) {
		this.divisionalCommissionerStatus = divisionalCommissionerStatus;
	}

	public String getDivisionalCommissionerRemark() {
		return divisionalCommissionerRemark;
	}

	public void setDivisionalCommissionerRemark(String divisionalCommissionerRemark) {
		this.divisionalCommissionerRemark = divisionalCommissionerRemark;
	}

	public String getBorStatus() {
		return borStatus;
	}

	public void setBorStatus(String borStatus) {
		this.borStatus = borStatus;
	}

	public String getBorRemark() {
		return borRemark;
	}

	public void setBorRemark(String borRemark) {
		this.borRemark = borRemark;
	}

	public CaseStatusEntity(Long caseStatusId, UserEntity user, NewCaseEntity caseEntity, String specialOfficerStatus,
			String specialOfficerRemark, String collectorOfficerStatus, String collectorOfficerRemark,
			String divisionalCommissionerStatus, String divisionalCommissionerRemark, String borStatus,
			String borRemark) {
		super();
		this.caseStatusId = caseStatusId;
		this.user = user;
		this.caseEntity = caseEntity;
		this.specialOfficerStatus = specialOfficerStatus;
		this.specialOfficerRemark = specialOfficerRemark;
		this.collectorOfficerStatus = collectorOfficerStatus;
		this.collectorOfficerRemark = collectorOfficerRemark;
		this.divisionalCommissionerStatus = divisionalCommissionerStatus;
		this.divisionalCommissionerRemark = divisionalCommissionerRemark;
		this.borStatus = borStatus;
		this.borRemark = borRemark;
	}
}
