package com.plat.orcl.domain;

public class Score {
	
	private String cid ;
	private String pid ;
	private double stu ; 
	private double tea ; 
	private double finalscore ;
	
	private Course c;
	private Person p;
	
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
	public double getStu() {
		return stu;
	}
	public void setStu(double stu) {
		this.stu = stu;
	}
	public double getTea() {
		return tea;
	}
	public void setTea(double tea) {
		this.tea = tea;
	}
	public double getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(double finalscore) {
		this.finalscore = finalscore;
	}
	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	} 
	
	
	
}
