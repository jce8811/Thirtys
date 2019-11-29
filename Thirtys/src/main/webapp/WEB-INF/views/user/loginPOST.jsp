<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
	alert("아이디와 비밀번호를 확인해주세요.");
	self.location = "${path}/user/login";
</script>
</body>
</html>