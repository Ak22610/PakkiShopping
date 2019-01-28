package net.kzn.PakkiShoppingOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// we have to annotate it by controller anotation

@Controller  // it will help all the mapping to this page
public class PageController 
{
	@RequestMapping(value= {"/","/home","/index"})
	public ModelAndView index() //model and view is the class which helps the model as well as the view name
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting","Welcome To Spring-MVC");
		return mv;  
	}

}

// Example of RequestMapping and PathVariables

	/*@RequestMapping(value="/test")
	// once we add required=false it makes the statement so that greeting is not mandatory to write
	public ModelAndView test(@RequestParam(value="greeting",required=false)String greeting)
	{
		if(greeting == null)
		{
			greeting = "Hello There";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv; 
	}
	
	@RequestMapping(value="/test/{greeting}") // the value of this mapping is dynamic thats why it needs curly braces
	// once we add required=false it makes the statement so that greeting is not mandatory to write
	public ModelAndView test(@PathVariable("greeting")String greeting)
	{
		if(greeting == null)
		{
			greeting = "Hello There";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv; 
	}*/