package com.plat.orcl.domain;

public class TestScore {
	private String pid;
	private String testid;
	private double score;
	
	private Person p;
	private Test t;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTestid() {
		return testid;
	}
	public void setTestid(String testid) {
		this.testid = testid;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	public Test getT() {
		return t;
	}
	public void setT(Test t) {
		this.t = t;
	}
	
	
	
}
