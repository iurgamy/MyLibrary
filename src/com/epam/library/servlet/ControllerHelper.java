package com.epam.library.servlet;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.epam.library.commands.AddBookCommand;
import com.epam.library.commands.AddToOrderCommand;
import com.epam.library.commands.ChangeLocalCommand;
import com.epam.library.commands.CopyCommand;
import com.epam.library.commands.GetAllBooksCommand;
import com.epam.library.commands.CreateUserCommand;
import com.epam.library.commands.DeleteBookCommand;
import com.epam.library.commands.GetMySubscriptionItemsCommand;
import com.epam.library.commands.GetSubmitedOrdersCommand;
import com.epam.library.commands.ICommand;
import com.epam.library.commands.LoginCommand;
import com.epam.library.commands.LogoutCommand;
import com.epam.library.commands.NewUserInputCommand;
import com.epam.library.commands.NoCommand;
import com.epam.library.commands.OrderToReadCommand;
import com.epam.library.commands.OrderToSubCommand;
import com.epam.library.commands.SearchCommand;
import com.epam.library.commands.SubmitOrderCommand;
import com.epam.library.commands.TurnBackSubItemCommand;

public class ControllerHelper {
	private static ControllerHelper instance = null;
	HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	private ControllerHelper() {
		// ���������� ������� ���������
		commands.put("login", new LoginCommand());
		commands.put("getallbooks", new GetAllBooksCommand());
		commands.put("newuser", new NewUserInputCommand());
		commands.put("adduser", new CreateUserCommand());
		commands.put("addbook", new AddBookCommand());
		commands.put("deletebook", new DeleteBookCommand());
		commands.put("copydetails", new CopyCommand());
		commands.put("addtocart", new AddToOrderCommand());
		commands.put("submit", new SubmitOrderCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("getorders", new GetSubmitedOrdersCommand());
		commands.put("tosub", new OrderToSubCommand());
		commands.put("toread", new OrderToReadCommand());
		commands.put("search", new SearchCommand());
		commands.put("local", new ChangeLocalCommand());
		commands.put("getmysubs", new GetMySubscriptionItemsCommand());
		commands.put("return", new TurnBackSubItemCommand());
		
		
		
	}

	public ICommand getCommand(HttpServletRequest request) {
		// ���������� ������� �� �������
		String action = request.getParameter("command");
		// ��������� �������, ���������������� �������
		ICommand command = commands.get(action);
		if (command == null) {
			 //���� ������� �� ���������� � ������� �������
			 command = new NoCommand();
		}
		return command;
	}

	// �������� ������������� ������� �� ������� Singleton
	public static ControllerHelper getInstance() {
		if (instance == null) {
			instance = new ControllerHelper();
		}
		return instance;
	}

}
