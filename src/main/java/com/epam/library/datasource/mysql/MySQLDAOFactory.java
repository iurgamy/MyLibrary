package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.util.logging.Logger;



import com.epam.library.dao.IBookDAO;
import com.epam.library.dao.IClientDAO;
import com.epam.library.dao.IDAOFactory;
import com.epam.library.dao.IOrderDAO;
import com.epam.library.dao.IOrderItemDAO;
import com.epam.library.dao.ISubscriptionDAO;
import com.epam.library.dao.ISubscriptionItemDAO;
import com.epam.library.dao.IUserDAO;

public class MySQLDAOFactory implements IDAOFactory {
	
	

	public static Logger logger = Logger.getLogger(MySQLDAOFactory.class
			.getName());

	// private String url = "jdbc:mysql://localhost/library";
	// private String user = "root";
	// private String password = "ghbdtn";
	// private String driver = "com.mysql.jdbc.Driver";



	@Override
	public IBookDAO getBookDAO(Connection connection) {
		return new MySQLBookDAO(connection);
	}

	@Override
	public IClientDAO getClientDAO(Connection connection) {
		return new MySQLClientDAO(connection);
	}

	@Override
	public IOrderDAO getOrderDAO(Connection connection) {
		return new MySQLOrderDAO(connection);
	}

	@Override
	public IUserDAO getUserDAO(Connection connection) {
		return new MySQLUsersDAO(connection);
	}

	@Override
	public ISubscriptionDAO getSubscriptionDAO(Connection connection) {
		return new MySQLSubscriptionDAO(connection);
	}

	@Override
	public ISubscriptionItemDAO getSubscriptionItemDAO(Connection connection) {
		return new MySQLSubscriptionItemDAO(connection);
	}

	@Override
	public IOrderItemDAO getOrderItemDAO(Connection connection) {
		return new MySQLOrderItemDAO(connection);
	}

}
