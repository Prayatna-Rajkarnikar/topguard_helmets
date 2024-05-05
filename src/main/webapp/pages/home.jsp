<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="util.StringUtil" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/home.css" />
</head>
<body>
<jsp:include page="navBar.jsp"/>
	<%
		String userSession = (String) session.getAttribute(StringUtil.username);
		out.print("Session"+userSession);
	 	String idSession = (String) session.getAttribute("id");
	%>
	<div class="welcome">
		<h1>Hello <%=userSession %>. Welcome to our page!</h1>
	</div>
	<section class="home_slider">
        <div class="content">
            <h1>Protect Your Passion: TopGuard Helmets</h1>
            <p>Safety, Style, and Confidence on Every Ride.</p>
            <div class="shopNow_btn">
                <button>Shop Now</button>
            </div>
        </div>
        <div class="img_helmet">
            <img src="${pageContext.request.contextPath}/resources/images/home1.jpg" alt="">
        </div>
    </section>

    <section class="helmets">
        <div class="helmet-card">
            <img src="${pageContext.request.contextPath}/resources/images/helemet1.jpg" alt="Product 1">
            <h2>Bee Safe Helmet</h2>
            <p>Rs. 4000</p>
        </div>
        <div class="helmet-card">
            <img src="${pageContext.request.contextPath}/resources/images/helmet2.jpg" alt="Product 2">
            <h2>Cycling Helmet</h2>
            <p>Rs. 2000</p> 
        </div>
        <div class="helmet-card">
            <img src="${pageContext.request.contextPath}/resources/images/helmet3.jpg" alt="Product 3">
            <h2>Full Face Helmet</h2>
            <p>Rs. 9000</p>
        </div>
        <div class="helmet-card">
            <img src="${pageContext.request.contextPath}/resources/images/helmet4.jpg" alt="Product 4">
            <h2>Dirt Helmet</h2>
            <p>Rs. 6000</p> 
        </div>
    </section>
</body>
</html>