package com.epam.library.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CLIENTS")
@NamedQueries({
		@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
		@NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
		@NamedQuery(name = "Client.findBySubscriptionid", query = "SELECT c FROM Client c WHERE c.subscriptionid = :subscriptionid"),
		@NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
		@NamedQuery(name = "Client.findBySurname", query = "SELECT c FROM Client c WHERE c.surname = :surname"),
		@NamedQuery(name = "Client.findByBirthday", query = "SELECT c FROM Client c WHERE c.birthday = :birthday") })
public class Client implements IDefault, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5437119001928387785L;
	@Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name= "SUBSCRIPTIONID")
	private Integer subscriptionid;
	@Column(name= "NAME", length=255)
	private String name;
	@Column(name= "SURNAME", length=255)
	private String surname;
	@Column(name= "BIRTHDAY")
	@Temporal(TemporalType.DATE)
	private java.util.Date birthday;
	@JoinColumn(name = "USERID", referencedColumnName = "id")
    @OneToOne
	private User user;

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(Integer id, Integer subscriptionid, String name,
			String surname, Date birthday, User user) {
		// TODO Auto-generated constructor stub
		setId(id);
		setSubscriptionid(subscriptionid);
		setName(name);
		setSurname(surname);
		setBirthday(birthday);
		setUser(user);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the subscriptionid
	 */
	public Integer getSubscriptionid() {
		return subscriptionid;
	}

	/**
	 * @param subscriptionid
	 *            the subscriptionid to set
	 */
	public void setSubscriptionid(Integer subscriptionid) {
		this.subscriptionid = subscriptionid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the birthday
	 */
	public java.util.Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((subscriptionid == null) ? 0 : subscriptionid.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subscriptionid == null) {
			if (other.subscriptionid != null)
				return false;
		} else if (!subscriptionid.equals(other.subscriptionid))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", subscriptionid=" + subscriptionid
				+ ", name=" + name + ", surname=" + surname + ", birthday="
				+ birthday + ", userid=" + user + "]" + "\n";
	}

}
