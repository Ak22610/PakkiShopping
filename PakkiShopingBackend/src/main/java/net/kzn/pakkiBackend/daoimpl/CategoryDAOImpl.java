package net.kzn.pakkiBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.kzn.pakkiBackend.dao.CategoryDAO;
import net.kzn.pakkiBackend.dto.Category;

@Repository("categoryDAO")  // this repository is been autowired in the page controller
public class CategoryDAOImpl implements CategoryDAO 
{

	private static List<Category> categories = new ArrayList();

	static 
	{
		Category category = new Category();

		// adding first Category
		category.setId(01);
		category.setName("Television");
		category.setDescription("This is Some Description for Television");
		category.setImageURL("Cat_1.png");
		category.setActive(true);

		categories.add(category);

		// adding second Category
		Category category1 = new Category();
		
		category1.setId(02);
		category1.setName("Mobile");
		category1.setDescription("This is Some Description for Mobile");
		category1.setImageURL("Cat_2.png");
		category1.setActive(true);

		categories.add(category1);

		// adding third Category
		Category category2 = new Category();
		
		category2.setId(03);
		category2.setName("Laptop");
		category2.setDescription("This is Some Description for Laptop");
		category2.setImageURL("Cat_3.png");
		category2.setActive(true);

		categories.add(category2);
	}

	public List<Category> list() 
	{

		return categories;
	}

	public Category get(int id) 
	{
		// enhanced for loop
		for(Category category : categories)  // categories is the collection
		{
			if(category.getId() == id) return category;
				
		}
		return null;
	}

}

