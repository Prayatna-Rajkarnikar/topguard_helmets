<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/adminProduct.css" />
</head>
<body>
	<div class="helmet-container">
        <h1>Helmet Management</h1>
        <div class="helmet-form-container">
            <h2>Add a New Helmet</h2>
            <form action="/TopGuard_Helmets/AdminProductServlet" method="post" enctype="multipart/form-data">
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
                <tbody>
                    <tr>
                        <td>Helmet Id</td>
                        <td><img src="images/helmet3.jpg" height="100" alt="Product Image"></td>
                        <td>Helmet Name</td>
                        <td> RS 6100</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class = "buttons">
		    <button class="edit-btn">Edit</button>
		    <button class="delete-btn">Delete</button>
    </div>

</body>
</html>