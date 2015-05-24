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
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.StuTestAndScore;

public class TestUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Person p = (Person) request.getSession().getAttribute("person");
		CourseServiceImpl csi = new CourseServiceImpl();
		TestServiceImpl tsi = new TestServiceImpl();
		
		
		String flag = request.getParameter("flag");
		if(flag.equals("1")){
			/*ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
			request.setAttribute("clist", lc);
			request.setAttribute("chapterlist", chapterlist);*/
			if(p.getPstate().equals("t")){
				//取章节列表
				ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
				request.setAttribute("chapterlist", chapterlist);
				//取题列表
				ArrayList<Question> qlist = tsi.getAllQuestion();
				request.setAttribute("questionlist", qlist);
			}else if(p.getPstate().equals("s")){
				//取章节列表
				ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
				request.setAttribute("chapterlist", chapterlist);
				//取题列表
				ArrayList<Question> qlist = tsi.getAllQuestion();
				request.setAttribute("questionlist", qlist);
			}
			WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/testtea.jsp", "/WEB-INF/jsp/teststu.jsp", request, response);
			return;
		}else if(flag.equals("2")){
			ArrayList<Question> qlist = tsi.getAllQuestion();
			request.setAttribute("questionlist", qlist);
			request.getRequestDispatcher("/WEB-INF/jsp/testtea2.jsp").forward(request, response);
			return;
		}else if(flag.equals("3")){
			
			if(p.getPstate().equals("t")){
				ArrayList<Test> tlist = tsi.getAllTest();
				request.setAttribute("testlist", tlist);
				
			}else if(p.getPstate().equals("s")){
				ArrayList<StuTestAndScore> tlist = tsi.getStudentTestScores(p);
				request.setAttribute("testlist", tlist);
				
				request.setAttribute("stutestandscorelist", tlist);
				
			}
			WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/testtea3.jsp", "/WEB-INF/jsp/teststu3.jsp", request, response);
			return;
		}
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
