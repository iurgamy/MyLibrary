package com.epam.library.commands;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeLocalCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
				
		//HttpSession session = request.getSession();
		String test = request.getParameter("local");
		request.setAttribute("lang", test);
		Cookie cookie = new Cookie("lang", test);
		response.addCookie(cookie);
		return "/index.jsp";
	}

}
