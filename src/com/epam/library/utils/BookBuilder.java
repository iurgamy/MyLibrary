package com.epam.library.utils;

import java.sql.Date;

import com.epam.library.entity.Book;

public class BookBuilder {

	private Integer id;
	private String title;
	private String author;
	private Date year;
	private Double price;
	private Integer count = 1;
	
	public BookBuilder withId(Integer id) {
		this.id = id;
		return this;
	}

	public BookBuilder withTitle(String title) {
		this.title = title;
		return this;
	}

	public BookBuilder withAuthor(String author) {
		this.author = author;
		return this;
	}

	public BookBuilder withYear(Date year) {
		this.year = year;
		return this;
	}

	public BookBuilder withPrice(Double price) {
		this.price = price;
		return this;
	}

	public BookBuilder withCount(Integer count) {
		this.count = count;
		return this;
	}

	public Book build() {
		return new Book(id, title, author, year, price, count);
	}
}
