package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.OrderItem;

public interface IOrderItemDAO {
	
	public OrderItem create(OrderItem object);

	public OrderItem getById(Integer id);

	public void update(OrderItem object);

	public boolean delete(OrderItem object);

	public List<OrderItem> getAll();

	public List<OrderItem> getAllByOrderId(Integer id);

}
