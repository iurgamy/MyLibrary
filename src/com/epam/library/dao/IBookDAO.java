package com.epam.library.dao;

import java.sql.Date;
import java.util.List;

import com.epam.library.entity.Book;

public interface IBookDAO {
	
	public Book create(Book object);

	public Book getById(Integer id);

	public void update(Book object);

	public boolean delete(Book object);
	
	public Book getByAllData(Book object);

	public List<Book> getAll();
	
	public List<Book> search(String sql, Date year);

}
