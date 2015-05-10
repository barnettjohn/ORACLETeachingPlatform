package com.plat.orcl.domain;

import java.util.ArrayList;
import java.util.Date;

public class Form {
	private String formid;
	private String chaptid;
	private String detail;
	private String fname;
	private Date time ;
	
	private Chapter chapter;
	private ArrayList<StuScore> stuScoreList;
	private ArrayList<FormQuestion> formquestionList;
	
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getChaptid() {
		return chaptid;
	}
	public void setChaptid(String chaptid) {
		this.chaptid = chaptid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public ArrayList<StuScore> getStuScoreList() {
		return stuScoreList;
	}
	public void setStuScoreList(ArrayList<StuScore> stuScoreList) {
		this.stuScoreList = stuScoreList;
	}
	public ArrayList<FormQuestion> getFormquestionList() {
		return formquestionList;
	}
	public void setFormquestionList(ArrayList<FormQuestion> formquestionList) {
		this.formquestionList = formquestionList;
	}
	
	
	
}
