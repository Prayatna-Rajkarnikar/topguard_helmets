package model;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtil;

public class UpdateUserModel {
	
	private int user_id;
	private String userName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private LocalDate dob;
	private String address;	
	private String password;
	private String gender;
	private String userImageUrl;
	private String role;
	
	public UpdateUserModel(){}
	
	public UpdateUserModel(int user_id, String userName, String fullName, String email, String phoneNumber, LocalDate dob,
			String address, String password, String gender, Part user_image, String role) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.address = address;
		this.password = password;
		this.gender = gender;
		this.userImageUrl = getImageUrl(user_image);
		this.role = role;
	}

	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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