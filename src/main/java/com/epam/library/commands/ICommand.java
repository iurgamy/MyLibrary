package com.epam.library.commands;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	public static Logger logger = Logger.getLogger(ICommand.class.getName());

	public String execute(HttpServletRequest request,
			HttpServletResponse response);

}
