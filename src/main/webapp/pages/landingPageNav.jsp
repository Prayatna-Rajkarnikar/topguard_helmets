<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/landingPageNav.css" />

</head>
<body>
  <nav>
    <div class="nav-content">
      <div class="logo">
        <a href="#"><b>TopGuard Helmets</b></a>
      </div>
      <ul class="nav_items">
        <li><a href="#">Home</a></li>
        <li><a href="#">Helmets</a></li>
        <li><a href="#">About us</a></li>
      </ul>
      <div class="buttons">
        <a href="${pageContext.request.contextPath}/pages/login.jsp">
			<button class="login_button">Login</button>
		</a>

      </div>
    </div>
  </nav>
</body>
</html>