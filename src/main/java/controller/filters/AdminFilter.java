package controller.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import controller.database.HelmetDbController;
import model.HelmetTableModel;
import util.StringUtil;

@WebFilter(urlPatterns = {"/pages/adminPanel.jsp", "/pages/adminProduct.jsp"})
public class AdminFilter implements Filter {
    private HelmetDbController dbController;

    public void init(FilterConfig filterConfig) throws ServletException {
        dbController = new HelmetDbController();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");
        String userRole = session != null ? (String) session.getAttribute(StringUtil.role) : null;

        // Check if the user is logged in and has the admin role
        if (isLoggedIn && "admin".equals(userRole)) {
            // User is authenticated and is an admin, allow access to admin panel
            if (httpReq.getRequestURI().endsWith("/pages/adminProduct.jsp")) {
                // Fetch all helmets from the database
                ArrayList<HelmetTableModel> helmets = dbController.getAllHelmets();
                request.setAttribute("helmets", helmets);
            }
            chain.doFilter(request, response);
        } else {
            // User is not authenticated as admin, redirect to login page
            System.out.println("Admin access denied!"); // Add logging
            httpResp.sendRedirect(httpReq.getContextPath() + "/pages/login.jsp");
        }
    }


    public void destroy() {
    }
}
