package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Score;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.WebUtil;

public class TeaScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ScoreServiceImpl ssi = new ScoreServiceImpl();
		
		//将处理存储信息
		Enumeration<String> paraNames=request.getParameterNames();
		String s = "";
		Score sc = new Score();
		for(Enumeration e=paraNames;e.hasMoreElements();){
			String score=e.nextElement().toString();
			String[]sa = score.split("_");
			if(sa.length==4){
				double s2=0;
				try {
					if(sa[3].equals("tea")){
						s2 = Double.parseDouble(request.getParameter(score));
						sc.setCid(sa[1]);
						sc.setPid(sa[2]);
						sc.setTea(s2);
						ssi.updateTeaScore(sc);
					}
					if(sa[3].equals("finalscore")){
						s2 = Double.parseDouble(request.getParameter(score));
						sc.setCid(sa[1]);
						sc.setPid(sa[2]);
						sc.setFinalscore(s2);
						ssi.updateFinallScore(sc);
					}
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		}
		//取（教师）分数列表
		ArrayList<Score> sl  = ssi.getTeaScoreList();
		request.setAttribute("scorelist", sl);
		request.getRequestDispatcher("/WEB-INF/jsp/scoretea2.jsp").forward(request, response);
		return;
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
