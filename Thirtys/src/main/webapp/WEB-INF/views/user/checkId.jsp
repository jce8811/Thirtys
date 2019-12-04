<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>30's</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<body>
<div id="wrap">
	<div id="user">
		<div class="container">
			<div class="user-side">
				<h2 style="font-size:24px;">비밀번호 변경</h2>
				<c:import url="../sidebar/sidebar.jsp"/> 
			</div>	
				<div class="info-body">
					<form class="user-form" action="${path}/user/checkIdPOST", method="post">
						<h3 style="font-size:30px;">아이디&이메일 확인</h3>
						<fieldset>
							<input type="text" name="uid"  class="uid" placeholder="아이디를 입력하세요.">
							<input type="email" name="uemail" class="uemail" placeholder="이메일을 입력하세요.">
						</fieldset>
						<div class="btn">
						<button class="btn-join" type="submit">비밀번호 찾기</button>
						</div>
					</form>
				</div>
		</div>
	</div>
</div>
</body>
</html>