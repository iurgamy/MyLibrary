package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class MySQLDataSource {
	private static MySQLDataSource mySqlDataSource;
	private DataSource dataSource;

	public static Logger logger = Logger.getLogger(MySQLDataSource.class
			.getName());

	private MySQLDataSource() {
	}

	public static MySQLDataSource getInstance() {
		if (mySqlDataSource == null) {
			mySqlDataSource = new MySQLDataSource();
		}
		return mySqlDataSource;

	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			if (dataSource == null) {

				Context initContext = new InitialContext();
				Context envContext = (Context) initContext
						.lookup("java:/comp/env");
				dataSource = (DataSource) envContext.lookup("jdbc/mysql");
				connection = dataSource.getConnection();
			} else
				connection = dataSource.getConnection();

		} catch (NamingException | SQLException e) {
			logger.error(e.getMessage(), e);

		}

		return connection;

	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

}
