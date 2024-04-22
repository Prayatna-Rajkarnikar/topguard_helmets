package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.HelmetDbController;
import model.LoginStatus;
import model.LoginUserModel;
import util.StringUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/LoginServlet",asyncSupported = true)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HelmetDbController dbController;
       
    public LoginServlet() {
        super();
        dbController = new HelmetDbController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(StringUtil.username);
        System.out.println("Username from user: " + username);
        String password = request.getParameter(StringUtil.password);
        System.out.println("Password from user: " + password);
        String role = request.getParameter(StringUtil.role);
        System.out.println("Role: " + role);
        
        LoginUserModel loginUserModel = new LoginUserModel(username, password, role);
        
        System.out.println("Password from user: " + loginUserModel);

        // Call DBController to validate login credentials
        LoginStatus loginResult = dbController.getUserLoginInfo(loginUserModel);        	
        System.out.println("Password from database: " + loginResult);

        if (loginResult.getStatus() == 1) {
        	HttpSession userSession = request.getSession();
			userSession.setAttribute("user_name", username);
			userSession.setMaxInactiveInterval(30*30);
			
			Cookie userCookie= new Cookie("user", username);
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			
			if ("admin".equals(loginResult.getRole())) {
                // User is admin, redirect to admin dashboard
                response.sendRedirect(request.getContextPath() +"/pages/register.jsp");
			 } else {
	                // User is not admin, redirect to home page
	                response.sendRedirect(request.getContextPath() +"/pages/welcome.jsp");
	            }
        	
            request.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_LOGIN);
        } else if (loginResult.getStatus() == -1) {
            // Username not found
        	 response.sendRedirect(request.getContextPath() +"login.jsp?error=1");
        } else {
            // Internal server error
            request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_SERVER);
            request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
        }
    }
}
