package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Order;
import com.epam.library.logic.Logic;

public class GetAllBooksCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
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
					// request.setAttribute("orderlist",
					// Logic.getALLSubbmitedOrders());
					return "/allbooks.jsp";
				} else {
					return new GetAllBooksCommandRestricted().execute(request,
							response);
				}
			} else
				return new NoCommand().execute(request, response);
		
	}
}