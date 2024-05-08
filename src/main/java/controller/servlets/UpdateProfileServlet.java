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
import util.StringUtil;

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
        String user_Name = request.getParameter(StringUtil.userName);
        String fullName = request.getParameter(StringUtil.userFullName);
        String email = request.getParameter(StringUtil.email);
        String contact_number = request.getParameter(StringUtil.contactNumber);
        String address = request.getParameter(StringUtil.address);

        // Update the database with the new values
        HelmetUserModel updatedHelmetUser = new HelmetUserModel();
        updatedHelmetUser.setUserName(user_Name);
        updatedHelmetUser.setFullName(fullName);
        updatedHelmetUser.setEmail(email);
        updatedHelmetUser.setPhoneNumber(contact_number);
        updatedHelmetUser.setAddress(address);

        // Update the user profile in the database
        int result = dbController.updateProfile(updatedHelmetUser);

        // Redirect back to the original JSP page with success or error message
        if (result == 1) {
            response.sendRedirect(request.getContextPath() + StringUtil.URL_PROFILE);
        } else {
            response.sendRedirect(request.getContextPath() + StringUtil.URL_PROFILE);
        }
    }
}

