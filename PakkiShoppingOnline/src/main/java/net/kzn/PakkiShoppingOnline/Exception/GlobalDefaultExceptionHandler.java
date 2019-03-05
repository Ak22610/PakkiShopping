package net.kzn.PakkiShoppingOnline.Exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler 
{
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","The Page Is Not Constructed");
		
		mv.addObject("errorDescription","The Page you are looking for is not available now!");
		
		mv.addObject("title","404 error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Product not Available");
		
		mv.addObject("errorDescription","The Product you are looking for is not available right now!");
		
		mv.addObject("title","Product Unavailable");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlException(Exception ex)
	{
		ModelAndView mv = new ModelAndView("error");
		
		mv.addObject("errorTitle","Contact your Administrator!");
		
		// only for debugging your application
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		mv.addObject("errorDescription",sw.toString());
		
		mv.addObject("title","Error");
		
		return mv;
	}


}
