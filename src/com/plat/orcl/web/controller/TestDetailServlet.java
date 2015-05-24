package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Test;
import com.plat.orcl.service.impl.TestServiceImpl;

public class TestDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TestServiceImpl tsi = new TestServiceImpl();
		String  testid = request.getParameter("testid");
		Test test = tsi.getOneTest(testid);
		request.setAttribute("test", test);
		request.getRequestDispatcher("/WEB-INF/jsp/teststu3_dotest.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
