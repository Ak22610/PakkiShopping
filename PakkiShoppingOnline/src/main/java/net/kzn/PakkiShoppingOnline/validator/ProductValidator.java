package net.kzn.PakkiShoppingOnline.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.kzn.pakkiBackend.product.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;

		// whether file has been selected or not

		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please Select An image file to upload");
			return;
		}

		if (!
				(product.getFile().getContentType().equals("image/jpeg")
				|| product.getFile().getContentType().equals("image/png")
				|| product.getFile().getContentType().equals("image/gif")) 
				)
		{
				errors.rejectValue("file", null,"Please use Only Imahe file for upload");
				return;
		}
		
		

	}

}
