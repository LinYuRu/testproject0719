<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人資料</title>
</head>
<body>
	<table border='1'>
		<tr>
			<th width='120'>學號</th>
			<th width='140'>姓名</th>
			<th width='120'>密碼</th>
			<th width='300'>信箱</th>
		</tr>
		<tr>
			<th width='120'><c:out value='${list.sno}' /></th>
			<th width='140'><c:out value='${list.sname}' /></th>
			<th width='120'><c:out value='${list.spwd}' /></th>
			<th width='120'><c:out value='${list.smail}' /></th>
		</tr>
	</table>
	<h3>
		<a href="index">回首頁</a>
	</h3>
</body>
</html>