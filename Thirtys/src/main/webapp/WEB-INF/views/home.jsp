<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>30's</title>	
</head>
<!-- 웹폰트 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Stylish&display=swap" rel="stylesheet">
<c:set var="path" value="${pageContext.request.contextPath}"/>
<body>
<div id="wrap">
	<c:import url="header/header.jsp"/>
	<c:import url="nav/nav.jsp"/>
	<div id="side">Side</div>
	<!-- side -->
	<div id="content">Content</div>
	<!-- //content -->
	<div id="footer">Footer</div>
	<!-- //footer -->
</div>
<!-- //wrap -->
</body>
</html>
