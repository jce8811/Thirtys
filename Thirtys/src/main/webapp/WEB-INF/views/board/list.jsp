<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="board">
		<div class="container">
		 	<div class="board-header">
			<h2>자유게시판</h2>
			</div>
			<div class="board-body">
				<table>
					<tr>
						<th width=50>No</th>
						<th width=600>글제목</th>
						<th>글쓴이</th>
						<th>글작성시간</th>
						<th>조회수</th>
					</tr>
					<c:forEach items="${list}" var="boardVO">
					<tr>
						<td>${boardVO.bno}</td>
						<td><a href="">${boardVO.title}</a></td>
						<td>${boardVO.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
						<td>${boardVO.viewcnt}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- //content -->
	<div id="footer"></div>
	<!-- //footer -->
</div>
</body>
</html>