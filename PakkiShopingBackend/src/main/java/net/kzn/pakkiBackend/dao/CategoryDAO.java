package net.kzn.pakkiBackend.dao;

import java.util.List;

import net.kzn.pakkiBackend.dto.Category;

public interface CategoryDAO 
{
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
	
	 // this becomes an abstract method

	
}
