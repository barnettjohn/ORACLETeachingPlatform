package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.dao.impl.ChapterDaoMysImpl;
import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Question;
import com.plat.orcl.service.impl.TestServiceImpl;

public class GetChapterAjaxServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String s = "";
		ChapterDaoMysImpl tsi = new ChapterDaoMysImpl();
		List<Chapter> list = tsi.findAll();
		for(Chapter chapter:list){
			s+="<option value='"+chapter.getChaptid()+"'>"+chapter.getChaptna()+"</option>";
		}
		out.print(s);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
