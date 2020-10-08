<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="pwdForget" action="pwdForget" method="POST">
		<table border="1">
			<thead>
				<tr>
					<th height="60" colspan="2" align="center">忘記密碼</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="120" height="40">帳號:</td>
					<td width="600" height="40" align="left"><input name="sno"
						id="sno" path="sno" type='text' />
				</tr>
				<tr>
					<td height="50" colspan="2" align="center"><input
						type="submit" value="送出新密碼"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<h3>
		<a href="index">回首頁</a>
	</h3>
</body>
</html>