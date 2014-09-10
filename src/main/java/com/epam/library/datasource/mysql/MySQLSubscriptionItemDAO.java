package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.ISubscriptionItemDAO;
import com.epam.library.entity.SubscriptionItem;
import com.epam.library.staticdata.ESubItemType;

public class MySQLSubscriptionItemDAO extends AbstractDAO<SubscriptionItem>
		implements ISubscriptionItemDAO {

	private Connection connection;

	public MySQLSubscriptionItemDAO(Connection connection) {
		super(connection);
		this.connection= connection;
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, SUBSCRIPTIONID, BOOKID, TYPE FROM SUBSCRIPTION_ITEMS";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO SUBSCRIPTION_ITEMS (SUBSCRIPTIONID, BOOKID, TYPE) \n"
				+ "VALUES (?,?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE SUBSCRIPTION_ITEMS \n"
				+ "SET SUBSCRIPTIONID = ?, BOOKID = ?, TYPE = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM SUBSCRIPTION_ITEMS WHERE ID = ?;";
	}

	@Override
	protected List<SubscriptionItem> parseResultSet(ResultSet resultSet) {
		List<SubscriptionItem> result = new ArrayList<>();
		try {
			while (resultSet.next()) {
				SubscriptionItem subscriptionItem = new SubscriptionItem();
				subscriptionItem.setId(resultSet.getInt("ID"));
				subscriptionItem.setSubscriptionid(resultSet
						.getInt("SUBSCRIPTIONID") == 0 ? null : resultSet
						.getInt("SUBSCRIPTIONID"));
				subscriptionItem
						.setBookid(resultSet.getInt("BOOKID") == 0 ? null
								: resultSet.getInt("BOOKID"));
				subscriptionItem.setType(ESubItemType.valueOf(resultSet
						.getString("TYPE")));
				result.add(subscriptionItem);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, SubscriptionItem object) {
		try {
			if (object.getSubscriptionid() != null) {
				preparedStatement.setInt(1, object.getSubscriptionid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getBookid() != null) {
				preparedStatement.setInt(2, object.getBookid());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getType() != null) {
				preparedStatement.setString(3, object.getType().name());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, SubscriptionItem object) {
		try {
			if (object.getSubscriptionid() != null) {
				preparedStatement.setInt(1, object.getSubscriptionid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getBookid() != null) {
				preparedStatement.setInt(2, object.getBookid());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getType() != null) {
				preparedStatement.setString(3, object.getType().name());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}

			preparedStatement.setInt(4, object.getId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<SubscriptionItem> getAllBySubId(Integer id) {
		ResultSet resultSet = null;
		List<SubscriptionItem> list = null;
		String sql = getSelectQuery();
		sql+=" WHERE SUBSCRIPTIONID = ?";
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
		return list;
	}

}
