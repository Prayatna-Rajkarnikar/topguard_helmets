package util;

import java.io.File;

public class StringUtil {
	
	// Image Save Path
	public static final String IMAGE_DIR_USER = "Users\\HP\\eclipse-workspace\\TopGuard_Helmets\\src\\main\\webapp\\resources\\user\\";
	public static final String IMAGE_DIR_HELMET = "Users\\HP\\eclipse-workspace\\TopGuard_Helmets\\src\\main\\webapp\\resources\\helmets\\";

	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR_USER;
	public static final String IMAGE_DIR_SAVE_PATH_HELMET = "C:" + File.separator + IMAGE_DIR_HELMET;
	
	
	//Queries for user
	public static final String INSERT_NEW_USER = "INSERT INTO user "
            + "(userName, user_fullName, email, contact_number, dob, address, password, gender, user_image, role)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_USER_INFO = "SELECT * FROM user WHERE userName = ?";
    
    public static final String CHECK_USERNAME_EXISTS = "SELECT COUNT(*) FROM user WHERE userName = ?";
    
    public static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(*) FROM user WHERE email = ?";
    
    public static final String CHECK_CONTACT_EXISTS = "SELECT COUNT(*) FROM user WHERE contact_number = ?";
    
    public static final String UPDATE_PWD = "UPDATE user SET password = ? WHERE userName = ?";
    
    public static final String UPDATE_USER_INFO = "UPDATE user SET user_fullName=?, email=?, contact_number=?, address=? WHERE userName=?";
    
    public static final String OBTAIN_USER_INFO = "SELECT * FROM user WHERE userName = ?";

    
    
    //Queries for helmet
    public static final String ADD_HELMET = "INSERT INTO helmet (helmet_Name, price, brand, color, size, helmet_image) VALUES (?, ?, ?, ?,?,?)";
    
    public static final String DELETE_HELMET = "DELETE FROM helmet WHERE helmet_ID = ?";
    
    public static final String UPDATE_HELMET =  "UPDATE helmet SET helmet_Name = ?, price = ?, brand = ?, color = ?, size = ? WHERE helmet_ID = ?";
    
    public static final String GET_HELMETS =  "SELECT * FROM helmet";

    //User variables
    public static final String userName = "userName";
    public static final String userFullName = "userFullName";
    public static final String email = "userEmail";
    public static final String contactNumber = "contactNumber";
    public static final String dobString = "birthday";
    public static final String address = "address";
    public static final String password = "password";
    public static final String retype_password = "retypePassword";
    public static final String gender = "gender";
    public static final String role = "role";
    public static final String newPassword = "new_password";
    public static final String confirmNewPassword = "confirm_password";
    
    // Helmet Variables
    public static final String helmetID = "helmet_id";
    public static final String helmetName = "helmet_name";
    public static final String helmetPrice = "price";
    public static final String brand = "brand";
    public static final String color = "color";
    public static final String size = "size";
    public static final String DELETE_ID= "deleteId";


    //Register Error messages 
    public static final String INVALID_FULLNAME_ERROR_MESSAGE ="Invalid Full name. Please don't enter symbols and numerical value.";
    public static final String USERNAME_EXIST_ERROR_MESSAGE = "Username already exists. Please choose a different username.";
    public static final String USERNAME_LENGTH_ERROR_MESSAGE = "Invalid User name. Please enter more than 6 characters";
    public static final String SYMBOL_ERROR_MESSAGE = "Symbols are not allowed in this field.";
    public static final String INVALID_DATE_ERROR_MESSAGE = "Invalid birthday date.";
    public static final String EMAIL_EXIST_ERROR_MESSAGE = "Email already exists. Please use a different email address.";
    public static final String CONTACT_EXIST_ERROR_MESSAGE ="Phone number already exists. Please use a different phone number.";
    public static final String CONTACT_LENGTH_ERROR_MESSAGE = "Invalid number. Phone Number must be of 14 characters.";
    public static final String CONTACT_STARTING_ERROR_MESSAGE ="Invalid number. Phone Number must start with + sign.";
    public static final String PWD_BUILD_ERROR_MESSAGE = "Invalid password. Password must contain at least one uppercase letter, "
															+ "one number, and one special character.";
    public static final String PWD_LENGTH_ERROR_MESSAGE ="Invalid Password. Please enter more than 6 characters.";
    public static final String PWD_MISMATCHED_MESSAGE ="Password and Retype Password do not match.";
    public static final String REGISTRATION_FAILED_MESSAGE ="Registration failed. Please try again.";
    
    
    //Register Success Message
    public static final String REGISTER_SUCCESS_MESSAGE = "Successfully Registered!";
    
    //SERVER ERROR
    public static final String SERVER_ERROR = "An unexpected error occurred. Please try again later.";
    
    //Login Page Error Message
    public static final String USERNAME_NOT_FOUND = "Username not found. Please create a new account.";
    public static final String WRONG_PASSWORD = "Incorrect Password. Please try again.";
    
    //Forget Password messages
    public static final String NEWPWD_MISMATCHED_MESSAGE ="New Password and Confirm New Password do not match.";
    public static final String PWD_UPDATED_MESSAGE ="Password Reset Successful!";
    
    //Admin Page Messages
    public static final String ADD_HELMET_ERROR ="Failed to add new helmet.";
    public static final String UPDATE_HELMET_ERROR ="Failed to Update helmet information.";
    public static final String DELETE_HELMET_ERROR ="Failed to delete helmet.";
    public static final String ADD_HELMET_SUCCESS ="Helmet added successfully!";
    public static final String UPDATE_HELMET_SUCCESS ="Helmet updated successfully!";
    public static final String DELETE_HELMET_SUCCESS ="Helmet deleted successfully!";







    
    
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";


	// Other Messages
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_HOME = "/pages/home.jsp";
	public static final String PAGE_URL_PRODUCTS = "/pages/product.jsp";
	public static final String PAGE_URL_CONTACT = "/pages/contactUs.jsp";
	public static final String PAGE_URL_ABOUT_US = "/pages/aboutUs.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String URL_LOGIN = "/pages/login.jsp";
	public static final String URL_LANDINGPAGE = "/pages/landingPage.jsp";
	public static final String URL_ADMIN_PANEL = "/pages/adminPanel.jsp";
	public static final String URL_ADMIN_PRODUCT = "/pages/adminProduct.jsp";
	public static final String URL_EDIT_HELMET = "/pages/editHelmet.jsp";
	public static final String URL_EDIT_PROFILE = "/pages/editProfile.jsp";
	public static final String URL_PROFILE = "/pages/profile.jsp";
	public static final String URL_RESET_PW = "/pages/forgetPw.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/LoginServlet";
	public static final String SERVLET_URL_REGISTER = "/RegisterServlet";
	public static final String SERVLET_URL_LOGOUT = "/LogoutServlet";
	public static final String SERVLET_URL_HOME = "/LogoutServlet";
	public static final String SERVLET_URL_ = "/LogoutServlet";
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	

		// End: Normal Text
}
