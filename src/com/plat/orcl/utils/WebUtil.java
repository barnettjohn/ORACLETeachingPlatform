package com.plat.orcl.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.morph.wrap.Bean;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Score;
import com.plat.orcl.web.formbean.AllScoreForm;
import com.plat.orcl.web.formbean.RegisterForm;

public class WebUtil {
	public static <T> T copyRequest2Bean(HttpServletRequest request, Class<T> beanClass ){
		
		try {
			T bean = beanClass.newInstance();
			
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	public static <T> ArrayList<T> copyRequest2BeanList(HttpServletRequest request, Class<T> beanClass,int step,int length ){
		
		try {
			ArrayList<T> l = new ArrayList<T>();
			int i = 0;
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()){
				String s = (String) e.nextElement();
				i++;
			}
			e = request.getParameterNames();
			
			int num = i/step; 
			T bean = beanClass.newInstance();
			for(int n=0 ; n<num;n++){
				bean = beanClass.newInstance();
				while(e.hasMoreElements()){
					String name = (String) e.nextElement();
					String value = request.getParameter(name);
					String[] names = name.split("_");
					name = names[0];
					String id = names[1];
					int j = Integer.parseInt(id.substring(length,id.length()));
					if(j==n){
						BeanUtils.setProperty(bean, name, value);
					}
				}
				String s = bean.toString();
				if(!PubUtil.isEmptring(bean.toString())) l.add(bean);
				e = request.getParameterNames();
			}
			
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		
	}

	public static void copyBean(Object orig, Object dest) {
		
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class type, Object value) {
				
				if(value==null) return null;
				
				String str = (String) value;
				
				if(str.trim().equals("")) return null;
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					return df.parse(str);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static <E, T> void copyBeanList(ArrayList<E> ls, ArrayList<T> asl,Class<T> beanClass) {
		for(E e:ls){
			try {
				T t = beanClass.newInstance();
				WebUtil.copyBean(e, t);
				asl.add(t);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public static void person2Right(String pState,String message,String urltea,String urlstu,String urladmin ,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if (pState.equals("s")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urlstu).forward(request, response);
			return;
		}
		else if(pState.equals("t")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}else if(pState.equals("a")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urladmin).forward(request, response);
			return;
		}
	}
	
	public static void person2Right(String pState,String message,String urltea,String urlstu,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		if (pState.equals("s")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urlstu).forward(request, response);
			return;
		}
		else if(pState.equals("t")){
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}else {
			request.setAttribute("message", message);
			request.getRequestDispatcher(urltea).forward(request, response);
			return;
		}
	}

}
