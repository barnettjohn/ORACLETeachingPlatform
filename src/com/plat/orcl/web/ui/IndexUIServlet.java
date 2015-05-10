package com.plat.orcl.web.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Person;

public class IndexUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("person")==null){
			request.setAttribute("message", "非法登陆！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			if (((Person)request.getSession().getAttribute("person")).getPstate().equals("s")){
				request.getRequestDispatcher("/WEB-INF/jsp/indexstu.jsp").forward(request, response);
				return;
			}
			else if(((Person)request.getSession().getAttribute("person")).getPstate().equals("t")){
				request.getRequestDispatcher("/WEB-INF/jsp/indextea.jsp").forward(request, response);
				return;
			}else if(((Person)request.getSession().getAttribute("person")).getPstate().equals("a")){
				request.getRequestDispatcher("/WEB-INF/jsp/indextea.jsp").forward(request, response);
				return;
				
			}
		}
		
		
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
