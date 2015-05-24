package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.Test;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;

public class GetStuScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScoreServiceImpl ssi = new ScoreServiceImpl();
		String formid = request.getParameter("formid");
		String pid = request.getParameter("pid");
		Form form = ssi.getOneForm(formid);
		request.getSession().setAttribute("formid",formid);
		request.getSession().setAttribute("pid",pid);
		request.setAttribute("form", form);
		request.getRequestDispatcher("/WEB-INF/jsp/scorestu_dotest.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
