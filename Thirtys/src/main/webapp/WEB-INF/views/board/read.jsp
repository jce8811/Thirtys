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
				<form role="form" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno}">
				</form>
			 <div class="btn-box02">
				 <a class="btn01" href="${path}/board/list">목록</a>
				 <button type="submit" class="btn-modify">글수정</button>
				 <button type="submit" class="btn-deleft">글삭제</button>
				 <a
			 </div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	var formObj = $("form[role='form']");
	console.log(formObj);
	$(".btn-modify").on("click", function(){
		formObj.attr("action", "/board/modify");
		formObj.attr("method", "get");
		formObj.submit();
	});
	$(".btn-delete").on("click", function(){
		formObj.attr("action", "/board/remove");
		formObj.submit();
	});
});
</script>
</body>
</html>