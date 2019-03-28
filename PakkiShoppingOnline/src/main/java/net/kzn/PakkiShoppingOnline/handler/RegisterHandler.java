package net.kzn.PakkiShoppingOnline.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.kzn.PakkiShoppingOnline.model.RegisterModel;
import net.kzn.pakkiBackend.User.dao.UserDAO;
import net.kzn.pakkiBackend.User.dto.Address;
import net.kzn.pakkiBackend.User.dto.Cart;
import net.kzn.pakkiBackend.User.dto.User;

@Component
public class RegisterHandler 
{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init()
	{
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user)
	{
		registerModel.setUser(user);
	}
	public void addBilling(RegisterModel registerModel, Address billing)
	{
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error)
	{
		String transitionValue = "success";
		//checking if password matches confirm password 
		
		if(!(user.getPassword().equals(user.getConfirmPassword())))
		{
			// it will build the statment for us 
			error.addMessage(new MessageBuilder().error().
					source("confirmPassword").
					defaultText("Password does not match the confirm password").
					build());
			
			
			transitionValue = "failure";
		}
		
		//check the uniqueness of the email id
		
		if(userDAO.getByEmail(user.getEmail())!=null)
		{
			transitionValue = "failure";
		}
		
		error.addMessage(new MessageBuilder().error().
				source("email").
				defaultText("Email address already exist !").
				build());
		
		return transitionValue;
	}

public String saveAll(RegisterModel model)
{
	String transitionValue = "success";
	
	//fetch the user 
	User user = model.getUser();
	
	if(user.getRole().equals("USER"))
	{
		Cart cart = new Cart();
		cart.setUser(user);
		user.setCart(cart);
		
	}
	// encode the password
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	
	// save the user 
	userDAO.addUser(user);
	
	// get the address
	
	Address billing = model.getBilling();
	billing.setUserId(user.getId());   // it will do the relation between the user and the address
	billing.setBilling(true); 
	
	// save the address
	userDAO.addAddress(billing);
	
	
	
	return transitionValue;
	
}

}







