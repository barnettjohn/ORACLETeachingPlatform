package com.plat.orcl.web.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.dao.impl.FileDaoMysqlImpl;
import com.plat.orcl.domain.File;
import com.plat.orcl.domain.Person;
import com.plat.orcl.service.impl.FileServiceImpl;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.FileForm;

public class ResourceUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Person p = (Person) request.getSession().getAttribute("person");
		FileServiceImpl fs = new FileServiceImpl();
		
		ArrayList<File> l = fs.fileList(p.getPstate(),p.getPid());
		ArrayList<FileForm> formList = new ArrayList<FileForm>();
		FileForm ff = null;
		for(File f:l){
			ff =new FileForm();
			WebUtil.copyBean(f, ff);
			formList.add(ff);
		}
		
		request.setAttribute("filelist", formList);
		WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/resourcetea.jsp", "/WEB-INF/jsp/resourcestu.jsp", request, response);
		return;
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);;
	}

}
