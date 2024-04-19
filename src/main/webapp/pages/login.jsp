<%@ page import="util.StringUtil" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
      <form action="/TopGuard_Helmets/LoginServlet" method="POST" >
      
        <input type="text" placeholder="Enter username" id="usernameInput" name="<%=StringUtil.username%>">
        <input type="password" placeholder="Enter your password" id="passwordInput" name="<%=StringUtil.password%>">
        <a href="forgetPw.jsp" id="forgotPasswordLink">Forgot password?</a>
        <button type="submit" class="button" value="Login" id="loginButton">Login</button>
      </form>
      <div class="signup" id="signupSection">
        <span class="signup">Don't have an account?
            <a href="register.jsp?action=signup" class="signup-link" name="signupLink">Signup</a>
        </span>
    </div>
    
    </div>
  </div>
</body>
</html>
