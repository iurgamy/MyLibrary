package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.logic.Logic;

public class GetAllBooksCommandRestricted implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			request.setAttribute("client", session.getAttribute("client"));
			request.setAttribute("order", session.getAttribute("order"));
			request.setAttribute("allbooks", Logic.getAllBooks());

			return "/allbooksrestricted.jsp";
		} else {
			return new NoCommand().execute(request, response);
		}
	}

}