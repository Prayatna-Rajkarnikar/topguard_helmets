<%@page import="util.StringUtil"%>
<%@page import="model.HelmetUserModel"%>
<%@ page import="controller.database.HelmetDbController" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TopGuard Helmets || Edit Profile</title>
    <style>
    
    
 * {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Roboto', sans-serif;
  
}

.body {
	background: #3A4A4D;
}

.container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: 15px;
  margin-top: 40px;
  max-width: 430px;
  width: 100%;
  background: #fff;
  border-radius: 7px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
  padding: 1rem;
}

.container h1 {
  font-size: 2rem;
  font-weight: 500;
  text-align: center;
  margin-bottom: 1.5rem;
}

.container p {
  font-size: 17px;
  margin-bottom: 1.3rem;
}

.container a {
  font-size: 16px;
  color: #3A4A4D;
  margin: 5px;
  text-decoration: none;
}

.container a:hover {
  text-decoration: underline;
}

.container button {
  height: 60px;
  width: 100%;
  padding: 0 15px;
  font-size: 1.2rem;
  font-weight: 500;
  letter-spacing: 1px;
  margin-top: 1.2rem;
  background-color: #3A4A4D;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.4s;
}

.container button:hover {
  background-color: #7f9fa4;
}

.update_profile {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
  padding: 2rem;
  width: 100%;
  max-width: 400px;
}

.update_profile h1 {
  font-size: 2rem;
  font-weight: 500;
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.update_form_group {
  margin-bottom: 1.0rem;
}

.update_form_group label {
  display: block;
  font-size: 1.1rem;
  color: #3A4A4D;
}

.update_form_group input {
  height: 50px;
  width: 100%;
  padding: 0 15px;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 6px;
  outline: none;
}

.update_form_group input:focus {
  box-shadow: 0 1px 0 rgba(0, 0, 0, 0.2);
}

.update_form_group input[type="submit"] {
  color: #fff;
  background: #3A4A4D;
  font-size: 1.2rem;
  font-weight: 500;
  letter-spacing: 1px;
  padding: 12px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: 0.4s;
}

.update_form_group input[type="submit"]:hover {
  background: #7f9fa4;
}
    
    </style>
     <jsp:include page="navBar.jsp"/>
</head>
<body class = body>
   <section class = "o_form">
    <div class="container">
        <div class="box">
            <h1>Edit Profile</h1>
            <form action="${pageContext.request.contextPath}/UpdateProfileServlet" method="post">
            <div class="update_form_group">
                    <label for="userName">User Name:</label>
        			<input type="text" id="userName" name="<%=StringUtil.userName%>" value="${userName}" readonly>
                </div>
                <div class="update_form_group">
                    <label for="userFullName">Full Name:</label>
        			<input type="text" id="userFullName" name="<%=StringUtil.userFullName%>" value="${userFullName}" required>
                </div>
                <div class="update_form_group">
                    <label for="userEmail">Email:</label>
        			<input type="email" id="userEmail" name="<%=StringUtil.email%>" value="${userEmail}" required>
                </div>
                <div class="update_form_group">
                    <label for="contactNumber">Phone Number:</label>
        			<input type="text" id="contactNumber" name="<%=StringUtil.contactNumber%>" value="${contactNumber}" required>
                </div>
                <div class="update_form_group">
                    <label for="address">Address:</label>
        			<input type="text" id="address" name="<%=StringUtil.address%>" value="${address}" required>
                </div>
                <div class="updateButton">
                    <button type="submit">Update</button>
                </div>
            </form>
            </div>
        </div>
        </section>
</body>
</html>
