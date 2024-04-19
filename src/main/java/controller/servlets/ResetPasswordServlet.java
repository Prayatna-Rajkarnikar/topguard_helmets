package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("Username: " + username);
        String new_password = request.getParameter("new_password");
        System.out.println("New Password: " + new_password);

        // Call the updateUserPasswordIfValid method from GadgetDbController
        int result = dbController.updateUserPasswordIfValid(username, new_password); 
        // Pass only the new password

        System.out.println("Result: " + result);
        if (result == 1) {
            // Password updated successfully
            System.out.println("Password updated successfully for user: " + username);
            // Redirect to a success page or show a success message
        } else if (result == -2) {
            // Incorrect old password
        	System.out.println("F Password");
            // Redirect to a page showing an error message
        } else if (result == -1) {
            // Username not found
        	System.out.println("F Username");

            // Redirect to a page showing an error message
        } else {
            // Error updating password
        	System.out.println("F Update");

            // Redirect to a page showing an error message
        }
    }

}


