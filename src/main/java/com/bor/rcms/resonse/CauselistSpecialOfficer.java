package com.bor.rcms.resonse;

public class CauselistSpecialOfficer {

	private  String objid;
	private  String bjectionerName;

	private  String respondedname;
	private  String dateOfFilling;
	
	private  String dateOfhearing;
	
	private  String hearingTime;

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getBjectionerName() {
		return bjectionerName;
	}

	public void setBjectionerName(String bjectionerName) {
		this.bjectionerName = bjectionerName;
	}

	public String getRespondedname() {
		return respondedname;
	}

	public void setRespondedname(String respondedname) {
		this.respondedname = respondedname;
	}

	public String getDateOfFilling() {
		return dateOfFilling;
	}

	public void setDateOfFilling(String dateOfFilling) {
		this.dateOfFilling = dateOfFilling;
	}

	public String getDateOfhearing() {
		return dateOfhearing;
	}

	public void setDateOfhearing(String dateOfhearing) {
		this.dateOfhearing = dateOfhearing;
	}

	

	public String getHearingTime() {
		return hearingTime;
	}

	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}

	@Override
	public String toString() {
		return "CauselistSpecialOfficer [objid=" + objid + ", bjectionerName=" + bjectionerName + ", respondedname="
				+ respondedname + ", dateOfFilling=" + dateOfFilling + ", dateOfhearing=" + dateOfhearing
				+ ", hearingTime=" + hearingTime + "]";
	}

	public CauselistSpecialOfficer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
