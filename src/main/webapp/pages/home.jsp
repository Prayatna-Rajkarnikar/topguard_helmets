<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="util.StringUtil" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/home.css" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
</head>
<body>
<jsp:include page="navBar.jsp"/>
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
	<sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/topguard_helmets" user="root" password="" />
            <sql:query dataSource="${dataSource}" var="helmets">
                SELECT * FROM helmet;
            </sql:query>
           <c:forEach var="helmet" items="${helmets.rows}">
    
        <div class="helmet-card">
           <img src="${pageContext.request.contextPath}/resources/helmets/${helmet.helmet_image}" alt="">
		            <h2>${helmet.helmet_Name}</h2>
		            <span>${helmet.price}</span>
        </div>
       </c:forEach> 
    
</body>
</html>