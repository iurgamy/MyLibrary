package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.IOrderItemDAO;
import com.epam.library.entity.OrderItem;

public class MySQLOrderItemDAO extends AbstractDAO<OrderItem> implements
		IOrderItemDAO {

	private Connection connection;

	public MySQLOrderItemDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, ORDERID, BOOKID FROM ORDER_ITEMS ";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO ORDER_ITEMS (ORDERID, BOOKID) \n" + "VALUES (?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE ORDER_ITEMS \n" + "SET ORDERID = ?, BOOKID = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM ORDER_ITEMS WHERE ID = ?;";
	}

	@Override
	protected List<OrderItem> parseResultSet(ResultSet resultSet) {
		List<OrderItem> result = new ArrayList<>();
		try {
			while (resultSet.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(resultSet.getInt("ID"));
				orderItem.setOrderid(resultSet.getInt("ORDERID") == 0 ? null
						: resultSet.getInt("ORDERID"));
				orderItem.setBookid(resultSet.getInt("BOOKID") == 0 ? null
						: resultSet.getInt("BOOKID"));
				result.add(orderItem);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, OrderItem object) {
		try {
			if (object.getOrderid() != null) {
				preparedStatement.setInt(1, object.getOrderid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getBookid() != null) {
				preparedStatement.setInt(2, object.getBookid());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, OrderItem object) {
		try {
			if (object.getOrderid() != null) {
				preparedStatement.setInt(1, object.getOrderid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getBookid() != null) {
				preparedStatement.setInt(2, object.getBookid());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			preparedStatement.setInt(3, object.getId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public List<OrderItem> getAllByOrderId(Integer id) {
		ResultSet resultSet = null;
		List<OrderItem> list = null;
		String sql = getSelectQuery();
		sql+="WHERE ORDERID = ?";
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
