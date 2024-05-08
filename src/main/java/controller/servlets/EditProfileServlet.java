package controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.HelmetDbController;
import model.HelmetUserModel;
import util.StringUtil;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProfileServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_Name = request.getParameter(StringUtil.userName);
        String fullName = request.getParameter(StringUtil.userFullName);
        String email = request.getParameter(StringUtil.email);
        String contact_number = request.getParameter(StringUtil.contactNumber);
        String address = request.getParameter(StringUtil.address);

        // Set attributes to forward to the JSP
        request.setAttribute(StringUtil.userName, user_Name);
        request.setAttribute(StringUtil.userFullName, fullName);
        request.setAttribute(StringUtil.email, email);
        request.setAttribute(StringUtil.contactNumber, contact_number);
        request.setAttribute(StringUtil.address, address);

        // Forward to the JSP for editing
        RequestDispatcher dispatcher = request.getRequestDispatcher(StringUtil.URL_EDIT_PROFILE);
        dispatcher.forward(request, response);
    }
}
