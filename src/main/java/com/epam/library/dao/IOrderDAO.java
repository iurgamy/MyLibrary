package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.Order;

public interface IOrderDAO {
	
	public Order create(Order order);

	public Order getById(Integer id);

	public Order getOpenOrder(Integer clientid);
	
	public void update(Order object);

	public boolean delete(Order object);

	public List<Order> getAll();
	
	public List<Order> getAllSubbmittedOrders();

}
