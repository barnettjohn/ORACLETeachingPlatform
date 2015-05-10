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
import com.plat.orcl.utils.PubUtil;
import com.plat.orcl.utils.WebUtil;

public class UpdateChapterServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CourseServiceImpl csi = new CourseServiceImpl();
		Person p = (Person) request.getSession().getAttribute("person");
		request.setCharacterEncoding("UTF-8");
		ArrayList<Chapter> chapterlist = WebUtil.copyRequest2BeanList(request, Chapter.class, 4,PubUtil.COURSE_ID_LENGTH);
		//先对学生进行分组
		int i = csi.dividedIntoTeams(chapterlist);
		if(i>0){
			request.setAttribute("message", "人数分配不均!请重新分配少了"+i+"人");
		}else if(i<0){
			request.setAttribute("message", "人数分配不均!请重新分配超了"+i+"人");
		}else{
			//更新章节信息
			csi.addOrUpdateChapter(chapterlist);
		}
		
		ArrayList<Course> lc = csi.getCourseList(p.getPstate(), p.getPid());
		chapterlist = csi.getChapterList(p.getPstate());
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
