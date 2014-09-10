package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.ISubscriptionDAO;
import com.epam.library.entity.Subscription;

public class MySQLSubscriptionDAO extends AbstractDAO<Subscription> implements
		ISubscriptionDAO {

	public MySQLSubscriptionDAO(Connection connection) {
		super(connection);
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, CLIENTID FROM SUBSCRIPTIONS";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO SUBSCRIPTIONS (CLIENTID) \n" + "VALUES (?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE SUBSCRIPTIONS \n" + "SET CLIENTID = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM SUBSCRIPTIONS WHERE ID = ?;";
	}

	@Override
	protected List<Subscription> parseResultSet(ResultSet resultSet) {
		List<Subscription> result = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getInt("ID"));
				subscription
						.setClientid(resultSet.getInt("CLIENTID") == 0 ? null
								: resultSet.getInt("CLIENTID"));
				result.add(subscription);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, Subscription object) {
		try {
			if (object.getClientid() != null) {
				preparedStatement.setInt(1, object.getClientid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, Subscription object) {
		try {
			if (object.getClientid() != null) {
				preparedStatement.setInt(1, object.getClientid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}

			preparedStatement.setInt(2, object.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
