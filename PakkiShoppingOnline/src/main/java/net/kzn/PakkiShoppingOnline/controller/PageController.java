package net.kzn.PakkiShoppingOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.pakkiBackend.dao.CategoryDAO;
import net.kzn.pakkiBackend.dto.Category;
// we have to annotate it by controller anotation

@Controller // it will help all the mapping to this page
public class PageController {
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() // model and view is the class which helps the model as well as the view name
	{

		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting","Welcome To Spring-MVC");
		mv.addObject("title", "Home");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickHome", "true"); // this value will be true only if index and home will be there
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() // model and view is the class which helps the model as well as the view name
	{
		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting","Welcome To Spring-MVC");
		mv.addObject("title", "About Us");
		
		mv.addObject("userClickAbout", "true"); // this value will be true only if index and home will be there

		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() // model and view is the class which helps the model as well as the view name
	{
		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting","Welcome To Spring-MVC");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", "true"); // this value will be true only if index and home will be there

		return mv;
	}
	/* 
	 * Methods to load all the products and based on category
	 * */
	
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() 
	{

		ModelAndView mv = new ModelAndView("page");
		// mv.addObject("greeting","Welcome To Spring-MVC");
		mv.addObject("title", "All Products");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true); // this value will be true only if index and home will be there
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id")int id) // we are having access to this products 
	{

		ModelAndView mv = new ModelAndView("page");
		
		
		//categoryDAO to fetch a single category
		
		Category category = null;
		category = categoryDAO.get(id);
		
		// THREE OBJECT WE ARE PASSING WHICH IS TITLE CATEGORIES CATEGORY
		
		// mv.addObject("greeting","Welcome To Spring-MVC");
		mv.addObject("title", category.getName()); // bcuss of this it displaying the category in the web page

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		// passing the single category object 
		mv.addObject("category", category);
		

		mv.addObject("userClickCategoryProducts", true); // this value will be true only if index and home will be there
		return mv;
	}

}

// Example of RequestMapping and PathVariables

/*
 * @RequestMapping(value="/test") // once we add required=false it makes the
 * statement so that greeting is not mandatory to write public ModelAndView
 * test(@RequestParam(value="greeting",required=false)String greeting) {
 * if(greeting == null) { greeting = "Hello There"; } ModelAndView mv = new
 * ModelAndView("page"); mv.addObject("greeting",greeting); return mv; }
 * 
 * @RequestMapping(value="/test/{greeting}") // the value of this mapping is
 * dynamic thats why it needs curly braces // once we add required=false it
 * makes the statement so that greeting is not mandatory to write public
 * ModelAndView test(@PathVariable("greeting")String greeting) { if(greeting ==
 * null) { greeting = "Hello There"; } ModelAndView mv = new
 * ModelAndView("page"); mv.addObject("greeting",greeting); return mv; }
 */