package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.FormQuestion;
import com.plat.orcl.domain.Person;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.thread.SendMail;
import com.plat.orcl.utils.JdbcUtil;
import com.plat.orcl.utils.PubUtil;

public class UploadFormServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Person p = (Person) request.getSession().getAttribute("person");
		String chapterid = null ;
		ScoreServiceImpl ssi = new ScoreServiceImpl();
		Enumeration<String> e = request.getParameterNames();
		Form form = new Form();
		FormQuestion question ;
		//先添加form和formquestion
		while(e.hasMoreElements()){
			String name = e.nextElement();
			if(name.equals("fanme")){
				form.setFname(request.getParameter(name));
			}else if(name.equals("chapter")){
				chapterid=request.getParameter(name);
				form.setChaptid(chapterid);
			}
		}
		String formid = PubUtil.createGUID();
		form.setFormid(formid);
		ssi.addForm(form);
		while(e.hasMoreElements()){
			String name = e.nextElement();
			if(name.contains("question")){
				question = new FormQuestion();
				question.setFormid(form.getFormid());
				question.setFormqid(PubUtil.createGUID());
				question.setContent(request.getParameter(name));
				String power =request.getParameter("power"+name.substring(8));
				question.setPower(Integer.parseInt(power));
				ssi.addFormQuestion(question);
			}
		}
		//这里面直接将这些操作放到线程里面去做
		SendMail sendMail = new SendMail(p,chapterid,ssi,request.getServletContext());
		sendMail.start();
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
