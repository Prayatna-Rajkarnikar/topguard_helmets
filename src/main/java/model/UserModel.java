package model;

import java.io.File;

import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtil;

public class UserModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user_name;
	private String full_name;
	private String email;
	private String phone_number;
	private LocalDate dob;
	private String address;	
	private String password;
	private String gender;
	private String userImageUrl;
	private String role;
	
	public UserModel() {}

	public UserModel(String user_name, String full_name, String email, String phone_number, LocalDate dob,
			String address, String password, String gender, Part user_image, String role) {
		super();
		this.user_name = user_name;
		this.full_name = full_name;
		this.email = email;
		this.phone_number = phone_number;
		this.dob = dob;
		this.address = address;
		this.password = password;
		this.gender = gender;
		this.userImageUrl = getImageUrl(user_image);
		this.role = role;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(Part part) {
		this.userImageUrl = getImageUrl(part);	
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setImageUrlFromDB(String imageUrl) {
		this.userImageUrl = imageUrl;
	}
	
	private String getImageUrl(Part part) {
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

