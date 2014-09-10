package com.epam.library.filters;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Order;
import com.epam.library.logic.Logic;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		HttpSession session = httpServletRequest.getSession();
		if ("/index.jsp".equals(httpServletRequest.getServletPath())) {

			if (session.getAttribute("user") != null) {
				if (session.getAttribute("client") != null) {
					request.setAttribute("client",
							session.getAttribute("client"));
					request.setAttribute("order", session.getAttribute("order"));
					if (session.getAttribute("searchflag") != "true") {
						request.setAttribute("allbooks", Logic.getAllBooks());
					} else {
						session.setAttribute("searchflag", "false");
					}
					if (session.getAttribute("order") != null) {
						Order order = (Order) session.getAttribute("order");
						if (order.getStatus().name() == "NEW") {
							request.setAttribute("cart",
									Logic.getAllOrderItems(order.getId()));
						}
					}
					RequestDispatcher dispatcher = httpServletRequest
							.getRequestDispatcher("/allbooks.jsp");
					dispatcher.forward(request, response);
					filterChain.doFilter(httpServletRequest,
							httpServletResponse);
				} else {
					filterChain.doFilter(httpServletRequest,
							httpServletResponse);
				}
			} else
				filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else

			filterChain.doFilter(httpServletRequest, httpServletResponse);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
