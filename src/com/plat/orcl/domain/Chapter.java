package com.plat.orcl.domain;

import java.util.ArrayList;

public class Chapter {
	private String chaptid;
	private String cid;
	private String chaptna;
	private String detial;
	private String difficulty;
	
	private Course c;
	private ArrayList<Person> p ;
	
	
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getChaptid() {
		return chaptid;
	}
	public void setChaptid(String chaptid) {
		this.chaptid = chaptid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getChaptna() {
		return chaptna;
	}
	public void setChaptna(String chaptna) {
		this.chaptna = chaptna;
	}
	public String getDetial() {
		return detial;
	}
	public void setDetial(String detial) {
		this.detial = detial;
	}
	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	public ArrayList<Person> getP() {
		return p;
	}
	public void setP(ArrayList<Person> p) {
		this.p = p;
	}
	
	
}
