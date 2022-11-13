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
	<h2>Sign up</h2>
	<form class="account/sign-up" method="POST">
		<input type="text" name="id" placeholder="Username" /> <input
			type="password" name="password" placeholder="Password" /> <input
			type="text" name="fullname" placeholder="Fullname" /> <input
			type="email" name="email" placeholder="Your Email" /> <label><input
			name="admin" value="false" type="hidden" /></label>

		<c:if test="${not empty message}">
			<div class="alert">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert">${error}</div>
		</c:if>
		<input type="submit" value="Register" formaction="account/sign-up" />
	</form>
	<a href="account/sign-in" class="signup-image-link">I am already member</a>
</body>
</html>