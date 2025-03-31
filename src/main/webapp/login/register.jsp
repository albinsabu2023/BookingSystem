<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> Register Now to login</h1>
	<form  action="${pageContext.request.contextPath}/register" method="POST" >
	  id <input  type="text" name="id"> </br>
	  name:<input type="text" name="username"> </br>
	  password <input  type="text" name="password"> </br>
	  <input type="submit" value="login">
	</form>
	
</body>
</html>