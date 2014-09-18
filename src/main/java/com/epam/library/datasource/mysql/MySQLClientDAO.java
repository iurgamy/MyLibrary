package com.epam.library.datasource.mysql;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.epam.library.dao.IClientDAO;
import com.epam.library.entity.Client;

import com.epam.library.utils.HibernateUtil;

public class MySQLClientDAO implements IClientDAO {

	public static Logger logger = Logger.getLogger(MySQLClientDAO.class
			.getName());

	public MySQLClientDAO() {

	}

	@Override
	public Client create(Client object) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return object;
	}

	@Override
	public Client getById(Integer id) {
		Session session = null;
		Client client = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("Client.findById");
			client = (Client) query.list().iterator().next();
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return client;
	}

	@Override
	public void update(Client object) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public boolean delete(Client object) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		Session session = null;
		List<Client> clients = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("Client.findAll");
			clients = query.list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return clients;
	}

}
