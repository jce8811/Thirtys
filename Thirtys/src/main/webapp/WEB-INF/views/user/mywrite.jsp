<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="mywrite">
		<div class="container">
			<div class="user-side">
				<h2>마이페이지</h2>
				<ul>
					<li><a href="#">회원정보 변경</a></li>
					<li><a href="#">내가 쓴 글 보기</a></li>
					<li><a href="#">회원탈퇴</a></li>
				</ul>
			</div>
			<div class="mywrite-body">
			
			</div>
		</div>
	</div>	
</div>
</body>
</html>