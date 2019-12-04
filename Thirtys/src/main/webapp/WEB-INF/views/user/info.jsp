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
	<div id="info">
		<div class="container">
			<div class="user-side">
				<h2>마이페이지</h2>
			<c:import url="../sidebar/sidebar.jsp"/>	
			</div>
			<div class="info-body">
				<div class="mypage">
				</div>
			</div>
		</div>
	</div>	
</div>
</body>
</html>