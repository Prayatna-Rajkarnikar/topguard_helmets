package controller.servlets;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.database.HelmetDbController;
import model.UserModel;
import util.StringUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelmetDbController dbController;

	public RegisterServlet() {
		super();
		dbController = new HelmetDbController();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// You can process the GET request here
		response.getWriter().println("GET request received");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_name = request.getParameter(StringUtil.username);
		String full_name = request.getParameter(StringUtil.fullName);
		String email = request.getParameter(StringUtil.email);
		String phone_number = request.getParameter(StringUtil.phoneNumber);
		String dobString = request.getParameter(StringUtil.dobString);
	     // Initialize dob variable
	     // Initialize dob variable
	        LocalDate dob;

	        // Check if dobString is null or empty
	        if (dobString == null || dobString.isEmpty()) {
	            System.out.println("Date of birth is null or empty");
	            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
	            return; // Exit the method to prevent further processing
	        }

	        // Define the date format using DateTimeFormatter
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        try {
	            // Parse the dobString into a LocalDate object using the specified formatter
	            dob = LocalDate.parse(dobString, formatter);
	        } catch (DateTimeParseException e) {
	            // Handle invalid date format
	            System.out.println("Error parsing date: " + e.getMessage());
	            response.sendRedirect(request.getContextPath() + "/pages/register.html?error=invalid_date_format");
	            return; // Exit the method to prevent further processing
	        }
		String address = request.getParameter(StringUtil.address);
		String password = request.getParameter(StringUtil.password);
		String gender = request.getParameter(StringUtil.gender);
		Part user_image = request.getPart("user_image");

		if (!isValidName(full_name)) {
			String errorMessage = "Invalid Full name. Please don't enter symbols and numerical value.";
			request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
//        
		if (user_name.length() < 6) {
			String errorMessage = "Invalid User name. Please enter more than 6 characters";
			request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!user_name.matches("^[a-zA-Z0-9]{6,}$")) {
			String errorMessage = "Invalid User name. Please don't enter symbols.";
			request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (dob.isAfter(LocalDate.now())) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, "Invalid birthday date.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (phone_number.length() != 14) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, "Invalid number. Phone Number must be of 14 characters.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!phone_number.startsWith("+")) {
			request.setAttribute(StringUtil.MESSAGE_ERROR, "Invalid number. Phone Number must start with + sign.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}$")) {
			request.setAttribute(StringUtil.MESSAGE_ERROR,
					"Invalid password. Password must contain at least one uppercase letter, one number, and one special character.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (password.length() < 6) {
			String errorMessage = "Invalid Password. Please enter more than 6 characters";
			request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
//		
		
		 String retypePassword = request.getParameter("retypePassword");
		if (!password.equals(retypePassword)) { 
			String errorMessage = "Password and Retype Password do not match.";
		 request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
		 request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request,
		 response); return; }
		 
        if (dbController.isUsernameExists(user_name)) {
            String errorMessage = "Username already exists. Please choose a different username.";
            request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }
        
     // Check if email already exists
        if (dbController.isEmailExists(email)) {
            String errorMessage = "Email already exists. Please use a different email address.";
            request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

        // Check if phone number already exists
        if (dbController.isPhoneNumberExists(phone_number)) {
            String errorMessage = "Phone number already exists. Please use a different phone number.";
            request.setAttribute(StringUtil.MESSAGE_ERROR, errorMessage);
            request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
            return;
        }

		UserModel user = new UserModel(user_name, full_name, email, phone_number, dob, address, retypePassword, gender, user_image, "user");

		String savePath = StringUtil.IMAGE_DIR_SAVE_PATH;
        String fileName = user.getUserImageUrl();
        if (!fileName.isEmpty() && fileName != null)
            user_image.write(savePath + fileName);
		
		int result = dbController.addNewUser(user);
		
		if (result == 1) {
			request.setAttribute(StringUtil.MESSAGE_SUCCESS_REGISTER, StringUtil.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_LOGIN);
		} else if (result == 0) {
			// No rows affected
			request.setAttribute(StringUtil.MESSAGE_ERROR, "Registration failed. Please try again.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
		} else if (result == -1) {
			// Error occurred
			request.setAttribute(StringUtil.MESSAGE_ERROR, "An unexpected error occurred. Please try again later.");
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
		}
	}

	private boolean isValidName(String name) {
		for (char c : name.toCharArray()) {
			if (!Character.isLetter(c) && c != ' ') {
				return false;
			}
		}
		return true;
	}
}
