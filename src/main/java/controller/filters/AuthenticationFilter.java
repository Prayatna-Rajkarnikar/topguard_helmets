package controller.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;

@WebFilter(urlPatterns = {"/pages/adminPanel.jsp","/pages/adminProduct.jsp"})
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
  
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");
        String username = session != null ? (String) session.getAttribute(StringUtil.username) : null;
        String userRole = session != null ? (String) session.getAttribute(StringUtil.role) : null;

        System.out.println("Username: " + username); // Add logging
        System.out.println("Role: " + userRole); // Add logging

        // Check if the user is logged in and has the admin role
        if (isLoggedIn && "admin".equals(userRole)) {
            // User is authenticated and is an admin, allow access to admin panel
            chain.doFilter(request, response);
        } else {
            // User is not authenticated as admin, redirect to login page
            System.out.println("Admin access denied!"); // Add logging
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/login.jsp");
        }
    }


    public void destroy() {
    }
}
