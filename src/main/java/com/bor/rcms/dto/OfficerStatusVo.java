package com.bor.rcms.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class OfficerStatusVo {
	
	
	private String admissionDate;
	private String admisionTime;
	
	private String affedefitDate;
	
	private String officerName;
	private String usertype;
	private Long   objId;
	private String caseId;
	private String remark;
	private String status;
	@JsonBackReference
    private OfficerStatusVo officerStatusVo;
	
	
	public OfficerStatusVo getOfficerStatusVo() {
		return officerStatusVo;
	}
	public void setOfficerStatusVo(OfficerStatusVo officerStatusVo) {
		this.officerStatusVo = officerStatusVo;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAdmisionTime() {
		return admisionTime;
	}
	public void setAdmisionTime(String admisionTime) {
		this.admisionTime = admisionTime;
	}
	
	public String getAffedefitDate() {
		return affedefitDate;
	}
	public void setAffedefitDate(String affedefitDate) {
		this.affedefitDate = affedefitDate;
	}
	public OfficerStatusVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Override
	public String toString() {
		return "OfficerStatusVo [admissionDate=" + admissionDate + ", admisionTime=" + admisionTime + ", affedefitDate="
				+ affedefitDate + ", officerName=" + officerName + ", usertype=" + usertype + ", objId=" + objId
				+ ", caseId=" + caseId + ", remark=" + remark + ", status=" + status + ", officerStatusVo="
				+ officerStatusVo + "]";
	}
	
	
	
	
	
	
	
}