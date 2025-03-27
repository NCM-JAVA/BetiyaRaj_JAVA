package com.bor.rcms.response;

import java.util.List;

import com.bor.rcms.entity.NewObjection;

public class ObjectionStatus {
	
	private String totalCase;
	private String pendingCase;

	private String closeCase;

	private String rejectCase;
	
    private List<NewObjection> objections; // Store a list of NewObjection objects

	public String getTotalCase() {
		return totalCase;
	}

	public String getPendingCase() {
		return pendingCase;
	}

	public void setPendingCase(String pendingCase) {
		this.pendingCase = pendingCase;
	}

	public String getCloseCase() {
		return closeCase;
	}

	public void setCloseCase(String closeCase) {
		this.closeCase = closeCase;
	}

	public String getRejectCase() {
		return rejectCase;
	}

	public void setRejectCase(String rejectCase) {
		this.rejectCase = rejectCase;
	}

	public List<NewObjection> getObjections() {
		return objections;
	}

	public void setObjections(List<NewObjection> objections) {
		this.objections = objections;
	}

	public void setTotalCase(String totalCase) {
		this.totalCase = totalCase;
	}

	@Override
	public String toString() {
		return "ObjectionStatus [totalCase=" + totalCase + ", pendingCase=" + pendingCase + ", closeCase=" + closeCase
				+ ", rejectCase=" + rejectCase + ", objections=" + objections + "]";
	}

	public ObjectionStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjectionStatus(String totalCase, String pendingCase, String closeCase, String rejectCase,
			List<NewObjection> objections) {
		super();
		this.totalCase = totalCase;
		this.pendingCase = pendingCase;
		this.closeCase = closeCase;
		this.rejectCase = rejectCase;
		this.objections = objections;
	}

	

}
