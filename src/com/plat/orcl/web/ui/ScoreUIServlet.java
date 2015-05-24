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
import com.plat.orcl.domain.Score;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.domain.Test;
import com.plat.orcl.service.impl.CourseServiceImpl;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.AllScoreForm;

public class ScoreUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Person p = (Person) request.getSession().getAttribute("person");
		ScoreServiceImpl ssi = new ScoreServiceImpl();
		String flag = request.getParameter("flag");
		if(flag.equals("1")){
			
			/*ArrayList<Chapter> chapterlist = csi.getChapterList(p.getPstate());
			request.setAttribute("chapterlist", chapterlist);
			
			//取题列表
			ArrayList<Question> qlist = tsi.getAllQuestion();
			request.setAttribute("questionlist", qlist);*/
			//从stuscore表中取记录
			if(p.getPstate().equals("s")){
				ArrayList<StuScore> sslist = ssi.getStuScoreList("pidup",p.getPid());
				request.setAttribute("sslist", sslist);
			}
			WebUtil.person2Right(p.getPstate(), "", "/WEB-INF/jsp/scoretea.jsp", "/WEB-INF/jsp/scorestu.jsp", request, response);
			return;
		}else if(flag.equals("2")){
			//取（教师）分数列表
			ArrayList<Score> sl  = ssi.getTeaScoreList();
			request.setAttribute("scorelist", sl);
			
			request.getRequestDispatcher("/WEB-INF/jsp/scoretea2.jsp").forward(request, response);
			return;
		}else if(flag.equals("3")){
			//取所有分数列表
			ArrayList<AllScoreForm> sl  = ssi.getAllScoreList();
			request.setAttribute("allscorelist", sl);
			
			request.getRequestDispatcher("/WEB-INF/jsp/scoretea3.jsp").forward(request, response);
			return;
		}
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
