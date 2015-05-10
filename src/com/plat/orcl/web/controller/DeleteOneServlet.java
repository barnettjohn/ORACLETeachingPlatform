package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.exception.PersonNullException;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.service.impl.PersonServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;

public class DeleteOneServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Person p = (Person) request.getSession().getAttribute("person");
		String qid = request.getParameter("qid");
		TestServiceImpl tsi = new TestServiceImpl();
		CourseServiceImpl csi = new CourseServiceImpl();

		
		ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
		request.setAttribute("chapterlist", chapterlist);
		
		Question q = tsi.getOneQuestion(qid);
		request.setAttribute("question", q);
		request.getRequestDispatcher("/WEB-INF/jsp/testtea_editquestion.jsp").forward(request, response);
		return;
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
