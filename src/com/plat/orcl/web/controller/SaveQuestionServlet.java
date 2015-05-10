package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.WebUtil;

public class SaveQuestionServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		CourseServiceImpl csi = new CourseServiceImpl();
		TestServiceImpl tsi = new TestServiceImpl();
		Person p = (Person) request.getSession().getAttribute("person");
		//将章节信息发过去
		ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
		request.setAttribute("chapterlist", chapterlist);
		
		//将处理存储信息
		Question q = WebUtil.copyRequest2Bean(request, Question.class);
		String chaptid = q.getChaptid();
		chaptid = chaptid.split(PubUtil.SEPARATOR)[0].trim();
		q.setChaptid(chaptid);
		q.setQid(PubUtil.createGUID());
		tsi.uploadQuestion(q);
		
		//取题列表
		ArrayList<Question> qlist = tsi.getAllQuestion();
		request.setAttribute("questionlist", qlist);
		
		
		WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/testtea.jsp", "/WEB-INF/jsp/teststu.jsp", request, response);
		return;
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
