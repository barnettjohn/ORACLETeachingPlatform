package com.plat.orcl.domain;

import java.util.List;

public class Course {
	
	private String cid ;
	private String pid ;
	private String cname ;
	private String detail ;
	private String chnum ;
	
	
	private Person teacher;
	private List<CoursePlan> coursePlans;
	private List<Score> Scores;
	
	public String getChnum() {
		return chnum;
	}
	public void setChnum(String chnum) {
		this.chnum = chnum;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Person getTeacher() {
		return teacher;
	}
	public void setTeacher(Person teacher) {
		this.teacher = teacher;
	}
	public List<CoursePlan> getCoursePlans() {
		return coursePlans;
	}
	public void setCoursePlans(List<CoursePlan> coursePlans) {
		this.coursePlans = coursePlans;
	}
	public List<Score> getScores() {
		return Scores;
	}
	public void setScores(List<Score> scores) {
		Scores = scores;
	}
	
	public boolean check(){
		return false;
	}
	
	
}
