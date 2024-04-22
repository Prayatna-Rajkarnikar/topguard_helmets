<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/landingPage.css" />
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
            <img src="./images/home1.jpg" alt="">
        </div>
    </section>

    <section class="helmets">
        <div class="helmet-card">
            <img src="./images/helemet1.jpg" alt="Product 1">
            <h2>Product 1</h2>
            <p>Rs. 4000</p>
        </div>
        <div class="helmet-card">
            <img src="./images/helmet2.jpg" alt="Product 2">
            <h2>Product 2</h2>
            <p>Rs. 2000</p> 
        </div>
        <div class="helmet-card">
            <img src="./images/helmet3.jpg" alt="Product 3">
            <h2>Product 3</h2>
            <p>Rs. 9000</p>
        </div>
        <div class="helmet-card">
            <img src="./images/helmet4.jpg" alt="Product 4">
            <h2>Product 4</h2>
            <p>Rs. 6000</p> 
        </div>
    </section>

</body>
</html>