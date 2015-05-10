package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Course;
import com.plat.orcl.domain.Person;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.utils.WebUtil;

public class UpdateCourserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Person p = (Person) request.getSession().getAttribute("person");
		CourseServiceImpl csi = new CourseServiceImpl();
		Course c = WebUtil.copyRequest2Bean(request, Course.class);
		csi.UpdateCourse(c);
		ArrayList<Course> lc = csi.getCourseList(p.getPstate(), p.getPid());
		ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
		request.setAttribute("clist", lc);
		request.setAttribute("chapterlist", chapterlist);
		request.getRequestDispatcher("/WEB-INF/jsp/coursetea.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
