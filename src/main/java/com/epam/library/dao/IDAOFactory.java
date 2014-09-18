package com.epam.library.dao;

import java.sql.Connection;

public interface IDAOFactory {

	public IBookDAO getBookDAO(Connection connection);

	public IClientDAO getClientDAO();
	
	public IUserDAO getUserDAO();

	public IOrderDAO getOrderDAO(Connection connection);
	
	public IOrderItemDAO getOrderItemDAO(Connection connection);
	
	public ISubscriptionDAO getSubscriptionDAO(Connection connection);
	
	public ISubscriptionItemDAO getSubscriptionItemDAO(Connection connection);

}
