package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Client;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderItem;
import com.epam.library.logic.Logic;

public class AddToOrderCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			if (Logic.getBookById(Integer.parseInt(request.getParameter("id")))
					.getCount() != 0) {
				if (session.getAttribute("order") == null) {
					Order order = new Order();
					Client client = (Client) session.getAttribute("client");
					order.setClientid(client.getId());
					order = Logic.addOrder(order);
					session.setAttribute("order", order);
					request.setAttribute("order", order);
					OrderItem orderItem = new OrderItem();
					orderItem.setBookid(Integer.parseInt(request
							.getParameter("id")));
					orderItem.setOrderid(order.getId());
					Logic.addOrderItem(orderItem);

				} else {
					OrderItem orderItem = new OrderItem();
					orderItem.setBookid(Integer.parseInt(request
							.getParameter("id")));
					Order order = (Order)session.getAttribute("order");
					orderItem.setOrderid(order.getId());
					Logic.addOrderItem(orderItem);

				}
			}
			return new GetAllBooksCommand().execute(request, response);
		} else {
			return new NoCommand().execute(request, response);
		}
	}
}
