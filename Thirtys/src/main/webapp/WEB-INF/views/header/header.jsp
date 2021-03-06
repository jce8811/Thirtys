<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<!-- 웹 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Gugi&display=swap" rel="stylesheet">
<!-- css -->
<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<div id="header">
	<div class="container">
		<div class="header">
			<div class="header-menu">
				<a href="">홈페이지 소개</a>
				<c:if test="${empty login}">
				<a href="${path}/user/login">로그인</a>
				<a href="${path}/user/join" style="color: #3d449c;">회원가입</a>
				</c:if>
				<c:if test="${not empty login}">
				<%-- <a href="${path}/user/info?uid=${login.uid}">${login.uname}님(${login.uid})</a> --%>
				<a href="${path}/admin/admin_Page">${login.uname}님(${login.uid})</a>
				<a href="${path}/user/logout">로그아웃</a>
				</c:if>
			</div>
		</div>
	</div>
</div>

<script src="${path}/resources/js/jquery.min_1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<!-- //header -->