<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/stylesheets/aboutUs.css">
</head>
<body>
<nav>
<jsp:include page = "navBar.jsp"/>
</nav>
  <section class="about-us">
    <h2>About Us</h2>
    <div class="about">
      <img src="${pageContext.request.contextPath}/resources/images/helmet4.jpg" class="pic">
      <div class="about_helmet">
        <p>Welcome to TopGuard Helmets, the premier source for top-tier head protection. 
          We take pride in offering a diverse selection of helmets that correspond to our customers' specific needs and preferences.
           We have you covered with everything from stylish motorcycle helmets to sturdy bicycle helmets. 
           Our dedication to safety is unwavering; every helmet in our inventory undergoes extensive testing to ensure that it meets or exceeds the most stringent safety requirements. 
           But our dedication does not end there. At TopGuard Helmets, we are also committed to sustainability. 
        </p>
      </div>
    </div>
  </section>
</body>
</html>