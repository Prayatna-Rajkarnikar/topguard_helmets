package model;

public class LoginUserModel {
	private String user_name;
	private String password;
	private String role;
	
	public LoginUserModel(String user_name, String password, String role) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.role = role;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
