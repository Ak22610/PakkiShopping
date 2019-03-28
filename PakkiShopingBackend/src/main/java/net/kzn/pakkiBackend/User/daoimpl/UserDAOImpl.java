package net.kzn.pakkiBackend.User.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.pakkiBackend.User.dao.UserDAO;
import net.kzn.pakkiBackend.User.dto.Address;
import net.kzn.pakkiBackend.User.dto.User;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean addUser(User user)
	{
		try
		{
			sessionFactory.getCurrentSession().persist(user);
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) 
	{
		try
	{
		sessionFactory.getCurrentSession().persist(address);
		
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}

	

	@Override
	public User getByEmail(String email) 
	{
		String selectQuery = "FROM User WHERE email = :email";
		
		try
		{
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		}
		catch(Exception e)
		{
   //	e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE user = :user AND billing = :billing";
		try
		{
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("userId", userId).setParameter("billing", true).getSingleResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(int userId) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping";
		try
		{
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("userId", userId).setParameter("shipping", true).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
