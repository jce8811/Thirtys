<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<body>
<div id="wrap">
	<div id="board">
		<div class="container">
			<div class="board-header">
			<h2>자유게시판</h2>
			</div>
			<div class="read-body">
				<div class="read">
					<div class="r-header">
						<p class="r-title">${boardVO.title}</p>
						<p class="r-info">
						작성자: ${boardVO.writer} /
						조회: ${boardVO.viewcnt}<br/>
						<span><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${boardVO.regdate}"/></span>
						</p>
					</div>	
						<div class="r-body">
							<div class="r-content">
							${boardVO.content}
							</div>
						</div>
				</div>
			 <div class="btn-box02">
				 <a class="btn01" href="${path}/board/list">목록</a>
				 <a class="btn02" href="${path}/board/write">글쓰기</a>
			 </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>