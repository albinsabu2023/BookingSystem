<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login To Book Your Room now</h1>
	<form  action="${pageContext.request.contextPath}/login" method="POST" >
	  id <input  type="text" name="id"> </br>
	  password <input  type="text" name="password"> </br>
	  <input type="submit" value="login">
	</form>
</body>
</html>