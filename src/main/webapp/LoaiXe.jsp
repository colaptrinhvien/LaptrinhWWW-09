<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="window.location.href='Trang-Chu'">Trở về</button>
<h1>Danh sách xe theo loại xe</h1>
<form action="DanhSachXeTheoLoaiXe" method="get">
	<select name="maLX">
	<c:forEach items="${model}" var="listLoai1">
		<option value="${listLoai1.maLoai}">${listLoai1.tenLoai}</option>
	</c:forEach>
	</select>
	<button type="submit">Tìm</button>
</form>	

	<table border="1" style="margin-top: 10px">
		<tr>
			<th style="width: 200px">Tên loại</th>
			
		</tr>
		<c:forEach items="${model}" var="listLoai">
		
		<tr>
			<td>${listLoai.tenLoai}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>