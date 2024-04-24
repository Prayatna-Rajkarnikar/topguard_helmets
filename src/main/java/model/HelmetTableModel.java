package model;

public class HelmetTableModel {
	private int helmet_ID;
	private String helmet_Name;
    private double price;
    private String brand;
    private String color;
    private String size;
    private String userImageUrl;
    
	public HelmetTableModel(int helmet_ID, String helmet_Name, double price, String brand, String color, String size,
			String userImageUrl) {
		super();
		this.helmet_ID = helmet_ID;
		this.helmet_Name = helmet_Name;
		this.price = price;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.userImageUrl = userImageUrl;
	}
	
	public HelmetTableModel() {
		
	}
	
	public int getHelmet_ID() {
		return helmet_ID;
	}


	public void setHelmet_ID(int helmet_ID) {
		this.helmet_ID = helmet_ID;
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

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
    
    
	

}
