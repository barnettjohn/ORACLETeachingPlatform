package com.plat.orcl.domain;

public class Question {
	private String qid;
	private String qname;
	private String qcontent;
	private String qright;
	private String qdetail;
	private String chaptid;
	private String pupid;
	
	private Chapter chapter;
	private Person p;
	
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getQright() {
		return qright;
	}
	public void setQright(String qright) {
		this.qright = qright;
	}
	public String getQdetail() {
		return qdetail;
	}
	public void setQdetail(String qdetail) {
		this.qdetail = qdetail;
	}
	public String getChaptid() {
		return chaptid;
	}
	public void setChaptid(String chaptid) {
		this.chaptid = chaptid;
	}
	public String getPupid() {
		return pupid;
	}
	public void setPupid(String pupid) {
		this.pupid = pupid;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	
	
}
