package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestQuestion;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;

public class ScoreDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pidup = ((Person) request.getSession().getAttribute("person")).getPid();
		
		ScoreServiceImpl ssi = new ScoreServiceImpl();
		String formid = request.getParameter("formid");
		String pid = request.getParameter("pid");
		
		Form form = ssi.getOneForm(formid);
		StuScore ss = ssi.getStuScoreList("formid='"+formid+"' and pid='"+pid+"' and pidup",pidup ).get(0);
			
		request.setAttribute("form", form);
		request.setAttribute("stuscore", ss);
		request.getRequestDispatcher("/WEB-INF/jsp/scorestu_seetest.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
