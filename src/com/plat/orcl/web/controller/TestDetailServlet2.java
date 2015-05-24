package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestQuestion;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;

public class TestDetailServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = ((Person) request.getSession().getAttribute("person")).getPid();
		TestServiceImpl tsi = new TestServiceImpl();
		String testid = request.getParameter("testid");
		Test test = tsi.getOneTest(testid);
		TestScore ts = tsi.findTestScore(pid, testid, true);
		for(TestQuestion q:test.getqList()){
			Question que = q.getQ();
			String wrongq = ts.getWrongq();
			if(wrongq.contains(q.getQid())){
				int start = wrongq.indexOf(q.getQid())+q.getQid().length();
				int end = wrongq.indexOf(PubUtil.SEPARATOR, start);
				String real = wrongq.substring(start+1, end);
				que.setQright("<span class='label label-important'>"+que.getQright()+" 以前错误答案为："+real+"</span>");
			}
			
		}
		request.setAttribute("test", test);
		request.getRequestDispatcher("/WEB-INF/jsp/teststu3_seetest.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
