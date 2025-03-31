<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1> welcome to landing page</h1>
		
		<a href="${pageContext.request.contextPath}/booking/bookings">View Bookings</a>
		<a href="${pageContext.request.contextPath}/booking/feedbacks">View feedbacks</a>
		<a href="${pageContext.request.contextPath}/booking/rooms">View Rooms</a>
		<a href="${pageContext.request.contextPath}/login">Login</a>
		<a href="${pageContext.request.contextPath}/register">Register</a>
		<a href="${pageContext.request.contextPath}/logout">logout</a>
</body>
</html>