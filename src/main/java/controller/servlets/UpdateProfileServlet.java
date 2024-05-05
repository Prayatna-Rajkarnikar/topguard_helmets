package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.HelmetDbController;
import model.HelmetTableModel;
import model.HelmetUserModel;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HelmetDbController dbController;
    
    public UpdateProfileServlet() {
        super();
        this.dbController = new HelmetDbController();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve updated values from the form
        String user_Name = request.getParameter("userName");
        String fullName = request.getParameter("userFullName");
        String email = request.getParameter("userEmail");
        String contact_number = request.getParameter("contactNumber");
        String address = request.getParameter("address");

        // Update the database with the new values
        HelmetUserModel updatedUser = new HelmetUserModel();
        updatedUser.setUserName(user_Name);
        updatedUser.setFullName(fullName);
        updatedUser.setEmail(email);
        updatedUser.setPhoneNumber(contact_number);
        updatedUser.setAddress(address);

        // Update the user profile in the database
        int result = dbController.updateProfile(updatedUser);

        // Redirect back to the original JSP page with success or error message
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/pages/profile.jsp?error=true");
        }
    }
}

