package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.HelmetDbController;
import model.LoginStatus;
import model.LoginUserModel;
import util.StringUtil;

@WebServlet(urlPatterns = "/LoginServlet", asyncSupported = true)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HelmetDbController dbController;

    public LoginServlet() {
        super();
        dbController = new HelmetDbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter(StringUtil.userName);
        String password = request.getParameter(StringUtil.password);

        LoginUserModel loginUserModel = new LoginUserModel(userName, password);
        loginUserModel.setUserName(userName);
        loginUserModel.setPassword(password);

        LoginStatus loginResult = dbController.getUserLoginDetails(loginUserModel);

        if (loginResult.getStatus() == 1) {
            if ("admin".equals(loginResult.getRole())) {
                // User is admin, redirect to admin dashboard
                HttpSession userSession = request.getSession();
                userSession.setAttribute("userName", userName);
                userSession.setAttribute("role", "admin"); // Set admin role
                userSession.setMaxInactiveInterval(30 * 30);
                userSession.setAttribute("loggedIn", true);
                response.sendRedirect(request.getContextPath() + StringUtil.URL_ADMIN_PANEL);
            } else {
                // User is not admin, redirect to home page
                HttpSession userSession = request.getSession();
                userSession.setAttribute("userName", userName);
                userSession.setMaxInactiveInterval(30 * 30);
                userSession.setAttribute("loggedIn", true);
                userSession.setAttribute("id", userSession.getId());
                response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_HOME);
            }
        } else if (loginResult.getStatus() == -1) {
            // Username not found
        	request.setAttribute("errorMessage",StringUtil.USERNAME_NOT_FOUND);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
			return;
         
	    } else if (loginResult.getStatus() == -2) {
	        // Incorrect password
	        request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.WRONG_PASSWORD);
	        request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
	        return;
	    }
        else {
        	request.setAttribute("errorMessage",StringUtil.SERVER_ERROR);
			request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);;
        }
    }
}
