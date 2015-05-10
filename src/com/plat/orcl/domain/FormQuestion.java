package com.plat.orcl.domain;

public class FormQuestion {
	private String formqid;
	private String formid;
	private String content;
	private int power;
	private String detail;
	
	private Form f;
	
	public String getFormqid() {
		return formqid;
	}
	public void setFormqid(String formqid) {
		this.formqid = formqid;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Form getF() {
		return f;
	}
	public void setF(Form f) {
		this.f = f;
	}
	
	
	
}
