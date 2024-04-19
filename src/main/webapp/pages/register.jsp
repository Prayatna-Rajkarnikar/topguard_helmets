<%@ page import="util.StringUtil" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/register.css" />
</head>
<body>
<div class="container">
    <div class="card">
        <h1>Sign Up</h1>

        <% String error = (String) request.getAttribute(StringUtil.MESSAGE_ERROR); %>
		<% if (error != null && !error.isEmpty()) { %>
   		<div style="color: red;"><%= error %></div>
		<% } %>
		
        <form action="/TopGuard_Helmets/RegisterServlet" method="POST" enctype="multipart/form-data">
            <div class="form-row">
                <div class="col">
                    <label for="full_Name">Full Name:</label>
                    <input type="text" id="full_Name" name="<%=StringUtil.fullName%>" required>
                </div>
                <div class="col">
                    <label for="username">Username:</label>
                    <input type="text" id="user_name" name="<%=StringUtil.username%>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col">
                    <label for="address">Address:</label>
                    <input type="text" id="user_address" name="<%=StringUtil.address%>" required>
                </div>
                <div class="col">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="birthday" name="<%=StringUtil.dobString%>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="<%=StringUtil.gender%>" required>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>
                <div class="col">
                    <label for="email">Email:</label>
                    <input type="email" id="userEmail" name="<%=StringUtil.email%>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col">
                    <label for="phone">Phone Number:</label>
                    <input type="tel" id="phone_Number" name="<%=StringUtil.phoneNumber%>" required>
                </div>
                <div class="col">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="<%=StringUtil.password%>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col">
                    <label for="retype-password">Retype Password:</label>
                    <input type="password" id="retypePassword" name="<%=StringUtil.retype_password%>" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col">
                  <label for="user_image">User Image:</label>
                  <input type="file" id="user_image" name="user_image" accept="image/png, image/jpeg, image/jpg" class="custom-file-input" required>
                </div>
              </div>
            <button type="submit">Register Now</button>
        </form>
    </div>
</div>
</body>
</html>
