package com.epam.library.logic;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.epam.library.dao.IBookDAO;
import com.epam.library.dao.IClientDAO;
import com.epam.library.dao.IDAOFactory;
import com.epam.library.dao.IOrderDAO;
import com.epam.library.dao.IOrderItemDAO;
import com.epam.library.dao.ISubscriptionDAO;
import com.epam.library.dao.ISubscriptionItemDAO;
import com.epam.library.dao.IUserDAO;
import com.epam.library.datasource.mysql.MySQLDAOFactory;
import com.epam.library.datasource.mysql.MySQLDataSource;
import com.epam.library.entity.Book;
import com.epam.library.entity.Client;
import com.epam.library.entity.Order;
import com.epam.library.entity.OrderItem;
import com.epam.library.entity.Subscription;
import com.epam.library.entity.SubscriptionItem;
import com.epam.library.entity.User;
import com.epam.library.staticdata.EOrderStatus;
import com.epam.library.staticdata.ESubItemType;

public class Logic {
	public static Logger logger = Logger.getLogger(Logic.class.getName());
	public static IDAOFactory factory = new MySQLDAOFactory();
	public static MySQLDataSource dataSource = MySQLDataSource.getInstance();

	public static boolean login(User user) {
		IUserDAO userDAO = factory.getUserDAO();
		boolean flag = userDAO.checkUser(user);
		return flag;
	}

	public static User createUser(User user) {
		IUserDAO userDAO = factory.getUserDAO();
		user = userDAO.create(user);
		return user;
	}

	public static Client createClient(Client client) {
		Connection connection = dataSource.getConnection();
		IClientDAO clientDAO = factory.getClientDAO(connection);
		client = clientDAO.create(client);
		dataSource.closeConnection(connection);
		return client;
	}

	public static void addBook(Book book) {
		Connection connection = dataSource.getConnection();
		IBookDAO bookDAO = factory.getBookDAO(connection);
		Book chechbook = bookDAO.getByAllData(book);
		if (chechbook == null) {
			bookDAO.create(book);
		} else {
			chechbook.setCount(chechbook.getCount() + 1);
			bookDAO.update(chechbook);
		}
		dataSource.closeConnection(connection);
	}

	public static void deleteBook(Integer id) {
		Connection connection = dataSource.getConnection();
		IBookDAO bookDAO = factory.getBookDAO(connection);
		Book chechbook = bookDAO.getById(id);
		if (chechbook.getCount() >= 1) {
			chechbook.setCount(chechbook.getCount() - 1);
			bookDAO.update(chechbook);

		}
		dataSource.closeConnection(connection);
	}

	public static List<Book> getAllBooks() {
		Connection connection = dataSource.getConnection();
		IBookDAO bookDAO = factory.getBookDAO(connection);
		List<Book> list = new ArrayList<>();
		list = bookDAO.getAll();
		dataSource.closeConnection(connection);
		return list;
	}

	public static List<Book> searchBooks(String sql, Date date) {
		Connection connection = dataSource.getConnection();
		IBookDAO bookDAO = factory.getBookDAO(connection);
		List<Book> list = new ArrayList<>();
		list = bookDAO.search(sql, date);
		dataSource.closeConnection(connection);
		return list;
	}

	public static Book getBookById(Integer id) {
		Connection connection = dataSource.getConnection();
		IBookDAO bookDAO = factory.getBookDAO(connection);
		Book book = bookDAO.getById(id);
		dataSource.closeConnection(connection);
		return book;
	}

	public static void addUser(User user) {
		IUserDAO userDAO = factory.getUserDAO();
		userDAO.create(user);
	}

	public static User getUserByLogin(User user) {
		IUserDAO userDAO = factory.getUserDAO();
		user = userDAO.getByLogin(user.getLogin());
		return user;
	}

	public static void addClient(Client client) {
		Connection connection = dataSource.getConnection();
		IClientDAO clientDAO = factory.getClientDAO(connection);
		clientDAO.create(client);
		dataSource.closeConnection(connection);
	}

	public static Client getClientById(Integer id) {
		Connection connection = dataSource.getConnection();
		IClientDAO clientDAO = factory.getClientDAO(connection);
		Client client = clientDAO.getById(id);
		dataSource.closeConnection(connection);
		return client;
	}

	public static Client getClientByUser(User user) {
		Connection connection = dataSource.getConnection();
		IClientDAO clientDAO = factory.getClientDAO(connection);
		Client client = clientDAO.getByUserId(user.getId());
		dataSource.closeConnection(connection);
		return client;
	}

	public static void addSubscription(Client client) {
		Connection connection = dataSource.getConnection();
		ISubscriptionDAO subscriptionDAO = factory
				.getSubscriptionDAO(connection);
		IClientDAO clientDAO = factory.getClientDAO(connection);
		Subscription subscription = new Subscription();
		subscription.setClientid(client.getId());
		client.setSubscriptionid(subscriptionDAO.create(subscription).getId());
		clientDAO.update(client);
		dataSource.closeConnection(connection);
	}

	public static List<SubscriptionItem> getAllSubItems(Client client) {
		Connection connection = dataSource.getConnection();
		ISubscriptionItemDAO subscriptionItemDAO = factory
				.getSubscriptionItemDAO(connection);

		List<SubscriptionItem> subItems = subscriptionItemDAO
				.getAllBySubId(client.getSubscriptionid());
		dataSource.closeConnection(connection);
		return subItems;
	}

	public static void turnBackSubItem(Integer subItemId) {
		Connection connection = dataSource.getConnection();
		ISubscriptionItemDAO subscriptionItemDAO = factory
				.getSubscriptionItemDAO(connection);
		IBookDAO bookDAO = factory.getBookDAO(connection);
		SubscriptionItem subscriptionItem = subscriptionItemDAO
				.getById(subItemId);
		if (subscriptionItemDAO.delete(subscriptionItem)) {
			Book book = bookDAO.getById(subscriptionItem.getBookid());
			book.setCount(book.getCount() + 1);
			bookDAO.update(book);
		}
	}

	public static Order addOrder(Order order) {
		Connection connection = dataSource.getConnection();
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		order.setStatus(EOrderStatus.NEW);
		order = orderDAO.create(order);
		dataSource.closeConnection(connection);
		return order;
	}

	public static Order submitOrder(Order order) {
		Connection connection = dataSource.getConnection();
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		// order = orderDAO.getById(order.getId());
		order.setStatus(EOrderStatus.SUBMITTED);
		orderDAO.update(order);
		dataSource.closeConnection(connection);
		return order;
	}

	public static OrderItem addOrderItem(OrderItem orderItem) {
		Connection connection = dataSource.getConnection();
		IOrderItemDAO orderItemDAO = factory.getOrderItemDAO(connection);
		deleteBook(orderItem.getBookid());
		orderItem = orderItemDAO.create(orderItem);
		dataSource.closeConnection(connection);
		return orderItem;
	}

	public static List<Book> getAllOrderItems(Integer id) {
		Connection connection = dataSource.getConnection();
		IOrderItemDAO orderItemDAO = factory.getOrderItemDAO(connection);
		List<Book> books = new ArrayList<>();
		List<OrderItem> list = orderItemDAO.getAllByOrderId(id);
		for (OrderItem orderItem : list) {
			books.add(Logic.getBookById(orderItem.getBookid()));
		}
		dataSource.closeConnection(connection);
		return books;
	}

	public static void addBooksToOrder(Order order, List<Book> list) {
		Connection connection = dataSource.getConnection();
		IOrderItemDAO orderItemDAO = factory.getOrderItemDAO(connection);
		for (Book book : list) {
			OrderItem orderItem = new OrderItem();
			orderItem.setBookid(book.getId());
			orderItem.setOrderid(order.getId());
			orderItemDAO.create(orderItem);
		}
		order.setOrderItems(list);
		dataSource.closeConnection(connection);
	}

	public static Order getOrderById(Integer id) {
		Connection connection = dataSource.getConnection();
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		Order order = orderDAO.getById(id);
		dataSource.closeConnection(connection);
		return order;
	}

	public static Order getOrderOpen(Integer clientid) {
		Connection connection = dataSource.getConnection();
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		Order order = orderDAO.getOpenOrder(clientid);
		dataSource.closeConnection(connection);
		return order;
	}

	public static List<Order> getALLSubbmitedOrders() {
		List<Order> list = null;
		Connection connection = dataSource.getConnection();
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		list = orderDAO.getAllSubbmittedOrders();
		for (Order order : list) {
			order.setClient(getClientById(order.getClientid()));
			order.setOrderItems(Logic.getAllOrderItems(order.getId()));
		}
		dataSource.closeConnection(connection);
		return list;
	}

	public static void orderToSubscription(Order order, String type) {
		Connection connection = dataSource.getConnection();
		Integer subscriptionid = null;
		IClientDAO clientDAO = factory.getClientDAO(connection);
		IOrderDAO orderDAO = factory.getOrderDAO(connection);
		ISubscriptionItemDAO subscriptionItemDAO = factory
				.getSubscriptionItemDAO(connection);
		subscriptionid = clientDAO.getById(order.getClientid())
				.getSubscriptionid();

		if (subscriptionid != null) {
			if (order.getStatus() != EOrderStatus.CLOSED) {
				for (Book book : Logic.getAllOrderItems(order.getId())) {
					SubscriptionItem subscriptionItem = new SubscriptionItem();
					subscriptionItem.setSubscriptionid(subscriptionid);
					subscriptionItem.setBookid(book.getId());
					subscriptionItem.setType(ESubItemType.valueOf(type));
					subscriptionItemDAO.create(subscriptionItem);
				}
				order.setStatus(EOrderStatus.CLOSED);
				orderDAO.update(order);
			} else {
				logger.info("Order already closed");
			}
		} else {
			logger.info("Client has no subscription! Please create it.");

		}
		dataSource.closeConnection(connection);
	}
}
