package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Person;
import com.plat.orcl.exception.PersonExistException;
import com.plat.orcl.service.impl.PersonServiceImpl;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.RegisterForm;

public class RegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//1.校验
		RegisterForm form = WebUtil.copyRequest2Bean(request, RegisterForm.class);
		boolean isOk = form.check();
		//2.校验失败注册页面回显校验信息
		if(!isOk){
			
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return ;
		}
		//3.校验成功调service
		Person p = new Person();
		WebUtil.copyBean(form,p);
		PersonServiceImpl ps = new PersonServiceImpl();
		try {
			//6.service处理成功跳到登陆页面 或跳到全局消息显示界面自动刷新回登陆界面
			ps.register(p);
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			request.setAttribute("message", p.getPname()+"您已注册成功！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
			
		} catch (PersonExistException e) {
			//4.service失败 如果是有人了 则 回到注册页面回显
			form.getErrors().put("pid", "此学生已经注册");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
			
		}catch (Exception e){
			//5.service失败 如果是其他错误  则 跳到全局消息显示界面
			request.setAttribute("message", "服务器未知错误！");
			request.getRequestDispatcher("/WEB-INF/jsp/error500.jsp").forward(request, response);
			return;
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
