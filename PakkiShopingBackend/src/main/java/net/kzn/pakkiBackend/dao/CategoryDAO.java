package net.kzn.pakkiBackend.dao;

import java.util.List;

import net.kzn.pakkiBackend.dto.Category;

public interface CategoryDAO 
{
	List<Category> list(); 
	Category get(int id); // this becomes an abstract method

	
}
