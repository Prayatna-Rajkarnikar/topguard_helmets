<%@ page import="util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Helmet Management</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/adminProduct.css" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
    <a href="${pageContext.request.contextPath}/pages/adminPanel.jsp">
            <button class="back_button">Go Back</button>
    </a>
    <div class="helmet-container">
        <h1>Helmet Management</h1>
        <div class="helmet-form-container">
            <h2>Add a New Helmet</h2>
            <form action="${pageContext.request.contextPath}/AdminProductServlet" method="post" enctype="multipart/form-data">
                <label for="helmet_name">Helmet Name:</label>
                <input type="text" id="helmet_name" name="helmet_name" required>
                <label for="helmet_price">Helmet Price:</label>
                <input type="number" id="helmet_price" name="helmet_price" required>
                <label for="helmet_brand">Helmet Brand:</label>
                <input type="text" id="helmet_brand" name="helmet_brand" required>
                <label for="helmet_color">Color:</label>
                <input type="text" id="helmet_color" name="helmet_color" required>
                <label for="helmet_size">Size:</label>
                <input type="text" id="helmet_size" name="helmet_size" required>
                <label for="helmet_image">Helmet Image:</label>
                <input type="file" id="helmet_image" name="helmet_image" accept="image/png, image/jpeg, image/jpg" required>
                <button type="submit" name="add_helmet">Add Helmet</button>
            </form>
        </div>
        <div class="helmet-table-container">
            <table class="helmet-table">
                    <thead>
                        <tr>
                            <th>Helmet Id</th>
                            <th>Helmet Image</th>
                            <th>Helmet Name</th>
                            <th>Helmet Price</th>
                            <th>Helmet Brand</th>
                            <th>Helmet Color</th>
                            <th>Helmet Size</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>        
                    <c:if test="${empty helmets}">
                    <tr>
                        <td colspan="8">No products found.</td>
                    </tr>
                </c:if>
                <c:if test="${not empty helmets}">
                    <c:forEach var="helmet" items="${helmets}">
                        <tr>
                            <td>${helmet.helmet_ID}</td>
                            <td><img src="${pageContext.request.contextPath}/resources/helmets/${helmet.userImageUrl}" height="100" alt="Helmet Image"></td>
                            <td>${helmet.helmet_Name}</td>
                            <td>${helmet.price}</td>
                            <td>${helmet.brand}</td>
                            <td>${helmet.color}</td>
                            <td>${helmet.size}</td>
                            <td>
                            	<form action = "/TopGuard_Helmets/ChangeProductServlet" method = "post">
                            	<input type = "hidden" name = "<%=StringUtil.UPDATE_ID%>" value = "${helmet.helmet_ID}">
                                <button class="edit-btn">Edit</button>
                                </form>
                                
                                <form action= "/TopGuard_Helmets/ChangeProductServlet" method="post">
                                <input type="hidden" name = "<%=StringUtil.DELETE_ID %>" value="${helmet.helmet_ID}">
                                	<button class="delete-btn">Delete</button>
                            	</form>
                            </td>
                        </tr>
                   </c:forEach>
                </c:if>
                    </tbody>
            </table>
        </div>
    </div>
</body>
</html>
