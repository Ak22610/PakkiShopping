package net.kzn.pakkiBackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category 
{
	
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getImageURL() 
	{
		return imageURL;
	}
	public void setImageURL(String imageURL) 
	{
		this.imageURL = imageURL;
	}
	public boolean isActive() 
	{
		return active;
	}
	public void setActive(boolean active) 
	{
		this.active = active;
	}
	
	
	
	/* 
	 * private fields
	 * */
	
	@Override
	public String toString() 
	{
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL
				+ ", active=" + active + "]";
	}



	@Id  // it will be the primary key in the database
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto generated fields   // to incremented automatically the identity we use this annotation
	
	private int id;
	
	private String name;
	
	private String description;
	
	
	@Column(name = "image_url")
	private String imageURL;
	
	@Column(name = "is_active")
	private boolean active = true;  // admin has a right to deactivate this 
	
	
}
