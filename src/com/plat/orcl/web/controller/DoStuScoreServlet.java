package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.FormQuestion;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;

public class DoStuScoreServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScoreServiceImpl ssi = new ScoreServiceImpl();
		String formid =  request.getParameter("formid");
		String pid = request.getParameter("pid");
		Person p = (Person) request.getSession().getAttribute("person");
		String pupid = p.getPid();
		Form f = ssi.getOneForm(formid);
		ArrayList<FormQuestion> fql = f.getFormquestionList();
		Enumeration<String> e =request.getParameterNames();
		
		double sum=0;
		double total=0;
		while(e.hasMoreElements()){
			String pn = e.nextElement();
			if(pn.startsWith("q_")){
				String[] s = pn.split("_");
				double power = Double.parseDouble(s[1]);
				double score = Double.parseDouble(request.getParameter(pn));
				sum+=power*score;
				total+=power*10;
			}
		}
		double finalScore = (sum/total)*100;
		ssi.updateStuScore(new StuScore(formid,pid,pupid,finalScore));
		response.sendRedirect(request.getContextPath()+"/servlet/ScoreDetailServlet?pid="+pid+"&formid"+formid);
		/*request.setAttribute("testscore", ts);
		request.setAttribute("test", tsi.getOneTest(testid));
		request.getRequestDispatcher("/WEB-INF/jsp/teststu3_seetest.jsp").forward(request, response);
		*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
