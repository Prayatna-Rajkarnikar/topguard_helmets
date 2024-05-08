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
import model.HelmetUserModel;
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
		String userName = request.getParameter(StringUtil.userName);
		String fullName = request.getParameter(StringUtil.userFullName);
		String email = request.getParameter(StringUtil.email);
		String phoneNumber = request.getParameter(StringUtil.contactNumber);
		String dobString = request.getParameter(StringUtil.dobString);

	        LocalDate dob;

	        // Check if dobString is null or empty
	        if (dobString == null || dobString.isEmpty()) {
	        	request.setAttribute("errorMessage",StringUtil.INVALID_DATE_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
				return;
	        }

	        // Define the date format using DateTimeFormatter
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        try {
	            // Parse the dobString into a LocalDate object using the specified formatter
	            dob = LocalDate.parse(dobString, formatter);
	        } catch (DateTimeParseException e) {
	        	request.setAttribute("errorMessage",StringUtil.INVALID_DATE_ERROR_MESSAGE);
				request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
				return;
	        }
		String address = request.getParameter(StringUtil.address);
		String password = request.getParameter(StringUtil.password);
		String gender = request.getParameter(StringUtil.gender);
		Part user_image = request.getPart("user_image");

		if (!isValidName(fullName)) {
			request.setAttribute("errorMessage",StringUtil.INVALID_FULLNAME_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		
		if (dbController.isUsernameExists(userName)) {
			request.setAttribute("errorMessage",StringUtil.USERNAME_EXIST_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
        }
              
		if (userName.length() < 6) {
			request.setAttribute("errorMessage",StringUtil.USERNAME_LENGTH_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!userName.matches("^[a-zA-Z0-9]{6,}$")) {
			request.setAttribute("errorMessage",StringUtil.SYMBOL_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (dob.isAfter(LocalDate.now())) {
			request.setAttribute("errorMessage",StringUtil.INVALID_DATE_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		
		if (dbController.isEmailExists(email)) {
			request.setAttribute("errorMessage",StringUtil.EMAIL_EXIST_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
        }

        // Check if phone number already exists
        if (dbController.isPhoneNumberExists(phoneNumber)) {
        	request.setAttribute("errorMessage",StringUtil.CONTACT_EXIST_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
        }

		if (phoneNumber.length() != 14) {
			request.setAttribute("errorMessage",StringUtil.CONTACT_LENGTH_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (!phoneNumber.startsWith("+")) {
			request.setAttribute("errorMessage",StringUtil.CONTACT_STARTING_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}

		if (password.length() < 6) {
			request.setAttribute("errorMessage",StringUtil.PWD_LENGTH_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		
		if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{6,}$")) {
			request.setAttribute("errorMessage",StringUtil.PWD_BUILD_ERROR_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}		
		
		String retypePassword = request.getParameter("retypePassword");
		if (!password.equals(retypePassword)) { 
			request.setAttribute("errorMessage",StringUtil.PWD_MISMATCHED_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		}
		   
		HelmetUserModel user = new HelmetUserModel(userName, fullName, email, phoneNumber, dob, address, retypePassword, gender, user_image, "user");

		String savePath = StringUtil.IMAGE_DIR_SAVE_PATH;
        String fileName = user.getUserImageUrl();
        if (!fileName.isEmpty() && fileName != null)
            user_image.write(savePath + fileName);
		
		int result = dbController.addNewUser(user);
		
		if (result == 1) {
			request.setAttribute("successMessage",StringUtil.REGISTER_SUCCESS_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
		} else if (result == 0) {
			request.setAttribute("errorMessage",StringUtil.REGISTRATION_FAILED_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_REGISTER).forward(request, response);
			return;
		} else if (result == -1) {
			// Error occurred
			request.setAttribute("errorMessage",StringUtil.SERVER_ERROR);
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
