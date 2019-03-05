package net.kzn.pakkiBackend.product.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kzn.pakkiBackend.product.dao.ProductDAO;
import net.kzn.pakkiBackend.product.dto.Product;

@Repository("productDAO")
@Transactional // this is because all these methods will run under transaction
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * SINGLE
	 */

	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * SINGLE
	 */

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();

	}

	/*
	 * INSERT
	 */
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	/*
	 * UPDATE
	 */
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	/*
	 * DELETE
	 */
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);

			// call the update method
			return this.update(product);
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";

		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();

	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";

		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) 
	{
		return sessionFactory.getCurrentSession().createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}
	}

