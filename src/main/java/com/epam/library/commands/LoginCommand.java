package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Client;
import com.epam.library.entity.Order;
import com.epam.library.entity.User;
import com.epam.library.logic.Logic;

public class LoginCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin(request.getParameter("user"));
		user.setPassword(request.getParameter("password"));

		if (Logic.login(user)) {
			user = Logic.getUserByLogin(user);
			session.setAttribute("user", user);
			//Controller.user = user;
			Client client = Logic.getClientByUser(user);
			if (client != null) {
				session.setAttribute("client", client);
				//Controller.client = client;
				//request.setAttribute("activeclient", client);
				Order order = Logic.getOrderOpen(client.getId());
				session.setAttribute("order", order);
				//Controller.order = order;
				//request.setAttribute("activeorder", order);

				return new GetAllBooksCommand().execute(request, response);
			}
			return new GetAllBooksCommandRestricted().execute(request, response);
		} else
			return new NoCommand().execute(request, response);
	}

}
