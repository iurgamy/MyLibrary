package com.epam.library.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.library.entity.Client;
import com.epam.library.entity.User;
import com.epam.library.logic.Logic;
import com.epam.library.utils.ClientBuilder;
import com.epam.library.utils.DateConvert;

public class CreateUserCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern
				.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
		Matcher matcher = pattern.matcher(request.getParameter("birthday"));
		if (matcher.matches()) {
			User user = new User();
			user.setLogin(request.getParameter("user"));
			user.setPassword(request.getParameter("password"));
			user = Logic.createUser(user);
			if (user.getId() != null) {
				if (!request.getParameter("name").isEmpty()
						|| !request.getParameter("surname").isEmpty()) {
					Client client = new ClientBuilder()
							.withName(request.getParameter("name"))
							.withSurname(request.getParameter("surname"))
							.withBirthday(
									DateConvert.convertToDate(request
											.getParameter("birthday")))
							.withUserId(user).build();

					client = Logic.createClient(client);

					Logic.addSubscription(client);
				}
			}
			return new NoCommand().execute(request, response);
		}
		return "/adduser.jsp";

	}
}
