<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/product.css" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<jsp:include page="navBar.jsp"/>
</head>
<body class = "body">

	<div class="heading">
        <h1>Our Products</h1>
    </div>
    <div class="helmet_container">
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/topguard_helmets" user="root" password="" />
            <sql:query dataSource="${dataSource}" var="helmets">
                SELECT * FROM helmet;
            </sql:query>
           <c:forEach var="helmet" items="${helmets.rows}">
		        <div class="helmet_box">
		            <img src="${pageContext.request.contextPath}/resources/helmets/${helmet.helmet_image}">
		            <h2>${helmet.helmet_Name}</h2>
		            <span>${helmet.price}</span>
		            <p>Brand: ${helmet.brand}</p>
		            <p>Color: ${helmet.color}</p>
		            <p>Size: ${helmet.size}</p>
		           
		            <div class="helmet_price"></div>
		            <div class="options">
		                <a href="#">Buy It Now</a>
		            </div>
		        </div>
          </c:forEach>
    </div>

</body>
</html>