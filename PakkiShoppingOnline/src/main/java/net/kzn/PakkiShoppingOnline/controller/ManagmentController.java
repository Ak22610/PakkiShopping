package net.kzn.PakkiShoppingOnline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.PakkiShoppingOnline.util.FileUploadUtility;
import net.kzn.PakkiShoppingOnline.validator.ProductValidator;
import net.kzn.pakkiBackend.dao.CategoryDAO;
import net.kzn.pakkiBackend.dto.Category;
import net.kzn.pakkiBackend.product.dao.ProductDAO;
import net.kzn.pakkiBackend.product.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagmentController {

@Autowired
private CategoryDAO categoryDAO;

@Autowired
private ProductDAO productDAO;

private static final Logger logger = LoggerFactory.getLogger(ManagmentController.class); // loggerprocces is best for the debugging process

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView shoeManageProducts(@RequestParam(name="operation", required=false)String operation) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nProduct = new Product();
		
		//set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct); // as soon as the product is been added new id will be form
		
		if(operation != null)
		{
			if(operation.equals("product"))
			{
				mv.addObject("message","Product Submitted Succesfully!");
			}
			else if(operation.equals("category"))
			{
				mv.addObject("message","Category Submitted Succesfully!");
			}
		}

		return mv;
	}
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		// fetch the product from the database
		Product nProduct = productDAO.get(id);
		
		// set the product fetch from database
		mv.addObject("product", nProduct);
		
		
		return mv;
	}
	
	
	
	//handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	// whenever add valid annotation the next parameter shud be BindingResult always before model
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) //string used because
	{
		// handle image validation for new products
		if(mProduct.getId() == 0)
		{
			new ProductValidator().validate(mProduct, results);
		}
		else
		{
			if(!mProduct.getFile().getOriginalFilename().equals(""))
				new ProductValidator().validate(mProduct, results);
		}
		
		//check if there are any errors
		if(results.hasErrors())
		{
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Product");
			model.addAttribute("message","Validation failed for the Product Submission");
			
			return "page";   // if use redirect here the error message will not display
		}
		
		logger.info(mProduct.toString());
		
		//create a new product record
		productDAO.add(mProduct);
		if(mProduct.getId() == 0)
		{
			// create a new product record if Id is 0
			productDAO.add(mProduct);
		}
		else
		{
			// update the product if id is not 0
			productDAO.update(mProduct);
		}
		
		
		
		if(!mProduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id)
	{
		// it is going to fetch the product from the database
		Product product = productDAO.get(id);    // getting the prodcuct from the Id
		boolean isActive = product.isActive();   // it will tells that product is True or false state
		
		// here we are activation and deactivation based on the value of active field
		product.setActive(!product.isActive());  // it wil do the if the product is active then it will deactivate it and if it is De-activate then it will make activate it 
		
		// updating the Product
		productDAO.update(product);
		
		
		return (isActive)? 
				"You Have succesfully Deactivated the product with Id" + product.getId() : 
			    "You Have succesfully Activated the product with Id" + product.getId();
		
	}
	
	// to handle category submission
	@RequestMapping(value="/category" , method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category)
	{
		//add the new category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	//return categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory()
	{
		return new Category();
	}
}
