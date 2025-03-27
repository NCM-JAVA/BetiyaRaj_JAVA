package com.bor.rcms.resonse;

import java.util.List;

import com.bor.rcms.entity.NewObjection;

public class Casesinform {
	private String totalCase;
	private String prendingCase;
	private String  totalAppeal;
	private  String   totalClose;
	private List<NewObjection> listobjct;
	public String getTotalCase() {
		return totalCase;
	}
	public void setTotalCase(String totalCase) {
		this.totalCase = totalCase;
	}
	public String getPrendingCase() {
		return prendingCase;
	}
	public void setPrendingCase(String prendingCase) {
		this.prendingCase = prendingCase;
	}
	public String getTotalAppeal() {
		return totalAppeal;
	}
	public void setTotalAppeal(String totalAppeal) {
		this.totalAppeal = totalAppeal;
	}
	public String getTotalClose() {
		return totalClose;
	}
	public void setTotalClose(String totalClose) {
		this.totalClose = totalClose;
	}
	public List<NewObjection> getListobjct() {
		return listobjct;
	}
	public void setListobjct(List<NewObjection> listobjct) {
		this.listobjct = listobjct;
	}
	@Override
	public String toString() {
		return "Casesinform [totalCase=" + totalCase + ", prendingCase=" + prendingCase + ", totalAppeal=" + totalAppeal
				+ ", totalClose=" + totalClose + ", listobjct=" + listobjct + "]";
	}
	public Casesinform() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Casesinform(String totalCase, String prendingCase, String totalAppeal, String totalClose,
			List<NewObjection> listobjct) {
		super();
		this.totalCase = totalCase;
		this.prendingCase = prendingCase;
		this.totalAppeal = totalAppeal;
		this.totalClose = totalClose;
		this.listobjct = listobjct;
	}
	
	

}
