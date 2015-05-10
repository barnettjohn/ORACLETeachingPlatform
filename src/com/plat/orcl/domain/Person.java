package com.plat.orcl.domain;

import java.util.List;

public class Person {
	
	private String pid ;
	private String pname ;
	private String password ;
	private String pclass ;
	private String email ;
	private String group ;
	private String pstate ;
	
	private List<Score> scores;
	private List<SignIn> signIns;
	private List<File> fileUps;
	private List<FileDownload> fileDowns;
	private List<Course> teaCourses;	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPclass() {
		return pclass;
	}
	public void setPclass(String pclass) {
		this.pclass = pclass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPstate() {
		return pstate;
	}
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	public List<SignIn> getSignIns() {
		return signIns;
	}
	public void setSignIns(List<SignIn> signIns) {
		this.signIns = signIns;
	}
	public List<File> getFileUps() {
		return fileUps;
	}
	public void setFileUps(List<File> fileUps) {
		this.fileUps = fileUps;
	}
	public List<FileDownload> getFileDowns() {
		return fileDowns;
	}
	public void setFileDowns(List<FileDownload> fileDowns) {
		this.fileDowns = fileDowns;
	}
	public List<Course> getTeaCourses() {
		return teaCourses;
	}
	public void setTeaCourses(List<Course> teaCourses) {
		this.teaCourses = teaCourses;
	}
	
	
	
}
