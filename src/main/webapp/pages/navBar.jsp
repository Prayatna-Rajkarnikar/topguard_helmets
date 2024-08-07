 <%@page import="util.StringUtil"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/navBar.css" />
</head>
<body>


<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtil.userName);
    String contextPath = request.getContextPath();
    
%>

    <nav>
        <div class="nav-content">
            <div class="logo">
                <a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_HOME}"><b>TopGuard Helmets</b></a>
            </div>
            <ul class="nav_items">
                <li><a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_HOME}">Home</a></li>
                <li><a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_PRODUCTS}">Helmets</a></li>
                <li><a href="${pageContext.request.contextPath}${StringUtil.URL_PROFILE}">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_CONTACT}">Contact</a></li>
                <li><a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_ABOUT_US}">About us</a></li>
                <li>
                <form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + StringUtil.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + StringUtil.PAGE_URL_LOGIN);
                    }
                %>" method="post">
                    <input type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(StringUtil.LOGOUT);
                        } else {
                            out.print(StringUtil.LOGIN);
                        }
                    %>"/>
                </form>
            	</li>
            </ul>
        </div>
    </nav>
</body>
</html>
