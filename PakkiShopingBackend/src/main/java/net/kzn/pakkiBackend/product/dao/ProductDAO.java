package net.kzn.pakkiBackend.product.dao;

import java.util.List;

import net.kzn.pakkiBackend.product.dto.Product;

public interface ProductDAO 
{
	Product get(int productId);
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete(Product product);

	// bussiness methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
	
}
