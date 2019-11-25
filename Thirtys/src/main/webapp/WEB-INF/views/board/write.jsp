<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
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
			<div class="write-body">
				<div class="write">
				<form role="form" id="writeForm" method="post" action="${path}/board/write">
					<div class="w-writer">
						<input type="text" id="writer" name="writer" placeholder="작성자">
					</div>
					<div class="w-title">
						<input type="text" id="title" name="title" placeholder="제목">
					</div>
					<div class="w-insert">
						<textarea id="content" name="content" rows="30" placeholder="내용"></textarea>
					</div>
					<div class="btn-box02">
					<a class="btn01" href="${path}/board/list">목록</a>
					<button type="reset">초기화</button>
					<button type="submit">작성</button>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>