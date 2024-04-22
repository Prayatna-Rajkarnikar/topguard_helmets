<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/navBar.css" />
</head>
<body>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute("username");
    String contextPath = request.getContextPath();
%>

    <nav>
        <div class="nav-content">
            <div class="logo">
                <a href="#"><b>TopGuard Helmets</b></a>
            </div>
            <ul class="nav_items">
                <li><a href="#">Home</a></li>
                <li><a href="#">Helmets</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">About us</a></li>
                  <li>
                <form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + "/LogoutServlet");
                    } else {
                        out.print(contextPath + "/pages/login.jsp");
                    }
                %>" method="post">
                    <input type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print("Logout");
                        } else {
                            out.print("Login");
                        }
                    %>"/>
                </form>
            </li>
            </ul>
        </div>
    </nav>
</body>
</html>
