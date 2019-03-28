package net.kzn.PakkiShoppingBackend.CartLine.Test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.pakkiBackend.CartLine.dao.CartLineDAO;
import net.kzn.pakkiBackend.CartLine.dto.CartLine;
import net.kzn.pakkiBackend.User.dao.UserDAO;
import net.kzn.pakkiBackend.User.dto.Cart;
import net.kzn.pakkiBackend.User.dto.User;
import net.kzn.pakkiBackend.product.dao.ProductDAO;
import net.kzn.pakkiBackend.product.dto.Product;

public class CartLineTest 
{
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.pakkiBackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
	}
	@Test
	public void testAddNewCartLine()
	{
		
		//1. get the user
		user = userDAO.getByEmail("ak@gmail.com");
		
		//2. fetch the cart
		cart = user.getCart();
		
		//3. get the product
		product = productDAO.get(1);
		
		//4. create a new cartLine
		
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitprice());
		
		cartLine.setProductCount(cartLine.getProductCount()+1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		AssertEquals("Failed to add the cartLine",true,cartLineDAO.add(cartLine));
		
		///update the cart 
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		AssertEquals("Failed to update the cartLine",true,cartLineDAO.updateCart(cart));
		
	}
	private void AssertEquals(String string, boolean b, boolean add) {
		// TODO Auto-generated method stub
		
	}
	

}
