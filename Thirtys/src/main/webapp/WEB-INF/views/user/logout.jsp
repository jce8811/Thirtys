<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<body>
<script>

var result = "${msg}";
if(result == "Msuccess"){
	alert("비밀번호 변경이 완료되었습니다.");
}

self.location = "${path}/";
</script>
</body>
</html>