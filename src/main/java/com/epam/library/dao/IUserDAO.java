package com.epam.library.dao;

import java.util.List;

import com.epam.library.entity.User;

public interface IUserDAO {

	public User create(User object);

	public User getById(Integer id);
	
	public User getByLogin(String login);

	public void update(User object);

	public boolean delete(User object);

	public List<User> getAll();
	
	public boolean checkUser(User user);

}
