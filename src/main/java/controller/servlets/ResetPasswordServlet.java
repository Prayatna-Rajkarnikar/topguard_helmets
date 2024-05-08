package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import util.StringUtil;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HelmetDbController dbController;
       
    public ResetPasswordServlet() {
        super();
        dbController = new HelmetDbController();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter(StringUtil.userName);
        String new_password = request.getParameter(StringUtil.newPassword);
        String confirmNewPassword = request.getParameter(StringUtil.confirmNewPassword);
        
        if (!new_password.equals(confirmNewPassword)) {
            // New password and confirm new password do not match
            // Set an error message and forward the request back to the reset password page
        	request.setAttribute("errorMessage",StringUtil.NEWPWD_MISMATCHED_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
        }

        // Call the updateUserPasswordIfValid method from GadgetDbController
        int result = dbController.updateUserPwdIfValid(userName, new_password); 
        
        // Pass only the new password
        if (result == 1) {
            // Password updated successfully
        	System.out.println("Password updated successfully for user: " + userName);
        	request.setAttribute("successMessage",StringUtil.PWD_UPDATED_MESSAGE);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;   
        } else if (result == -1) {
            // Username not found
        	request.setAttribute("errorMessage",StringUtil.USERNAME_NOT_FOUND);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
        } else {
        	request.setAttribute("errorMessage",StringUtil.SERVER_ERROR);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);;
        }
    }

}


