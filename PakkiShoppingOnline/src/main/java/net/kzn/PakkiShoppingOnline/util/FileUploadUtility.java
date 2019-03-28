package net.kzn.PakkiShoppingOnline.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility 
{
	private static final String ABS_PATH = "C://Users//Administrator//git//PakkiShopping//PakkiShoppingOnline//src//main//webapp//assets//images//";
	private static String REAL_PATH = "";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) 
	{
		// get the real Path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		// to make sure all the directory exist
		// please create the directories 
		if(!new File(ABS_PATH).exists())
		{
			//create the directories
			new File(ABS_PATH).mkdirs(); // this method will make all he directory
			
			
		}
		
		if(!new File(ABS_PATH).exists())
		{
			//create the directories
			new File(REAL_PATH).mkdirs(); // this method will make all he directory
			
		}
		
		try
		{
			// server upload
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			
			// project directory upload
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	

}
