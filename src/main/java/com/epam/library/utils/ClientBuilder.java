package com.epam.library.utils;

import java.sql.Date;

import com.epam.library.entity.Client;

public class ClientBuilder {
	private Integer id;
	private Integer subscriptionid;
	private String name;
	private String surname;
	private Date birthday;
	private Integer userid;
	
	public ClientBuilder withId(Integer id) {
		this.id = id;
		return this;
	}
	
	public ClientBuilder withSubscriptionid(Integer subscriptionid) {
		this.subscriptionid = subscriptionid;
		return this;
	}
	
	public ClientBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public ClientBuilder withSurname(String surname) {
		this.surname = surname;
		return this;
	}
	
	public ClientBuilder withBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}
	
	public ClientBuilder withUserId(Integer userid) {
		this.userid = userid;
		return this;
	}
	
	public Client build() {
		return new Client(id, subscriptionid, name, surname, birthday, userid);
	}

}
