<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register here</title>
</head>
<body>

	<form name="register" action="register" method="POST">
		<table border="1">
			<thead>
				<tr>
					<th height="60" colspan="2" align="center">新增學生資料</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="120" height="40">帳號:</td>
					<td width="600" height="40" align="left"><input name="sno"
						id="sno" path="sno" type='text' />
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">密碼:</td>
					<td width="600" height="40" align="left"><input name="spwd"
						id="spwd" path="spwd" type='text' /></td>
				</tr>
				<tr bgcolor='tan'>
					<td width="120" height="40">姓名:</td>
					<td width="600" height="40" align="left"><input name="sname"
						id="sname" path="sname" type='text' /></td>
				</tr>
				<tr>
					<td width="120" height="40">信箱:</td>
					<td width="600" height="40" align="left"><input name="smail"
						id="smail" path="smail" type='text' /></td>
				</tr>
				<tr>
					<td height="50" colspan="2" align="center"><input
						type="submit" value="送出"></td>
				</tr>

			</tbody>
		</table>
	</form>
	<h3>
		<a href="index">回首頁</a>
	</h3>
</body>
</html>