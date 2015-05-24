package com.plat.orcl.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.plat.orcl.domain.Person;

public class DestroyTimerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
System.out.println("定时器监听器被创建");
	ServletContext sc = sce.getServletContext();
	Map<String, Timer>timerMap = new HashMap<String, Timer>();
	Map<String, ArrayList<Person>>personMap = new HashMap<String, ArrayList<Person>>();
	//timerMap.put("mailTimer", new Timer());
	sc.setAttribute("timerMap", timerMap);
	sc.setAttribute("personMap", personMap);
System.out.println(""+timerMap.get("mailTimer"));

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
System.out.println("定时器监听器被销毁");
		ServletContext sc = sce.getServletContext();
		Map<String, Timer>timerMap = (Map<String, Timer>) sc.getAttribute("timerMap");
		if(timerMap!=null&&timerMap.entrySet().size()!=0){
			for(Entry<String, Timer> s : timerMap.entrySet()){
				System.out.println(s.getKey()+":"+s.getValue()+":"+"定时器被停止");
				s.getValue().cancel();
			}
		}
		
	}

}
