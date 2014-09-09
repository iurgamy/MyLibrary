package com.epam.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	public String execute(HttpServletRequest request, HttpServletResponse response);

}
