package net.kzn.PakkiShoppingOnline.model;

import java.io.Serializable;

import net.kzn.pakkiBackend.User.dto.Address;
import net.kzn.pakkiBackend.User.dto.User;

public class RegisterModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Address billing;
	
	
	public User getUser() 
	{
		return user;
	}
	public void setUser(User user) 
	{
		this.user = user;
	}
	public Address getBilling() 
	{
		return billing;
	}
	public void setBilling(Address billing) 
	{
		this.billing = billing;
	}
	
	
	

}
