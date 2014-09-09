package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.logic.Logic;
import com.epam.library.staticdata.ESubItemType;

public class OrderToReadCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {

			Logic.orderToSubscription(Logic.getOrderById(Integer
					.parseInt(request.getParameter("orderid"))),
					ESubItemType.READING_ROOM.name());
			return new GetSubmitedOrdersCommand().execute(request, response);
		}

		return new NoCommand().execute(request, response);
	}

}
