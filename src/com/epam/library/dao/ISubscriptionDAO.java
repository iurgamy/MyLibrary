package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.Subscription;

public interface ISubscriptionDAO {

	public Subscription create(Subscription object);

	public Subscription getById(Integer id);

	public void update(Subscription object);

	public boolean delete(Subscription object);

	public List<Subscription> getAll();

}
