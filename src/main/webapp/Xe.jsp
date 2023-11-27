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
	<h1>Danh sách xe</h1>
	<form action="DanhSachXeTheoLoaiXe" method="get">
		<select name="maLX">
		<c:forEach items="${model}" var="listLoai1">
			<option value="${listLoai1.maLoai}">${listLoai1.tenLoai}</option>
		</c:forEach>
		</select>
		<button type="submit">Tìm</button>
	</form>
	<table border="1">
		<tr>
			<th>Tên xe</th>
			<th>Giá</th>
			<th>Năm sản xuất</th>
			<th>Tên loại xe</th>
			<th>Chỉnh sửa</th>
		</tr>
		<c:forEach var="listXe" items="${models}">
			<tr>
				<td>${listXe.tenXe}</td>
				<td>${listXe.gia}</td>
				<td>${listXe.namSX}</td>
				<td>${listXe.getLoaiXe().getTenLoai()}</td>
				<td>
					<form action="deleteXe?maXe=${listXe.maXe}" method="post">
						<button type="submit">delete</button>
					</form>
					<button onclick="window.location.href='FormUpXe?maXe=${listXe.maXe}'">Sửa</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>