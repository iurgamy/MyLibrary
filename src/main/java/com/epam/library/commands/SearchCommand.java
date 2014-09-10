package com.epam.library.commands;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.logic.Logic;
import com.epam.library.utils.DateConvert;

public class SearchCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {

			Integer id = request.getParameter("id").isEmpty() ? null : Integer
					.parseInt(request.getParameter("id"));
			String title = request.getParameter("title").isEmpty() ? null
					: request.getParameter("title");
			String author = request.getParameter("author").isEmpty() ? null
					: request.getParameter("author");
			String year = request.getParameter("year").isEmpty() ? null
					: request.getParameter("year");
			Double price = request.getParameter("price").isEmpty() ? null
					: Double.parseDouble(request.getParameter("price"));
			Integer count = request.getParameter("count").isEmpty() ? null
					: Integer.parseInt(request.getParameter("count"));
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM BOOKS ");
			sql.append((id == null) ? ("WHERE ") : ("WHERE ID = " + id + " AND "));
			sql.append((title == null) ? ("") : (" TITLE = " + "'"+title+"'" + " AND "));
			sql.append((author == null) ? ("") : (" author = " + "'"+author+"'" + " AND "));
			sql.append((year == null) ? ("") : (" year = ? AND "));
			sql.append((price == null) ? ("") : (" price = " + price + " AND "));
			sql.append((count == null) ? (" 1=1;") : (" count = " + count + ";"));
			Date date = year == null? null : DateConvert.convertToYear(year);
			session.setAttribute("allbooks", Logic.searchBooks(sql.toString(), date));
			session.setAttribute("searchflag", "true");

			return new GetAllBooksCommand().execute(request, response);
		} else
			return new NoCommand().execute(request, response);
	}
}
