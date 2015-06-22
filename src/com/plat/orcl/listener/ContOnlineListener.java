package com.plat.orcl.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ContOnlineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		Object o = sc.getAttribute("online");
		if(o==null){
			sc.setAttribute("online", 1);
		}else{
			int onlinenum = (Integer) o;
			onlinenum++;
			sc.setAttribute("online", onlinenum);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext sc = se.getSession().getServletContext();
		Object o = sc.getAttribute("online");
		if(o==null){
			sc.setAttribute("online", 1);
		}else{
			int onlinenum = (Integer) o;
			onlinenum--;
			sc.setAttribute("online", onlinenum);
		}
	}

}
