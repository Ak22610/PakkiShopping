package net.kzn.PakkiShopingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.pakkiBackend.dao.CategoryDAO;
import net.kzn.pakkiBackend.dto.Category;

public class CategoryTestCase 
{
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.pakkiBackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	/*@Test
	public void testAddCategory()
	{
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is Some Description for Television");
		category.setImageURL("Cat_1.png");
		
		category = new Category();
		category.setName("Mobile");
		category.setDescription("This is Some Description for Mobile");
		category.setImageURL("Cat_2.png");
		
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is Some Description for Laptop");
		category.setImageURL("Cat_3.png");
		
		assertEquals("Succesfully Added a category inside the table",true,categoryDAO.add(category));

	@Test
	public void testGetCategory()
	{
		category = categoryDAO.get(1);
		
		assertEquals("Succesfully Fetched single category from the table","Television",category.getName());
	}
	
	@Test
	public void testUpdateCategory()
	{
		category = categoryDAO.get(1);
		category.setName("Television");
		assertEquals("Succesfully Updated single category from in the table!",true,categoryDAO.update(category));
	}
	
	@Test
	public void testDeleteCategory()
	{
		category = categoryDAO.get(1);
		assertEquals("Succesfully deleted single category in the table!",true,categoryDAO.delete(category));
	}
	
	
	@Test
	public void testListCategory()
	{
		assertEquals("Succesfully fetched the single categories from the table!",2,categoryDAO.list().size());
	}
	*/
	@Test
	public void testCRUDCategory()
	{
		category = new Category();
		
		category.setName("Mobile");
		category.setDescription("This is Some Description for Television");
		category.setImageURL("Cat_1.png");

		assertEquals("Succesfully Added a category inside the table",true,categoryDAO.add(category));

		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is Some Description for Mobile");
		category.setImageURL("Cat_2.png");

		assertEquals("Succesfully Added a category inside the table",true,categoryDAO.add(category));

		// fetching and updating the category
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Succesfully Updated single category from in the table!",true,categoryDAO.update(category));
		
		// delete the category
		assertEquals("Succesfully deleted single category in the table!",true,categoryDAO.delete(category));
	
		// fetching the list
		assertEquals("Succesfully fetched the single categories from the table!",12,categoryDAO.list().size());
		
	}
	
	
	}

