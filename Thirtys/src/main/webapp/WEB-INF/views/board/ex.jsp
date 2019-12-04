<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% response.setContentType("text/html"); %>    
<c:set var="path" value="${pageContext.request.contextPath}"/> 
<%

request.setCharacterEncoding("euc-kr");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>폼전송</h3>
<form action="${path}/board/submit" method="post">
    <input type="text" name="text" />
    <input type="submit" value="전송"/>
</form>

</body>
</html>