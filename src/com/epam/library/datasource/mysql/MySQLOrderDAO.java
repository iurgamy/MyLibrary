package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.IOrderDAO;
import com.epam.library.entity.Order;
import com.epam.library.staticdata.EOrderStatus;

public class MySQLOrderDAO extends AbstractDAO<Order> implements IOrderDAO {
	private Connection connection;

	public MySQLOrderDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, CLIENTID, STATUS FROM ORDERS ";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO ORDERS (CLIENTID, STATUS) \n" + "VALUES (?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE ORDERS \n" + "SET CLIENTID = ?, STATUS = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM ORDERS WHERE ID = ?;";
	}

	@Override
	protected List<Order> parseResultSet(ResultSet resultSet) {
		List<Order> result = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("ID"));
				order.setClientid(resultSet.getInt("CLIENTID") == 0 ? null
						: resultSet.getInt("CLIENTID"));
				order.setStatus(EOrderStatus.valueOf(resultSet
						.getString("STATUS")));
				result.add(order);

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, Order object) {
		try {
			if (object.getClientid() != null) {
				preparedStatement.setInt(1, object.getClientid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getStatus() != null) {
				preparedStatement.setString(2, object.getStatus().name());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, Order object) {
		try {
			if (object.getClientid() != null) {
				preparedStatement.setInt(1, object.getClientid());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getStatus() != null) {
				preparedStatement.setString(2, object.getStatus().name());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			preparedStatement.setInt(3, object.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Order getOpenOrder(Integer clientid) {
		List<Order> list = null;
		String sql = getSelectQuery();
		sql += " WHERE CLIENTID = ? AND STATUS = ?;";
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setInt(1, clientid);
			preparedStatement.setString(2, EOrderStatus.NEW.name());
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

	public List<Order> getAllSubbmittedOrders() {
		ResultSet resultSet = null;
		List<Order> list = null;
		String sql = getSelectQuery();
		sql+= " WHERE STATUS = ?;";
		
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setString(1, EOrderStatus.SUBMITTED.name());
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
