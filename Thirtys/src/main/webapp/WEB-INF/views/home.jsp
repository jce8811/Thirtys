<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>30's</title>	
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div id="wrap">
<c:import url="header/header.jsp"/>
<c:import url="nav/nav.jsp"/>
	<div class="container">
		<div id="middle">Middle</div>
		<!-- //content -->
		<div id="footer">Footer</div>
		<!-- //footer -->
	</div>	
</div>
<!-- //wrap -->
</body>
</html>
