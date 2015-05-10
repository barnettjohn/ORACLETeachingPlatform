package com.plat.orcl.web.ui;

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

public class CourseUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*int[] num = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
				21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,
				41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
		request.setCharacterEncoding("UTF-8");*/
		Person p = (Person) request.getSession().getAttribute("person");
		CourseServiceImpl csi = new CourseServiceImpl();
		ArrayList<Course> lc = csi.getCourseList(p.getPstate(), p.getPid());
		ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
		request.setAttribute("clist", lc);
		request.setAttribute("chapterlist", chapterlist);
		/*request.setAttribute("num", num);*/
		WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/coursetea.jsp", "/WEB-INF/jsp/coursestu.jsp", request, response);
		return;
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
