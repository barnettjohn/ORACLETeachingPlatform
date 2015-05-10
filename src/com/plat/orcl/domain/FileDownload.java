package com.plat.orcl.domain;

import java.util.Date;

public class FileDownload {

	private String fdid;
	private String fid;
	private String pid;
	private Date time;
	
	private Person p;
	private File f;
	
	public String getFdid() {
		return fdid;
	}
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
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
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	public File getF() {
		return f;
	}
	public void setF(File f) {
		this.f = f;
	}
	
}
