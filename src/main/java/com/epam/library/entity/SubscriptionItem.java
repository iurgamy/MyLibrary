package com.epam.library.entity;

import java.io.Serializable;

import com.epam.library.staticdata.ESubItemType;

public class SubscriptionItem implements IDefault, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 134644945771025391L;
	private Integer id;
	private Integer subscriptionid;
	private Integer bookid;
	private ESubItemType type;
	
	public SubscriptionItem() {
	
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubscriptionid() {
		return subscriptionid;
	}
	public void setSubscriptionid(Integer subscriptionid) {
		this.subscriptionid = subscriptionid;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public ESubItemType getType() {
		return type;
	}
	public void setType(ESubItemType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SubscriptionItem [id=" + id + ", subscriptionid="
				+ subscriptionid + ", bookid=" + bookid + ", type=" + type
				+ "]" + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookid == null) ? 0 : bookid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((subscriptionid == null) ? 0 : subscriptionid.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SubscriptionItem))
			return false;
		SubscriptionItem other = (SubscriptionItem) obj;
		if (bookid == null) {
			if (other.bookid != null)
				return false;
		} else if (!bookid.equals(other.bookid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subscriptionid == null) {
			if (other.subscriptionid != null)
				return false;
		} else if (!subscriptionid.equals(other.subscriptionid))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	

}
