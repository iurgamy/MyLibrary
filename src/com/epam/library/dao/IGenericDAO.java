package com.epam.library.dao;

import java.util.List;

public interface IGenericDAO<T> {

	public T create(T object);

	public T getById(Integer id);

	public void update(T object);

	public boolean delete(T object);

	public List<T> getAll();

}
