package com.plat.orcl.web.formbean;

import java.util.Date;
import java.util.List;

import com.plat.orcl.domain.FileDownload;
import com.plat.orcl.domain.Person;

public class FileForm {
	
	private String fid ;
	private String fname ;
	private String pidup ;
	private String url ;
	private String time ;
	private Person p;
	private List<FileDownload> fileDownloads;
	
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
