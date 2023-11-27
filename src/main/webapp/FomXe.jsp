<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="addXe" method="post">
	<label >Tên xe</label><br>
	<input name="tenXe" /><br>
	<label>Giá</label><br>
	<input name="gia" /><br>
	<label>Năm sản xuất</label><br>
	<input name="namSX" /><br>
	<label>Loại xe</label>
	<select name="loaiXe">
	<c:forEach items="${model}" var="listLX">
		<option value="${listLX.maLoai}">${listLX.tenLoai}</option>
	</c:forEach>
	</select><br>
	<button type="submit">Thêm</button>
</form>
</body>
</html>