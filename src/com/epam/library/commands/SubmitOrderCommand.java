package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Order;
import com.epam.library.logic.Logic;

public class SubmitOrderCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			Order order = (Order) session.getAttribute("order");
			Logic.submitOrder(order);
			session.setAttribute("order", null); 
			return new GetAllBooksCommand().execute(request, response);
		}
		return new NoCommand().execute(request, response);
	}

}
