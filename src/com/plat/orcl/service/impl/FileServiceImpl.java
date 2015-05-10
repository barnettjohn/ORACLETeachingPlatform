package com.plat.orcl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.plat.orcl.dao.FileDao;
import com.plat.orcl.dao.FileDownloadDao;
import com.plat.orcl.dao.impl.FileDaoMysqlImpl;
import com.plat.orcl.dao.impl.FileDownloadDaoMysqlImpl;
import com.plat.orcl.domain.File;
import com.plat.orcl.domain.Person;
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.ServiceUtil;


public class FileServiceImpl {
	private FileDao fileDao = new FileDaoMysqlImpl();
	private FileDownloadDao fileDDao = new FileDownloadDaoMysqlImpl();
	//上传业务
	public void upload(File f) {
		fileDao.addFile(f);
	}
	
	//获取文件列表
	public ArrayList<File> fileList(String pState,String p){
		ArrayList<File> l = null;
		if(pState.equals("t")||pState.equals("a")){
			l = (ArrayList<File>) fileDao.findFileAllByString("1", "1");
		}else if(pState.equals("s")){
			l = (ArrayList<File>) fileDao.findFileAllByString("1", "1");
			for (Iterator iterator = l.iterator(); iterator.hasNext();) {
				File file = (File) iterator.next();
				if(file.getAuthority().getTime()>System.currentTimeMillis()){
					iterator.remove();
				}
			}
		}
		return l;
	}
	
	
	
	//下载业务
	public void dowload(String fid){
		
	}

	public ArrayList<java.io.File> name2File(Enumeration<String> fileIds) {
		ArrayList<java.io.File> l =new ArrayList<java.io.File>();
		while(fileIds.hasMoreElements()){
			String fileId=fileIds.nextElement();
			String filePath;
			try {
				filePath = fileDao.findFileById(fileId).getUrl();
			} catch (NullPointerException e) {
				System.out.println("参数名不是文件");
				continue;
			}
			java.io.File f = new java.io.File(filePath);
			l.add(f);
		}
		return l;
	}

	
}
