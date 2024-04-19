<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="util.StringUtil" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/welcome.css" />
</head>
<body>
	<%
		String userSession = (String) session.getAttribute(StringUtil.username);

	%>
	<div class="welcome-container">
		<h1>Hello <%=userSession %>. Welcome to our page!</h1>
		<p>Session user name: <%=userSession %></p>
		<a href="${pageContext.request.contextPath}/index.jsp">
			<button class="home-button">Continue to Home Page</button>
		</a>
	</div>
</body>
</html>