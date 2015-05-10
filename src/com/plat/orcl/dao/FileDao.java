package com.plat.orcl.dao;

import java.util.List;

import com.plat.orcl.domain.File;

public interface FileDao {

	void addFile(File f);

	void deleteFile(String fid);

	void deleteFileCascade(String fid);

	void deleteFileByString(String xid, String yid);

	void deleteFileByStringCascade(String xid, String yid);

	List<File> findFileByName(String fname);

	File findFileById(String fid);

	List<File> findFileByTime(String time1, String time2);

	List<File> findFileByString(String xid, String yid);

	List<File> findFileAllByString(String xid, String yid);
	
}