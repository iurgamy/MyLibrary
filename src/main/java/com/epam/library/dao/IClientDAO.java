package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.Client;

public interface IClientDAO {

	public Client create(Client object);

	public Client getById(Integer id);

	public void update(Client object);

	public boolean delete(Client object);

	public List<Client> getAll();

}
