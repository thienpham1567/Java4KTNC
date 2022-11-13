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
	<h2>Account Details</h2>
	<form method="POST">
		<input type="text" name="id" value="${user.id}" /> <input
			type="password" name="password" value="${user.password}" /> <input
			type="text" name="fullname" value="${user.fullname}" /> <input
			type="email" name="email" value="${user.email}" /> <label
			class="ml-2"><input name="admin" value="${user.admin}"
			type="hidden" /></label>

		<c:if test="${not empty message}">
			<div>${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div>${error}</div>
		</c:if>

		<input type="submit" value="Save Change"
			formaction="account/edit-profile" />
	</form>

	<a href="account/sign-out" class="signup-image-link">Sign out</a>
</body>
</html>