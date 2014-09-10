package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.logic.Logic;

public class DeleteBookCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			Logic.deleteBook(Integer.parseInt(request.getParameter("id")));
			return new GetAllBooksCommand().execute(request, response);
		} else {
			return new NoCommand().execute(request, response);
		}
	}
}
