package com.plat.orcl.domain;

import java.util.Date;

public class SignIn {
	
	
	private int siid ;
	private String pid ;
	private Date time ;
	
	private Person p ;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getSiid() {
		return siid;
	}
	public void setSiid(int siid) {
		this.siid = siid;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	
	
}
