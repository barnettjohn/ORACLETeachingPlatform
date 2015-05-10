package com.plat.orcl.domain;

import java.util.List;

public class TimePlan {
	
	private String tid ;
	private String tdetail ;
	private String tsign ;
	private String tweek ;
	
	private List<CoursePlan> coursePlans ;
	
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTdetail() {
		return tdetail;
	}
	public void setTdetail(String tdetail) {
		this.tdetail = tdetail;
	}
	public String getTsign() {
		return tsign;
	}
	public void setTsign(String tsign) {
		this.tsign = tsign;
	}
	public String getTweek() {
		return tweek;
	}
	public void setTweek(String tweek) {
		this.tweek = tweek;
	}
	
	public List<CoursePlan> getCoursePlans() {
		return coursePlans;
	}
	public void setCoursePlans(List<CoursePlan> coursePlans) {
		this.coursePlans = coursePlans;
	}
	
}
