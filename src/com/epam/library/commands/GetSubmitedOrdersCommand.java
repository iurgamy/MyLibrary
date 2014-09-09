package com.epam.library.commands;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.epam.library.logic.Logic;

public class GetSubmitedOrdersCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {

			session.setAttribute("orderlist", Logic.getALLSubbmitedOrders());
			return "/ordersToApprove.jsp";
		} else
			return new NoCommand().execute(request, response);

	}

}
