package com.epam.library.commands;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public interface ICommand {
	public static Logger logger = Logger.getLogger(ICommand.class.getName());

	public String execute(HttpServletRequest request,
			HttpServletResponse response);

}
