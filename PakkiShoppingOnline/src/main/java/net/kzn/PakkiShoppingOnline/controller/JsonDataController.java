package net.kzn.PakkiShoppingOnline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kzn.pakkiBackend.product.dao.ProductDAO;
import net.kzn.pakkiBackend.product.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController 
{
	@Autowired   // we use aut
	private ProductDAO productDAO;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product>getAllProducts()
	{
		return productDAO.listActiveProducts();
	}
	@RequestMapping("admin/all/products")
	@ResponseBody
	public List<Product>getAllProductsForAdmin()
	{
		return productDAO.list();
	}
	
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody // this is used to send the data in the form of JSON
	public List<Product>getAllProductsByCategory(@PathVariable int id)
	{
		return productDAO.listActiveProductsByCategory(id);
		
	}
	
	
}
