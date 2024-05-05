<%@page import="model.UpdateUserModel"%>
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
    String loggedInUsername = (String) session.getAttribute(StringUtil.username);
    if (loggedInUsername == null || loggedInUsername.isEmpty()) {
        // Redirect to login page if not logged in
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return; // Stop further execution
    }

    // Initialize the database controller
    HelmetDbController dbController = new HelmetDbController();
    
    // Retrieve user profile information from the database based on the logged-in username
    HelmetUserModel userProfile = dbController.getUserProfile(loggedInUsername);

    // Check if user profile is null
    if (userProfile == null) {
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
                <img src="${pageContext.request.contextPath}/resources/user/${userProfile.userImageUrl}" alt="User Image">
            </div>
            <div class="user-details">
                <p><strong>Username:</strong> <%= userProfile.getUserName() %></p>
                <p><strong>Full Name:</strong> <%= userProfile.getFullName() %></p>
                <p><strong>Email:</strong> <%= userProfile.getEmail() %></p>
                <p><strong>Phone Number:</strong> <%= userProfile.getPhoneNumber() %></p>
                <p><strong>DOB:</strong> <%= userProfile.getDob() %></p>
                <p><strong>Address:</strong> <%= userProfile.getAddress() %></p>
            </div>
            <form action="${pageContext.request.contextPath}/EditProfileServlet" method="post">
                <input type="hidden" id="userName" name="userName" value="<%= userProfile.getUserName() %>">
                <input type="hidden" id="userFullName" name="userFullName" value="<%= userProfile.getFullName() %>">
                <input type="hidden" id="userEmail" name="userEmail" value="<%= userProfile.getEmail() %>">
                <input type="hidden" id="contactNumber" name="contactNumber" value="<%= userProfile.getPhoneNumber() %>">
                <input type="hidden" id="address" name="address" value="<%= userProfile.getAddress() %>">
                <div class="updateButton">
                    <button type="submit">Update</button>
                </div>
            </form>

            <a href="forgetPw.jsp" id="forgotPasswordLink">Reset Password</a>
        </div>
    </div>
</body>
</html>
