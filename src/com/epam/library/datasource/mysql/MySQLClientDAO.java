package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.IClientDAO;
import com.epam.library.entity.Client;
import com.epam.library.utils.ClientBuilder;

public class MySQLClientDAO extends AbstractDAO<Client> implements IClientDAO {

	private Connection connection;

	public MySQLClientDAO(Connection connection) {
		super(connection);
		this.connection = connection;
		
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, SUBSCRIPTIONID, NAME, SURNAME, BIRTHDAY, USERID FROM CLIENTS ";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO CLIENTS (SUBSCRIPTIONID, NAME, SURNAME, BIRTHDAY, USERID) \n"
				+ "VALUES (?,?,?,?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE CLIENTS \n"
				+ "SET SUBSCRIPTIONID = ?, NAME = ?, SURNAME = ?, BIRTHDAY = ?, USERID = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM CLIENTS WHERE ID = ?;";
	}

	@Override
	protected List<Client> parseResultSet(ResultSet resultSet) {
		List<Client> result = new ArrayList<>();
		try {
			while (resultSet.next()) {

				result.add(new ClientBuilder()
						.withId(resultSet.getInt("ID"))
						.withSubscriptionid(
								resultSet.getInt("SUBSCRIPTIONID") == 0 ? null
										: resultSet.getInt("SUBSCRIPTIONID"))
						.withName(resultSet.getString("NAME"))
						.withSurname(resultSet.getString("SURNAME"))
						.withBirthday(resultSet.getDate("BIRTHDAY"))
						.withUserId(
								resultSet.getInt("USERID") == 0 ? null
										: resultSet.getInt("USERID")).build());

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, Client object) {
		try {
			if (object.getSubscriptionid() != null) {
				preparedStatement.setInt(1, object.getSubscriptionid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getName() != null) {
				preparedStatement.setString(2, object.getName());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getSurname() != null) {
				preparedStatement.setString(3, object.getSurname());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}
			if (object.getBirthday() != null) {
				preparedStatement.setDate(4, object.getBirthday());
			} else {
				preparedStatement.setNull(4, java.sql.Types.NULL);
			}
			if (object.getUserid() != null) {
				preparedStatement.setInt(5, object.getUserid());
			} else {
				preparedStatement.setNull(5, java.sql.Types.NULL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, Client object) {
		try {
			if (object.getSubscriptionid() != null) {
				preparedStatement.setInt(1, object.getSubscriptionid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getName() != null) {
				preparedStatement.setString(2, object.getName());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getSurname() != null) {
				preparedStatement.setString(3, object.getSurname());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}
			if (object.getBirthday() != null) {
				preparedStatement.setDate(4, object.getBirthday());
			} else {
				preparedStatement.setNull(4, java.sql.Types.NULL);
			}
			if (object.getUserid() != null) {
				preparedStatement.setInt(5, object.getUserid());
			} else {
				preparedStatement.setNull(5, java.sql.Types.NULL);
			}

			preparedStatement.setInt(6, object.getId());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public Client getByUserId(Integer id) {
		List<Client> list = null;
		String sql = getSelectQuery();
		sql += " WHERE USERID = ?;";
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
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
