package net.kzn.pakkiBackend.User.dao;

import java.util.List;

import net.kzn.pakkiBackend.User.dto.Address;
import net.kzn.pakkiBackend.User.dto.User;

public interface UserDAO 
{
	//add an User
	boolean addUser(User user);
	User getByEmail(String email);
	
	
	//add anddress
	boolean addAddress(Address address);
	
	//alternative
	 Address getBillingAddress(int userId);
	 List<Address> listShippingAddress(int userId);
	
	
	// Address getBillingAddress(User user);
	// List<Address> listShippingAddress(User user);
	

}
