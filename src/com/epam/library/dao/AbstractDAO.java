package com.epam.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

import com.epam.library.entity.IDefault;

public abstract class AbstractDAO<T extends IDefault> implements IGenericDAO<T> {
	public static Logger logger = Logger.getLogger(AbstractDAO.class.getName());
	// public static Logger logger = Logger.getLogger(AbstractDAO.class
	// .getName());

	private Connection connection;

	public AbstractDAO(Connection connection) {
		this.connection = connection;
	}

	protected abstract String getSelectQuery();

	protected abstract String getCreateQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getDeleteQuery();

	protected abstract List<T> parseResultSet(ResultSet resultSet);

	protected abstract void prepareStatementForInsert(
			PreparedStatement preparedStatement, T object);

	protected abstract void prepareStatementForUpdate(
			PreparedStatement preparedStatement, T object);

	@Override
	public T getById(Integer id) {
		List<T> list = null;
		String sql = getSelectQuery();
		sql += " WHERE ID = ?;";
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

	@Override
	public List<T> getAll() {
		ResultSet resultSet = null;
		List<T> list = null;
		String sql = getSelectQuery();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
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

	@Override
	public T create(T object) {
		ResultSet generatedKeys = null;
		String sql = getCreateQuery();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql, new String[] { "ID" })) {
			prepareStatementForInsert(preparedStatement, object);
			int count = preparedStatement.executeUpdate();
			if (count != 1) {
				logger.warn(("Was inserted more than one record: " + count));
			}

			generatedKeys = preparedStatement.getGeneratedKeys();
			if (null != generatedKeys && generatedKeys.next()) {
				object.setId(generatedKeys.getInt(1));

			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return object;
	}

	@Override
	public void update(T object) {
		String sql = getUpdateQuery();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			prepareStatementForUpdate(preparedStatement, object);
			int count = preparedStatement.executeUpdate();
			if (count != 1) {
				logger.warn("Was updated more than one record: " + count);
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage());
		}

	}

	@Override
	public boolean delete(T object) {
		String sql = getDeleteQuery();
		boolean flag = false;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			try {
				preparedStatement.setObject(1, object.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);

			}
			int count = preparedStatement.executeUpdate();
			if (count != 1) {
				logger.warn("Was deleted more than one record: " + count);
				flag = false;
			} else
				flag = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return flag;

	}

}
