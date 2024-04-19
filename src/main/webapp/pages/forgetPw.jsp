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
<form action="/TopGuard_Helmets/ResetPasswordServlet" method="POST" >      
		<input type="text" id= "username" name= "username" placeholder="Enter username">
      <input type="password" id="new_password" name= "new_password" placeholder="Enter new password">
      <input type="password" id="confirm_password" name= "confirm_password" placeholder="Confirm new password">
      <input type="submit" class="button" id="reset-button" value="Reset Password">
    </form>
    <a href="#" id="login-link">Login</a>
  </div>
</div>
</body>
</html>