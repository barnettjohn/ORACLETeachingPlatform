package com.plat.orcl.domain;

public class StuScore {
	private String pid;
	private String pidup;
	private String formid;
	private double score;
	private String detail;
	
	private Person p ;
	private Person pup ;
	private Form form ;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPidup() {
		return pidup;
	}
	public void setPidup(String pidup) {
		this.pidup = pidup;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	public Person getPup() {
		return pup;
	}
	public void setPup(Person pup) {
		this.pup = pup;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	
	
	
	
	
}
