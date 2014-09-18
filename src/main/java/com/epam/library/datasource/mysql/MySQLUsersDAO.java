package com.epam.library.datasource.mysql;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.epam.library.dao.IUserDAO;
import com.epam.library.entity.User;
import com.epam.library.utils.HibernateUtil;

public class MySQLUsersDAO implements IUserDAO {
	public static Logger logger = Logger.getLogger(MySQLUsersDAO.class
			.getName());

	public MySQLUsersDAO() {
	}

	public boolean checkUser(User user) {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("User.findByLoginAndPassword")
					.setParameter("login", user.getLogin())
					.setParameter("password", user.getPassword());
			user = (User) query.list().iterator().next();
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error("User '" + user.getLogin() + "' not found", ex);			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		if (user.getId() != null) {
			return true;
		} else {

			return false;
		}

	}

	@Override
	public User getByLogin(String login) {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("User.findByLogin")
					.setParameter("login", login);
			user = (User) query.list().iterator().next();
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return user;

	}

	public User create(User user) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

	@Override
	public User getById(Integer id) {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			user = (User) session.load(User.class, id);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;

	}

	@Override
	public void update(User user) {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean delete(User user) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Session session = null;
		List<User> users = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("User.findAll");
			users = query.list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return users;
	}

}
