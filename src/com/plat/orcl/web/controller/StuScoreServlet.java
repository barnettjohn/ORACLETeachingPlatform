package com.plat.orcl.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Question;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.service.impl.TestServiceImpl;
import com.plat.orcl.utils.PubUtil;

public class StuScoreServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TestServiceImpl tsi = new TestServiceImpl();
		String testid =  request.getParameter("testid");
		Person p = (Person) request.getSession().getAttribute("person");
		Enumeration<String> e =request.getParameterNames();
		HashMap<String, String> qMap = new HashMap<String, String>();
		int right = 0;
		int flase = 0;
		double sum=0;
		String flaseStr = "" ;
		while(e.hasMoreElements()){
			String pn = e.nextElement();
			if(pn.startsWith("q_")){
				String qid = pn.substring(2);
				String qreal = request.getParameter(pn);
				if(qreal!=null){
					qreal=qreal.trim().toLowerCase();
					Question q = tsi.getOneQuestion(qid);
					if(q!=null){
						//错误为0正确为1
						if (q.getQright().equals(qreal)) {
							qMap.put(qid, "1");
							right++;
						}else {
							qMap.put(qid, "0");
							flase++;
							flaseStr+=qid+"_"+qreal+PubUtil.SEPARATOR;
						}
					}else{
						throw new RuntimeException("问题未找到");
					}
				}else qMap.put(qid, "1");
				
			}
		}
		if((flase+right) ==qMap.size() ){
			sum = ((double)right/qMap.size())*100;
		}
		TestScore ts = new TestScore();
		ts.setPid(p.getPid());
		ts.setScore(sum);
		ts.setTestid(testid);
		ts.setWrongq(flaseStr);
		flaseStr.split(PubUtil.SEPARATOR);
		tsi.addTestScore(ts);
		response.sendRedirect(request.getContextPath()+"/servlet/TestDetailServlet2?testid="+testid);
		/*request.setAttribute("testscore", ts);
		request.setAttribute("test", tsi.getOneTest(testid));
		request.getRequestDispatcher("/WEB-INF/jsp/teststu3_seetest.jsp").forward(request, response);
		*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
