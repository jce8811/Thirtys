<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- 웹 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
<!-- css -->
<link rel="stylesheet" href="<c:url value="/resources/css/reset.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<div id="header">
	<div class="container">
		<div class="header">
			<div class="header-menu">
				<a href="">홈페이지 소개</a>
				<a href="${path}/user/login">로그인</a>
				<a href="${path}/user/join" style="color: #3d449c;">회원가입</a>
			</div>
		</div>
	</div>
</div>
<script src="${path}/resources/js/jquery.min_1.12.4.js"></script>
<!-- //header -->