package com.epam.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subscription implements IDefault, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3536114597619198197L;
	private Integer id;
	private Integer clientid;
	private List<Book> subscriptionItems;

	public Subscription() {
		// TODO Auto-generated constructor stub
		setSubscriptionItems(new ArrayList<Book>());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientid() {
		return clientid;
	}

	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}

	public List<Book> getSubscriptionItems() {
		return subscriptionItems;
	}

	public void setSubscriptionItems(List<Book> subscriptionItems) {
		this.subscriptionItems = subscriptionItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientid == null) ? 0 : clientid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((subscriptionItems == null) ? 0 : subscriptionItems
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Subscription))
			return false;
		Subscription other = (Subscription) obj;
		if (clientid == null) {
			if (other.clientid != null)
				return false;
		} else if (!clientid.equals(other.clientid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subscriptionItems == null) {
			if (other.subscriptionItems != null)
				return false;
		} else if (!subscriptionItems.equals(other.subscriptionItems))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", clientid=" + clientid
				+ ", subscriptionItems=" + subscriptionItems + "]" + "\n";
	}

}
