 <%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/adminPanel.css" />
</head>
<body>
	<%
    // Get the session and request objects
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtil.username);
    String contextPath = request.getContextPath();
    
	%>
	
    <div class="sideNav">
        <nav>     
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/pages/adminPanel.jsp">
                        <h1><span class="navItem">Dashboard</span></h1>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/pages/.jsp">
                        <span class="navItem">Profile</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/pages/adminProduct.jsp">
                        <span class="navItem">Products</span>
                    </a>
                </li>
                <li>
                    <a href="">
                        <span class="navItem">Help</span>
                    </a>
                </li>
                <li>
                    <form action="<%
                    // Conditionally set the action URL based on user session
                    if (currentUser != null) {
                        out.print(contextPath + StringUtil.SERVLET_URL_LOGOUT);
                    } else {
                        out.print(contextPath + StringUtil.PAGE_URL_LOGIN);
                    }
                %>" method="post">
                    <input type="submit" value="<%
                        // Conditionally set the button label based on user session
                        if (currentUser != null) {
                            out.print(StringUtil.LOGOUT);
                        } else {
                            out.print(StringUtil.LOGIN);
                        }
                    %>"/>
                </form>
                </li>
            </ul>
        </nav>
    </div>
    <section class="main">
        <div class="reports">
            <div class="report_card">
                <h1>User Activity</h1>
                <h3>10,000</h3>
            </div>
            <div class="report_card">
                <h1>Monthly Revenue</h1>
                <h3>$50,000</h3>
            </div>
            <div class="report_card">
                <h1>Page Viewer</h1>
                <h3>25,000</h3>
            </div>
        </div>
        
        <div class="record-header"></div>
        
        <div>
            <table width="100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Helmet Name</th>
                        <th>Price</th>
                        <th>Brand</th>
                        <th>Color</th>
                        <th>Size</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Helmet 1</td>
                        <td>$100</td>
                        <td>Brand 1</td>
                        <td>Red</td>
                        <td>Medium</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Helmet 2</td>
                        <td>$120</td>
                        <td>Brand 2</td>
                        <td>Blue</td>
                        <td>Large</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Helmet 3</td>
                        <td>$90</td>
                        <td>Brand 3</td>
                        <td>Green</td>
                        <td>Small</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>
</body>
</html>