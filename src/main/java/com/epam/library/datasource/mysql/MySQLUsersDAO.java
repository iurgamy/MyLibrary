package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.IUserDAO;
import com.epam.library.entity.User;

public class MySQLUsersDAO extends AbstractDAO<User> implements IUserDAO {

	private Connection connection;

	public MySQLUsersDAO(Connection connection) {
		super(connection);
		this.connection = connection;
		// TODO Auto-generated constructor stub
	}

	protected String getCheckUserQuery() {
		return "SELECT ID FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
	}

	@Override
	protected String getSelectQuery() {
		// TODO Auto-generated method stub
		return "SELECT ID, LOGIN, PASSWORD FROM USERS ";
	}

	@Override
	protected String getCreateQuery() {
		// TODO Auto-generated method stub
		return "INSERT INTO USERS (LOGIN, PASSWORD) \n" + "VALUES (?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		// TODO Auto-generated method stub
		return "UPDATE USERS \n" + "SET LOGIN = ?, PASSWORD = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		// TODO Auto-generated method stub
		return "DELETE FROM USERS WHERE ID = ?;";
	}

	@Override
	protected List<User> parseResultSet(ResultSet resultSet) {
		// TODO Auto-generated method stub
		List<User> result = new ArrayList<>();
		try {
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setLogin(resultSet.getString("LOGIN"));
				user.setPassword(resultSet.getString("PASSWORD"));
				result.add(user);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;

	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, User object) {
		// TODO Auto-generated method stub
		try {

			preparedStatement.setString(1, object.getLogin());
			preparedStatement.setString(2, object.getPassword());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, User object) {
		// TODO Auto-generated method stub
		try {

			preparedStatement.setString(1, object.getLogin());
			preparedStatement.setString(2, object.getPassword());
			preparedStatement.setInt(3, object.getId());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}

	public boolean checkUser(User user) {
		String sql = getCheckUserQuery();
		ResultSet resultSet = null;
		boolean flag = false;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				if (resultSet.getInt("ID") == 0) {
					flag = false;
				} else
					flag = true;
			} else {
				logger.error("User not found");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return flag;

	}

	@Override
	public User getByLogin(String login) {
		List<User> list = null;
		String sql = getSelectQuery();
		sql += " WHERE LOGIN = ?;";
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			list = parseResultSet(resultSet);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}

		if (list == null || list.size() == 0) {
			return null;
		}

		if (list.size() > 1) {
			logger.error("Received more than one record from db");

		}
		return list.iterator().next();
	}

}
