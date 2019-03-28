package net.kzn.pakkiBackend.CartLine.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kzn.pakkiBackend.CartLine.dao.CartLineDAO;
import net.kzn.pakkiBackend.CartLine.dto.CartLine;
import net.kzn.pakkiBackend.User.dto.Cart;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) 
	{
		
		return sessionFactory.getCurrentSession().get(CartLine.class,Integer.valueOf(id));
	}

	@Override
	public boolean add(CartLine cartline) 
	{
		try
		{
			sessionFactory.getCurrentSession().persist(cartline);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(CartLine cartline) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(cartline);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartline) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(cartline);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public List<CartLine> list(int cartId) 
	{
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession().createQuery(query,CartLine.class)
				.setParameter("cartId", cartId).getResultList();
		
	}

	@Override
	public List<CartLine> listAvailable(int cartId) 
	{
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession().createQuery(query,CartLine.class)
				.setParameter("cartId", cartId).setParameter("available", true).getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) 
	{
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
		return sessionFactory.getCurrentSession().createQuery(query,CartLine.class)
				.setParameter("cartId", cartId).setParameter("productId", productId).getSingleResult();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	//related to the cart
	@Override
	public boolean updateCart(Cart cart)
	{
		try
		{
			sessionFactory.getCurrentSession().update(cart);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
