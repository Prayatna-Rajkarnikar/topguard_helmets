<%@page import = "util.StringUtil" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Helmet</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/editHelmet.css" />
</head>
<body>
    <div class="update_Helmet">
        <h2>Update Helmet</h2>
        <form action="${pageContext.request.contextPath}/UpdateHelmetServlet" method="post">
            <div class="update_form_group">
                <label for="helmet_id">Helmet ID:</label>
                <input type="text" id="helmet_id" name="<%=StringUtil.helmetID%>" value="${helmet_id}" readonly>
            </div>
            <div class="update_form_group">
                <label for="helmet_name">Helmet Name:</label>
                <input type="text" id="helmet_name" name="<%=StringUtil.helmetName%>" value="${helmet_name}" required>
            </div>
            <div class="update_form_group">
                <label for="price">Helmet Price:</label>
                <input type="number" id="price" name="<%=StringUtil.helmetPrice%>" value="${price}" required>
            </div>
            <div class="update_form_group">
                <label for="brand">Brand:</label>
                <input type="text" id="brand" name="<%=StringUtil.brand%>" value="${brand}" required>
            </div>
            <div class="update_form_group">
                <label for="color">Color:</label>
                <input type="text" id="color" name="<%=StringUtil.color%>" value="${color}" required>
            </div>
            <div class="update_form_group">
                <label for="size">Size:</label>
                <input type="text" id="size" name="<%=StringUtil.size%>" value="${size}" required>
            </div>
            <div class="update_form_group">
                <button type="submit">Update Helmet</button>
            </div>
        </form>
    </div>
</body>
</html>
