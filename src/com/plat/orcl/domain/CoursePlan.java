package com.plat.orcl.domain;

public class CoursePlan {
	
	private String cid ;
	private String tid ;
	
	private TimePlan tp ;
	private Course c ;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public TimePlan getTp() {
		return tp;
	}
	public void setTp(TimePlan tp) {
		this.tp = tp;
	}
	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	
}
