package model;

import java.io.File;

import javax.servlet.http.Part;

import util.StringUtil;

public class HelmetModel {
	private String helmet_Name;
    private double price;
    private String brand;
    private String color;
    private String size;
    private String userImageUrl;
    
	public HelmetModel(String helmet_Name, double price, String brand, String color, String size, Part helmet_image) {
		super();
		this.helmet_Name = helmet_Name;
		this.price = price;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.userImageUrl = getImageUrl(helmet_image);
		
	}

	public String getHelmet_Name() {
		return helmet_Name;
	}

	public void setHelmet_Name(String helmet_Name) {
		this.helmet_Name = helmet_Name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	 public String getUserImageUrl() {
	        return userImageUrl;
	    }

	    public void setUserImageUrl(Part part) {
	        this.userImageUrl = getImageUrl(part);    
	    }
	    
	    public void setImageUrlFromDB(String imageUrl) {
	        this.userImageUrl = imageUrl;
	    }
	    
	    private String getImageUrl(Part part) {
	        if (part == null) {
	            return "default_image.jpg"; // Return a default image URL or handle this case as per your requirement
	        }
	        String savePath = StringUtil.IMAGE_DIR_SAVE_PATH;
	        File fileSaveDir = new File(savePath);
	        String userImageUrl = null;
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                userImageUrl = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            }
	        }
	        if ( userImageUrl== null || userImageUrl.isEmpty()) {
	            userImageUrl = "download.jpg";
	        }
	        return userImageUrl;
	    }
    
    

}
