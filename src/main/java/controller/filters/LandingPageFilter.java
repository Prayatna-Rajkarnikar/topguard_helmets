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

@WebFilter(urlPatterns = {"/pages/profile.jsp", "/pages/contactUs.jsp", "/pages/home.jsp"})
public class LandingPageFilter implements Filter {
    

    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession(false);

        boolean isLoggedIn = session != null && session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn");
        String username = session != null ? (String) session.getAttribute(StringUtil.userName) : null;

        //System.out.println("Role: " + userRole); // Add logging

        // Check if the user is logged in 
        if (isLoggedIn) {
            // User is authenticated allow access to pages
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login page
            System.out.println("Access denied!"); // Add logging
            httpResp.sendRedirect(httpReq.getContextPath() + "/pages/login.jsp");
        }
    }


    public void destroy() {
    }
}
