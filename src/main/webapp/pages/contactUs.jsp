<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/contactUs.css" />

</head>
<body>
<jsp:include page="navBar.jsp"/>
<div class="contact_container">
    <div class="content">
      <div class="image-box">
        <img src="images/helmet3.jpg" alt="">
      </div>
      <form action="#">
        <div class="topic">Get in touch</div>
        <div class="input-box">
          <input type="text" required>
          <label>Enter your name</label>
        </div>
        <div class="input-box">
          <input type="text" required>
          <label>Enter your email</label>
        </div>
        <div class="message-box">
          <label>We'll get in touch with you</label>
        </div>
        <div class="input-box">
          <input type="submit" value="Send">
        </div>
      </form>
    </div>
  </div>

</body>
</html>