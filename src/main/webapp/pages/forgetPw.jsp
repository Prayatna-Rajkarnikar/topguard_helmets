<%@ page import = "util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/forgetPw.css" />

</head>
<body>
<div class="resetPw_container">
  <div class="resetPw_form">
    <header>Reset Password</header>
     <% String error = (String) request.getAttribute(StringUtil.MESSAGE_ERROR); %>
		<% if (error != null && !error.isEmpty()) { %>
   		<div style="color: red;"><%= error %></div>
		<% } %>
		
		<% String success = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS); %>
		<% if (success != null && !success.isEmpty()) { %>
   		<div style="color: green;"><%= success %></div>
		<% } %>
<form action="/TopGuard_Helmets/ResetPasswordServlet" method="POST" >      
		<input type="text" id= "userName" name= "<%=StringUtil.userName%>" placeholder="Enter username" required>
      <input type="password" id="new_password" name= "<%=StringUtil.newPassword%>" placeholder="Enter new password" required>
      <input type="password" id="confirm_password" name= "<%=StringUtil.confirmNewPassword%>" placeholder="Confirm new password" required>
      <input type="submit" class="button" id="reset-button" value="Reset Password">
    </form>
    <a href="login.jsp" id="login-link">Login</a>
  </div>
</div>
</body>
</html>