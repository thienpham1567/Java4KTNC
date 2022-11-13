<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Title</title>
<meta charset="utf-8">
<base href="/JAVA4_Lab5/">
</head>
<body>
		<h2>Sign In</h2>
		<form class="account/sign-in" method="POST">
			<input type="text" name="id" placeholder="Username" /> 
			<input type="password" name="password" placeholder="Password" />
	<div>
		<c:if test="${not empty message}">
			<div class="alert">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert">${error}</div>
		</c:if>
	</div>
		<br>
		<input type="submit" class="form-submit" value="Log in"	formaction="account/sign-in" />
		<a href="account/sign-up">Create an account</a>
		</form>
</body>
</html>