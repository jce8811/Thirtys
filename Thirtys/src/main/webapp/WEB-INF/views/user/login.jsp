<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="user">
		<div class="container">
			<div class="user-side">
				<h2>로그인</h2>
			</div>
			<div class="user-body">
				<form class="user-form" action="${path}/user/loginPOST" method="post">
					<h3>L O G I N</h3>
					<fieldset>
						<input type="text" name="uemail" class="uemail" placeholder="이메일">
						<input type="password" name="upw" class="upw" placeholder="비밀번호">
					</fieldset>
					<div class="find">
						<a>아이디 비밀번호 찾기</a>
					</div>
				<div class="btn">
						<button class="btn-join" type="submit">로그인</button>
						<a href="${path}/user/join" class="btn-back">회원가입</a>
					</div>	
				</form>	
			</div>
		</div>
	</div>
</div>
<script>
var msg = "${msg}"
if(msg == "join"){
	alert("회원가입이 완료되었습니다. 로그인 해 주세요");
}
</script>
</body>
</html>