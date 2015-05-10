package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.PersonNullException;
import com.plat.orcl.service.impl.PersonServiceImpl;

public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pid = request.getParameter("pid");
		String password = request.getParameter("password");
		
		PersonServiceImpl ps = new PersonServiceImpl();
		try {
			Person p = ps.login(pid, password);
			HttpSession session = request.getSession();
			session.setAttribute("person", p);
			//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
			response.setCharacterEncoding("UTF-8");
			response.sendRedirect(request.getContextPath()+"/servlet/IndexUIServlet");
		} catch (PersonNullException e) {
			request.setAttribute("message", "用户名或密码不正确！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
