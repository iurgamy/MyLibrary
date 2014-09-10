package com.epam.library.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.staticdata.EOrderStatus;

public class Order implements IDefault, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7886382760471247106L;
	private Integer id;
	private Integer clientid;
	private EOrderStatus status;
	private List<Book> orderItems;
	private Client client;

	public Order() {
		// TODO Auto-generated constructor stub
		orderItems = new ArrayList<Book>();
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

	public EOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EOrderStatus status) {
		this.status = status;
	}

	public List<Book> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Book> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientid == null) ? 0 : clientid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
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
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", clientid=" + clientid + ", status="
				+ status + ", orderItems=" + orderItems + "]" + "\n";
	}



}
