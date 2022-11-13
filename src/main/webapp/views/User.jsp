<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<base href="/JAVA4_Lab5/">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Thong Bao -->
	${message}
	<c:url var="url" value="/user" />
	<!-- Form -->
	<form action="user" method="post">
		<label>User ID:</label> <input value="${user.id}" name="id" placeholder="Username"> <br>
		<label>Password:</label> <input value="${user.password}" name="password" placeholder="Password"> <br>
		<label>Fullname:</label> <input value="${user.fullname}" name="fullname" placeholder="Fullname"> <br> 
		<label>Email Address:</label> <input value="${user.email}" name="email" placeholder="Email"> <br>
		<label>Role:</label> 
		<label><input type="radio" name="admin" 
				value="true" ${user.admin ? 'checked' : ''} />Admin </label> 
		<label><input type="radio" name="admin" 
				value="false" ${!user.admin ? 'checked' : ''} />User </label>
		<hr>
		<button formaction="user/create">Create</button>
		<button formaction="user/update">Update</button>
		<button formaction="user/delete">Delete</button>
		<a href="user/reset">Reset</a>
	</form>

	<!-- Table -->


	<table border="1" style="width: 100%">
		<thead>
			<tr>
				<td>User ID</td>
				<td>Password</td>
				<td>Fullname</td>
				<td>Email</td>
				<td>Role</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<c:forEach var="item" items="${users}">
			<tr>
				<td>${item.id}</td>
				<td>${item.password}</td>
				<td>${item.fullname}</td>
				<td>${item.email}</td>
				<td>${item.admin ? 'Admin' : 'User'}</td>
				<td><a href="user/edit?id=${item.id}">Edit</a> | 
				<a href="user/delete?id=${item.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>