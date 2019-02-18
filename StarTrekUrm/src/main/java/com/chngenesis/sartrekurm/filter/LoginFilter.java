package com.chngenesis.sartrekurm.filter;

import com.chngenesis.sartrekurm.common.entity.Account;
import com.chngenesis.sartrekurm.utils.Constants;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {

    @Override
	public void destroy() {
	}

    @Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");

		// 获取储存在SESSION中的账号
		Account account = (Account) ((HttpServletRequest) servletRequest).getSession().getAttribute(Constants.ACCOUNT_IN_SESSION);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String servletpath = request.getServletPath();
		String requestPath = servletpath.substring(1);
		String suffix = "";
		if (servletpath.contains(".")) {
			requestPath = servletpath.substring(1, servletpath.lastIndexOf("."));
			suffix = servletpath.substring(servletpath.lastIndexOf("."));
		}
		List<String> pathList = new ArrayList<>();
		pathList.add(".js");
		pathList.add(".css");
		pathList.add(".jpg");
		pathList.add(".png");
		pathList.add(".vue");
		if ("account/checkLogin".equals(requestPath) || "account/register".equals(requestPath) || "index".equals(requestPath) || pathList.contains(suffix)) {
		    // 登录放行
			filterChain.doFilter(servletRequest, servletResponse);
		}else if(account!=null && StringUtils.isNotBlank(account.getId())){
			filterChain.doFilter(servletRequest, servletResponse);
		}else {
			//把sessionID放入响应
//			Cookie cookie = new Cookie("JSESSIONID", sessionId);
//			cookie.setPath("/");
//			response.addCookie(cookie);
			
			PrintWriter out = response.getWriter();
			out.write("{\"error\":true,\"message\":\"未登录\"}");
			out.flush();
			out.close();
		}
	}

    @Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
