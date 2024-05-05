package util;

import java.io.File;

public class StringUtil {
	
	public static final String IMAGE_DIR_USER = "Users\\HP\\eclipse-workspace\\TopGuard_Helmets\\src\\main\\webapp\\resources\\user\\";
	public static final String IMAGE_DIR_HELMET = "Users\\HP\\eclipse-workspace\\TopGuard_Helmets\\src\\main\\webapp\\resources\\helmets\\";

	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR_USER;
	public static final String IMAGE_DIR_SAVE_PATH_HELMET = "C:" + File.separator + IMAGE_DIR_HELMET;
	
	public static final String INSERT_NEW_USER = "INSERT INTO user "
            + "(userName, user_fullName, email, contact_number, dob, address, password, gender, user_image, role)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	
    public static final String GET_USER_INFO = "SELECT * FROM user WHERE userName = ?";
    
    public static final String username = "user_name";
    public static final String fullName = "full_Name";
    public static final String email = "userEmail";
    public static final String phoneNumber = "phone_Number";
    public static final String dobString = "birthday";
    public static final String address = "user_address";
    public static final String password = "password";
    public static final String retype_password = "retypePassword";
    public static final String gender = "gender";
    public static final String role = "role";

    
    public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
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
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String URL_LOGIN = "/pages/login.jsp";
	public static final String URL_LANDINGPAGE = "/pages/landingPage.jsp";
	public static final String URL_ADMIN_PANEL = "/pages/adminPanel.jsp";
	public static final String URL_ADMIN_PRODUCT = "/pages/adminProduct.jsp";
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
	public static final String DELETE_ID= "deleteId";

		// End: Normal Text
}
