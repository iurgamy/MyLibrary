package com.epam.library.datasource.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.AbstractDAO;
import com.epam.library.dao.IBookDAO;
import com.epam.library.entity.Book;
import com.epam.library.utils.BookBuilder;

public class MySQLBookDAO extends AbstractDAO<Book> implements IBookDAO {
	private Connection connection;

	public MySQLBookDAO(Connection connection) {
		super(connection);
		this.connection = connection;
	}

	@Override
	protected String getSelectQuery() {
		return "SELECT ID, TITLE, AUTHOR, YEAR, PRICE, COUNT FROM BOOKS ";
	}

	@Override
	protected String getCreateQuery() {
		return "INSERT INTO BOOKS (TITLE, AUTHOR, YEAR, PRICE, COUNT) \n"
				+ "VALUES (?,?,?,?,?);";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE BOOKS \n"
				+ "SET TITLE = ?, AUTHOR = ?, YEAR = ?, PRICE = ?, COUNT = ? \n"
				+ "WHERE ID = ?;";
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM BOOKS WHERE ID = ?;";
	}

	@Override
	protected List<Book> parseResultSet(ResultSet resultSet) {
		List<Book> result = new ArrayList<>();
		try {
			while (resultSet.next()) {

				result.add(new BookBuilder().withId(resultSet.getInt("ID"))
						.withTitle(resultSet.getString("TITLE"))
						.withAuthor(resultSet.getString("AUTHOR"))
						.withYear(resultSet.getDate("YEAR"))
						.withPrice(resultSet.getDouble("PRICE"))
						.withCount(resultSet.getInt("COUNT")).build());

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForInsert(
			PreparedStatement preparedStatement, Book object) {
		try {
			if (object.getTitle() != null) {
				preparedStatement.setString(1, object.getTitle());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getAuthor() != null) {
				preparedStatement.setString(2, object.getAuthor());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getYear() != null) {
				preparedStatement.setDate(3, object.getYear());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}
			if (object.getPrice() != null) {
				preparedStatement.setDouble(4, object.getPrice());
			} else {
				preparedStatement.setNull(4, java.sql.Types.NULL);
			}
			if (object.getCount() != null) {
				preparedStatement.setInt(5, object.getCount());
			} else {
				preparedStatement.setNull(5, java.sql.Types.NULL);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void prepareStatementForUpdate(
			PreparedStatement preparedStatement, Book object) {
		try {
			if (object.getTitle() != null) {
				preparedStatement.setString(1, object.getTitle());
			} else {
				preparedStatement.setNull(1, java.sql.Types.NULL);
			}
			if (object.getAuthor() != null) {
				preparedStatement.setString(2, object.getAuthor());
			} else {
				preparedStatement.setNull(2, java.sql.Types.NULL);
			}
			if (object.getYear() != null) {
				preparedStatement.setDate(3, object.getYear());
			} else {
				preparedStatement.setNull(3, java.sql.Types.NULL);
			}
			if (object.getPrice() != null) {
				preparedStatement.setDouble(4, object.getPrice());
			} else {
				preparedStatement.setNull(4, java.sql.Types.NULL);
			}
			if (object.getCount() != null) {
				preparedStatement.setInt(5, object.getCount());
			} else {
				preparedStatement.setNull(5, java.sql.Types.NULL);
			}
			preparedStatement.setInt(6, object.getId());

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	public Book getByAllData(Book object) {
		List<Book> list = null;
		String sql = getSelectQuery();
		sql += " WHERE TITLE = ? AND AUTHOR = ? AND YEAR = ? AND PRICE = ?;";
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql)) {
			preparedStatement.setString(1, object.getTitle());
			preparedStatement.setString(2, object.getAuthor());
			preparedStatement.setDate(3, object.getYear());
			preparedStatement.setDouble(4, object.getPrice());
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

	public List<Book> search(String sql, Date year) {
		List<Book> list = null;
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(sql.toString())) {
			if (year != null) {
				preparedStatement.setDate(1, year);
			}
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
