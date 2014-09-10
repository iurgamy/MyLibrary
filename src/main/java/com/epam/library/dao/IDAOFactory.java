package com.epam.library.dao;

import java.sql.Connection;

public interface IDAOFactory {

	//public Connection getConnection();
	
	//public void closeConnection();

	public IBookDAO getBookDAO(Connection connection);

	public IClientDAO getClientDAO(Connection connection);
	
	public IUserDAO getUserDAO(Connection connection);

	public IOrderDAO getOrderDAO(Connection connection);
	
	public IOrderItemDAO getOrderItemDAO(Connection connection);
	
	public ISubscriptionDAO getSubscriptionDAO(Connection connection);
	
	public ISubscriptionItemDAO getSubscriptionItemDAO(Connection connection);

}
