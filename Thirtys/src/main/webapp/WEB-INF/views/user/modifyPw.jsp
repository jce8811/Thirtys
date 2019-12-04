<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="user">
		<div class="container">
			<div class="user-side">
				<h2 style="font-size:24px;">비밀번호 변경</h2>
				<c:import url="../sidebar/sidebar.jsp"/>	
			</div>
			
			<div class="info-body">
			<c:choose>
				<c:when test="${upw != null}">
				<form class="user-form" action="${path}/user/modifyPwPOST" method="post">
					<fieldset>
						<input type="password" name="upw" id="upw" placeholder="변경할 비밀번호를 입력하세요">
						<input type="password" name="upwchk" id="upwchk" placeholder="한번 더 확인해 주세요.">
					</fieldset>
					<input type="hidden" id="uid" name="uid" value="${login.uid}"/>
					<div class="btn">
					<button class="btn-join" type="submit">비밀번호 변경</button>
					</div>
				</form>
			</div>
			</c:when>
			<c:otherwise>
				<p>입력된 정보가 일치하지 않습니다.</p>
				<a href="${path}/user/checkId">다시 확인하러 가기</a>
			</c:otherwise>
			</c:choose>
		</div>
	</div>	
</div>
</body>
</html>