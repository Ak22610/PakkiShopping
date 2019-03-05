package net.kzn.PakkiShoppingOnline.Exception;

public class ProductNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ProductNotFoundException()
	{
		this("Product is not available!");
	}
	public ProductNotFoundException(String message)
	{
		this.message = System.currentTimeMillis() + ": " + message;
	}
	
	// getter and setters
	public String getMessage() 
	{
		return message;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	


}
