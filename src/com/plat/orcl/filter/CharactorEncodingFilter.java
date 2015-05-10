package com.plat.orcl.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plat.orcl.utils.PubUtil;

public class CharactorEncodingFilter implements Filter {
	
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		*/
		System.out.println(encoding);
		if(!PubUtil.isEmptring(encoding)){
			request.setCharacterEncoding(encoding);
		}
		response.setContentType("text/html;charset="+encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
