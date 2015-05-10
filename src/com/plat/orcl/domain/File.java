package com.plat.orcl.domain;

import java.util.Date;
import java.util.List;

public class File {
	
	private String fid ;
	private String fname ;
	private String pidup ;
	private String url ;
	private Date time ;
	private Date authority;//权限时间 用于限制文件的下载日期
	
	private Person p;
	private List<FileDownload> fileDownloads;
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getPidup() {
		return pidup;
	}
	public void setPidup(String pidup) {
		this.pidup = pidup;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getAuthority() {
		return authority;
	}
	public void setAuthority(Date authority) {
		this.authority = authority;
	}
	public Person getP() {
		return p;
	}
	public void setP(Person p) {
		this.p = p;
	}
	public List<FileDownload> getFileDownloads() {
		return fileDownloads;
	}
	public void setFileDownloads(List<FileDownload> fileDownloads) {
		this.fileDownloads = fileDownloads;
	}
	
	
}
