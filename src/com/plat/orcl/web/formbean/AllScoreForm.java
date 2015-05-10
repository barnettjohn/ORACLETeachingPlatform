package com.plat.orcl.web.formbean;

import com.plat.orcl.domain.Course;
import com.plat.orcl.domain.Person;

public class AllScoreForm {
	private String cid;
	private String pid;
	private String stu;
	private String tea;
	private String test;
	private String finalscore;
	private String total;
	
	
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
	public String getStu() {
		return stu;
	}
	public void setStu(String stu) {
		this.stu = stu;
	}
	public String getTea() {
		return tea;
	}
	public void setTea(String tea) {
		this.tea = tea;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(String finalscore) {
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
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public void setTotal() {
		this.total =""+(Double.parseDouble(stu)+Double.parseDouble(tea)+Double.parseDouble(test)+Double.parseDouble(finalscore));
	}
	@Override
	public String toString() {
		return this.pid+","+this.p.getPname()+","+this.c.getCid()+","+this.c.getCname()+","+
			   this.getStu()+","+this.getTea()+","+this.getTest()+","+this.getFinalscore()+","+this.getTotal();
	}
	
	
}
