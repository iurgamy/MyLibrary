package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.SubscriptionItem;

public interface ISubscriptionItemDAO {
	
	public SubscriptionItem create(SubscriptionItem object);

	public SubscriptionItem getById(Integer id);

	public void update(SubscriptionItem object);

	public boolean delete(SubscriptionItem object);

	public List<SubscriptionItem> getAll();
	
	public List<SubscriptionItem> getAllBySubId(Integer id);


}
