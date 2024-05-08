<%@ page import="util.StringUtil" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/login.css" />
</head>
<body>
  <div class="container">
    <div class="login_form" id="loginForm">
      <header>Login</header>
      <% String error = (String) request.getAttribute(StringUtil.MESSAGE_ERROR); %>
		<% if (error != null && !error.isEmpty()) { %>
   		<div style="color: red;"><%= error %></div>
		<% } %>
		
		<% String success = (String) request.getAttribute(StringUtil.MESSAGE_SUCCESS); %>
		<% if (success != null && !success.isEmpty()) { %>
   		<div style="color: green;"><%= success %></div>
		<% } %>
      <form action="/TopGuard_Helmets/LoginServlet" method="POST" >
      
        <input type="text" placeholder="Enter username" id="userName" name="<%=StringUtil.userName%>">
        <input type="password" placeholder="Enter your password" id="password" name="<%=StringUtil.password%>">
        
        <a href="${pageContext.request.contextPath}${StringUtil.URL_RESET_PW}" id="forgotPasswordLink">Forgot password?</a>
        <input type="submit" class="button" value="Login" id="loginButton">
      </form>
      <div class="signup" id="signupSection">
        <span class="signup">Don't have an account?
            <a href="${pageContext.request.contextPath}${StringUtil.PAGE_URL_REGISTER}" class="signup-link">Signup</a>
        </span>
    </div>
    
    </div>
  </div>
</body>
</html>
