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
        String userName = request.getParameter(StringUtil.username);
        String password = request.getParameter(StringUtil.password);

        LoginUserModel loginUserModel = new LoginUserModel(userName, password);
        loginUserModel.setUserName(userName);
        loginUserModel.setPassword(password);

        // Call DBController to validate login credentials
        LoginStatus loginResult = dbController.getUserLoginInfo(loginUserModel);

        if (loginResult.getStatus() == 1) {
            if ("admin".equals(loginResult.getRole())) {
                // User is admin, redirect to admin dashboard
                HttpSession userSession = request.getSession();
                userSession.setAttribute("user_name", userName);
                userSession.setAttribute("role", "admin"); // Set admin role
                userSession.setMaxInactiveInterval(30 * 30);
                userSession.setAttribute("loggedIn", true);
                response.sendRedirect(request.getContextPath() + StringUtil.URL_ADMIN_PANEL);
            } else {
                // User is not admin, redirect to home page
                HttpSession userSession = request.getSession();
                userSession.setAttribute("user_name", userName);
                userSession.setMaxInactiveInterval(30 * 30);
                userSession.setAttribute("loggedIn", true);
                userSession.setAttribute("id", userSession.getId());
                response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_HOME);
            }
            request.setAttribute(StringUtil.MESSAGE_SUCCESS, StringUtil.MESSAGE_SUCCESS_LOGIN);
        } else if (loginResult.getStatus() == -1) {
            // Username not found
            request.setAttribute(StringUtil.MESSAGE_ERROR, "Create new account");
            request.setAttribute(StringUtil.username, userName);
            request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
        } else {
            // Internal server error
            System.out.println("No login");
            request.setAttribute(StringUtil.MESSAGE_ERROR, StringUtil.MESSAGE_ERROR_SERVER);
            request.setAttribute(StringUtil.username, userName);
            request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
        }
    }
}
