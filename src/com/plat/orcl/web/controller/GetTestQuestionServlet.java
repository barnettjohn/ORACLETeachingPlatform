package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Question;
import com.plat.orcl.service.impl.TestServiceImpl;

public class GetTestQuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		TestServiceImpl tsi = new TestServiceImpl();

		Enumeration<String> paraNames=request.getParameterNames();
		String s = "";
		for(Enumeration e=paraNames;e.hasMoreElements();){
			String thisName=e.nextElement().toString();
			Question q = tsi.getOneQuestion(thisName);
			if(q!=null){
				/*if(s.equals("")){
					s+="{\"qid\":\""+q.getQid()+"\",\"qname\":\""+q.getQname()+"\"}";
				}else{
					s+=",{\"qid\":\""+q.getQid()+"\",\"qname\":\""+q.getQname()+"\"}";
				}*/
				s+="<option selected value="+q.getQid()+">"+q.getQname()+"</option>";
			}
			
		}
		//out.print(s);
		ArrayList<Question> qlist = tsi.getAllQuestion();
		request.setAttribute("questionlist", qlist);
		request.getSession().setAttribute("qs", s);
		request.getRequestDispatcher("/WEB-INF/jsp/testtea2.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
