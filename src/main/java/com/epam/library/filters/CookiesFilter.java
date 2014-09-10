package com.epam.library.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookiesFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Cookie[] cookies = httpServletRequest.getCookies();

		String lang = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("lang".equals(cookie.getName())) {
					lang = cookie.getValue();
				}
			}
		}
		httpServletRequest.setAttribute("lang", lang);
		filterChain.doFilter(httpServletRequest, httpServletResponse);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
