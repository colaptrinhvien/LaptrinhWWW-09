<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="window.location.href='DanhSachLoaiXe'">Trở về</button>
	<h1>Danh sách xe</h1>
	<table border="1">
		<tr>
			<th>Tên xe</th>
			<th>Giá</th>
			<th>Năm sản xuất</th>
			<th>Tên loại xe</th>
		</tr>
		<c:forEach var="listXe" items="${modelXe}">
			<tr>
				<td>${listXe.tenXe}</td>
				<td>${listXe.gia}</td>
				<td>${listXe.namSX}</td>
				<td>${listXe.getLoaiXe().getTenLoai()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>