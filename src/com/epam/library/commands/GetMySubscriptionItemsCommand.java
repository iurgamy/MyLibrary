package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Client;
import com.epam.library.logic.Logic;

public class GetMySubscriptionItemsCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			session.setAttribute("SubItems", Logic
					.getAllSubItems((Client) session.getAttribute("client")));
			return "/mysubslist.jsp";
		} else {
			return new NoCommand().execute(request, response);
		}

	}

}
