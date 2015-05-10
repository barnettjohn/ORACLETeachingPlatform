package com.plat.orcl.domain;

import java.util.Date;
import java.util.List;

public class Test {
	private String testid;
	private String testname;
	private Date time ;
	private String detail;
	private String cid;
	
	private List<TestScore> sList; 
	private List<TestQuestion> qList;
	
	private Course c ;
	
	public String getTestid() {
		return testid;
	}
	public void setTestid(String testid) {
		this.testid = testid;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<TestScore> getsList() {
		return sList;
	}
	public void setsList(List<TestScore> sList) {
		this.sList = sList;
	}
	public List<TestQuestion> getqList() {
		return qList;
	}
	public void setqList(List<TestQuestion> qList) {
		this.qList = qList;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	
	
	
}
