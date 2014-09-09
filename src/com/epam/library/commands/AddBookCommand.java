package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.entity.Book;
import com.epam.library.logic.Logic;
import com.epam.library.utils.BookBuilder;
import com.epam.library.utils.DateConvert;

public class AddBookCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			String temp = request.getParameter("price");
			Book book = new BookBuilder()
					.withTitle(request.getParameter("title"))
					.withAuthor(request.getParameter("author"))
					.withYear(
							DateConvert.convertToYear(request
									.getParameter("year")))
					.withPrice(Double.parseDouble(temp.isEmpty() ? "0" : temp))
					.build();
			Logic.addBook(book);
			return new GetAllBooksCommand().execute(request, response);
		} else {
			return new NoCommand().execute(request, response);
		}
	}
}