package com.chngenesis.sartrekurm.filter;

import com.chngenesis.sartrekurm.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "http://7.0.1.18:8080");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		
		String sessionId = SessionUtil.getSessionId(request);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		filterChain.doFilter(servletRequest, servletResponse);
		Cookie cookie = new Cookie("JSESSIONID", sessionId);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
