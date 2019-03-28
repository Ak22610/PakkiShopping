package net.kzn.PakkiShoppingOnline.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.PakkiShoppingOnline.model.UserModel;
import net.kzn.pakkiBackend.CartLine.dao.CartLineDAO;
import net.kzn.pakkiBackend.CartLine.dto.CartLine;
import net.kzn.pakkiBackend.User.dto.Cart;
import net.kzn.pakkiBackend.product.dao.ProductDAO;
import net.kzn.pakkiBackend.product.dto.Product;

@Service("cartService")
public class CartService {
	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;

	// returns the cart of the user who has logged in
	private Cart getCart() 
	{
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	// returns the entire cartLines
	public List<CartLine> getCartLines() 
	{
		return cartLineDAO.list(this.getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) 
	{
		// fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) 
		{
			return "result=error";
		} 
		else 
		{
			Product product = cartLine.getProduct();

			double oldTotal = cartLine.getTotal();

			if (product.getQuanitity() <= count) 
			{
				count = product.getQuanitity();
			}

			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setTotal(product.getUnitprice() * count);
			cartLineDAO.update(cartLine);
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
					
			return "result=updated";
		}

	}
	public String deleteCartLine(int cartLineId) 
	{
		//fetch the cartline
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null)
		{
			return "result=error";
		}
		else
		{
			//upodate the cart
			Cart cart = this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
					
			//remove the cart line
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";
		}
		
	}
	public String addCartLine(int productid)
	{
		String response = null;
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productid);
		
		if(cartLine == null)
		{
			//add new CartLine
			cartLine = new CartLine();
			
			//fetch the product
			Product product = productDAO.get(productid);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitprice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines());
			cart.setGrandTotal(cart.getGrandTotal());
			cartLineDAO.updateCart(cart);
			response = "result=added";
			
		}
		return response;
		
	}

}