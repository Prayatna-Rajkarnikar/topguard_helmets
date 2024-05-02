<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/profile.css" />

<!-- <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            min-height: 100vh;
            width: 100%;
            background: #3A4A4D;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .profile_container {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            width: 100%;
            max-width: 600px;
            margin: auto;
        }

        .profile_container h1 {
            font-size: 2rem;
            font-weight: 600;
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }

        .user-details {
            padding: 1rem 0;
            border-bottom: 1px solid #ddd;
            margin-bottom: 1.5rem;
        }

        .user-details p {
            font-size: 1.1rem;
            color: #555;
            margin-bottom: 0.5rem;
        }

        .updateButton {
            text-align: center;
        }

        .update-button a {
            text-decoration: none;
        }

        .update-button button {
            background-color: #3A4A4D;
            color: #fff;
            border: none;
            border-radius: 8px;
            padding: 12px 20px;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.4s;
            outline: none;
        }

        .update-button button:hover {
            background-color: #7f9fa4;
        }
    </style> -->
</head>
<body>
<jsp:include page="navBar.jsp"/>
<div class="profile_container">
    <h1>Profile</h1>
    <div class="user-details">
        <p><b>Username:JohnDoe123</b></p>
        <p><b>Full Name:</b> John Doe</p>
        <p><b>Email:</b> johndoe@example.com</p>
        <p><b>Address:</b> 123 Main Street, Cityville</p>
    </div>
    <div class="updateButton">
        <a href="../DS UI/editProfile.html" target="_self">
            <button>Update <i class="fas fa-edit"></i></button>
        </a>
    </div>
</div>
</body>
</html>