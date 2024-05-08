<%@ page import="controller.database.HelmetDbController" %>
<%@ page import="model.LoginUserModel" %>
<%@ page import="model.HelmetUserModel" %>
<%@ page import = "util.StringUtil" %>
<%@ page import="model.LoginStatus" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Check if the user is logged in
    // Assuming you have a session attribute named "loggedInUser" that stores the logged-in user's username
    String loggedInUsername = (String) session.getAttribute(StringUtil.userName);
    if (loggedInUsername == null || loggedInUsername.isEmpty()) {
        // Redirect to login page if not logged in
        response.sendRedirect(request.getContextPath() + StringUtil.URL_LOGIN);
        return; // Stop further execution
    }

    // Initialize the database controller
    HelmetDbController dbController = new HelmetDbController();
    
    // Retrieve user profile information from the database based on the logged-in username
    HelmetUserModel profile = dbController.obtainUserProfile(loggedInUsername);

    // Check if user profile is null
    if (profile == null) {
        // Handle case where user profile is not found
        // For example, display an error message
        out.println("User profile not found");
        return; // Stop further execution
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/profile.css" />
</head>
<body>
    <jsp:include page="navBar.jsp"/>

    <div class="container">
        <div class="box">
            <h1>Profile</h1>
            <div class="image">
            	<img src="${pageContext.request.contextPath}/resources/user/account.png" alt="User Image">
                <%-- <img src="${pageContext.request.contextPath}/resources/user/${userProfile.getImageUrl}" alt="User Image"> --%>
            </div>
            <div class="user-details">
                <p><strong>Username:</strong> <%= profile.getUserName() %></p>
                <p><strong>Full Name:</strong> <%= profile.getFullName() %></p>
                <p><strong>Email:</strong> <%= profile.getEmail() %></p>
                <p><strong>Phone Number:</strong> <%= profile.getPhoneNumber() %></p>
                <p><strong>DOB:</strong> <%= profile.getDob() %></p>
                <p><strong>Address:</strong> <%= profile.getAddress() %></p>
            </div>
            <form action="${pageContext.request.contextPath}/EditProfileServlet" method="post">
                <input type="hidden" id="userName" name="<%=StringUtil.userName%>" value="<%= profile.getUserName() %>">
                <input type="hidden" id="userFullName" name="<%=StringUtil.userFullName%>" value="<%= profile.getFullName() %>">
                <input type="hidden" id="userEmail" name="<%=StringUtil.email%>" value="<%= profile.getEmail() %>">
                <input type="hidden" id="contactNumber" name="<%=StringUtil.contactNumber%>" value="<%= profile.getPhoneNumber() %>">
                <input type="hidden" id="address" name="<%=StringUtil.address%>" value="<%= profile.getAddress() %>">
                <div class="updateButton">
                    <button type="submit">Update</button>
                </div>
            </form>

            <a href="${pageContext.request.contextPath}${StringUtil.URL_RESET_PW}" id="forgotPasswordLink">Reset Password</a>
        </div>
    </div>
</body>
</html>
