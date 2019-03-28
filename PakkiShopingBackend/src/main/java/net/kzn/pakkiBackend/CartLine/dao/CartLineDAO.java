package net.kzn.pakkiBackend.CartLine.dao;

import java.util.List;

import net.kzn.pakkiBackend.CartLine.dto.CartLine;
import net.kzn.pakkiBackend.User.dto.Cart;

public interface CartLineDAO 
{
	// the common methods 
	public CartLine get(int id);
	public boolean add(CartLine cartline);
	public boolean update(CartLine cartline);
	public boolean delete(CartLine cartline);
	public List<CartLine> list(int cartId);
	
	//other business methods related to the cartline
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	
	//update a cart
	boolean updateCart(Cart cart);
	
}
