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
						<td><a href="${path}/board/read?bno=${boardVO.bno}">${boardVO.title}</a></td>
						<td>${boardVO.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
						<td>${boardVO.viewcnt}</td>
					</tr>
				</c:forEach>
				</table>
				
			<div class="paging1">
				<div class="paging"></div>
				<div class="btn-box01"><a class="btn01" href="${path}/board/write">글쓰기</a></div>
			</div>
			<!-- //페이징 및 버튼 끝 -->
			</div>
			<!-- //게시판리스트 끝 -->
		</div>
	</div>
	<!-- //게시판 전체 끝 -->
	<div id="footer"></div>
	<!-- //footer -->
</div>

<!-- script -->
<script>
	var result = "${msg}";
	if(result == "Wsuccess"){
		alert("작성이 완료되었습니다.");
	}else if(reuslt == "Msuccess"){
		alert("수정이 완료되었습니다.");
	}else if(result == "Dsuccess"){
		alert("삭제가 완료되었습니다.");
	}
</script>
</body>
</html>