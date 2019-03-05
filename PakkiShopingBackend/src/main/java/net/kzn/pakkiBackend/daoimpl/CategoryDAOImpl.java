package net.kzn.pakkiBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.pakkiBackend.dao.CategoryDAO;
import net.kzn.pakkiBackend.dto.Category;

@Repository("categoryDAO") // this repository is been autowired in the page controller
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * private static List<Category> categories = new ArrayList();
	 * 
	 * static { Category category = new Category();
	 * 
	 * // adding first Category category.setId(01); category.setName("Television");
	 * category.setDescription("This is Some Description for Television");
	 * category.setImageURL("Cat_1.png"); category.setActive(true);
	 * 
	 * categories.add(category);
	 * 
	 * // adding second Category Category category1 = new Category();
	 * 
	 * category1.setId(02); category1.setName("Mobile");
	 * category1.setDescription("This is Some Description for Mobile");
	 * category1.setImageURL("Cat_2.png"); category1.setActive(true);
	 * 
	 * categories.add(category1);
	 * 
	 * // adding third Category Category category2 = new Category();
	 * 
	 * category2.setId(03); category2.setName("Laptop");
	 * category2.setDescription("This is Some Description for Laptop");
	 * category2.setImageURL("Cat_3.png"); category2.setActive(true);
	 * 
	 * categories.add(category2); }
	 */

	public List<Category> list() 
	{
		String selectActiveCategory = "FROM Category WHERE active = :active";  // it is from the hibernate query language it is always going to be your entity name not in the database name
		 
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		
		return query.getResultList();
	}

	/*
	 * Getting single category based on id
	 */
	public Category get(int id) {
		/*
		 * // enhanced for loop for(Category category : categories) // categories is the
		 * collection { if(category.getId() == id) return category;
		 * 
		 * }
		 */

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) 
	{
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}

	}

	/*
	 * Updating the single Category
	 *  */
	
	@Override
	public boolean update(Category category) 
	{
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean delete(Category category) {

		category.setActive(false); 
		
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}

}
}