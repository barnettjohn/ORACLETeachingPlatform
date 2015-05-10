package com.plat.orcl.filter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpRetryException;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plat.orcl.domain.Person;
import com.plat.orcl.utils.PubUtil;

public class LoginFilter implements Filter {
	
	private String checkSessionKey ;
	private String redirectURL ;
	private List notCheckURLList ;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//读取web.xml文件中的初始化参数
		ServletContext s = filterConfig.getServletContext();
		checkSessionKey = s.getInitParameter("checkSessionKey");
		
		redirectURL = s.getInitParameter("redirectURL");
		
		String[] notCheckURLListStrings = s.getInitParameter("notCheckURLList").split(",");
		notCheckURLList = Arrays.asList(notCheckURLListStrings);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
System.out.println(checkSessionKey+redirectURL+notCheckURLList);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获取session中参数值
		HttpSession session = req.getSession();
		//获取访问路径
		String servletPath = req.getServletPath();
		
		if(this.notCheckURLList != null && this.notCheckURLList.contains(servletPath)){
			chain.doFilter(request, response);
		}else{
			Person p = (Person) session.getAttribute(checkSessionKey);
			if(p!=null){
				chain.doFilter(request, response);
			}else{
				resp.sendRedirect(req.getContextPath()+redirectURL);
			}
		}
	}

	@Override
	public void destroy() {

	}

}
