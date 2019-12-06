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
				<h2 style="font-size:24px;">이메일 확인</h2>
			</div>	
				<div class="user-body">
					<form class="user-form" action="${path}/user/findIdPwPOST", method="post">
						<h3 style="font-size:30px;">아이디&이메일 확인</h3>
						<fieldset>
							<input type="email" name="uemail" class="uemail" placeholder="이메일을 입력하세요." style="border-top:1px solid #b9b9b9">
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