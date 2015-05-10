package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Test;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.WebUtil;

public class SaveTestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TestServiceImpl tsi = new TestServiceImpl();
		Test t = WebUtil.copyRequest2Bean(request, Test.class);
		t.setTestid(PubUtil.createGUID());
		String[] qlist2 = request.getParameterValues("test_question2");
		tsi.saveTest(t, qlist2);
		
		ArrayList<Question> qlist = tsi.getAllQuestion();
		request.setAttribute("questionlist", qlist);
		request.getRequestDispatcher("/WEB-INF/jsp/testtea2.jsp").forward(request, response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
