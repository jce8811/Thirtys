<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<body>
<div id="wrap">
	<div id="info">
		<div class="container">
			<div class="user-side">
				<h2>관리자 페이지</h2>
			<c:import url="../sidebar/sidebar_admin.jsp"/>	
			</div>
			<div class="board-body" style="overflow:hidden;">
				<div class="search-tap">
					<select name="searchType">
						<option value="n" <c:out value="${scri.searchType == null?'selected':'' }"/>>---</option> <!-- 검색조건이 없음 -->
						<option value="t" <c:out value="${scri.searchType eq 't'?'selected':'' }"/>>아이디</option> <!-- 아이디로 검색 -->
						<option value="c" <c:out value="${scri.searchType eq 'c'?'selected':'' }"/>>닉네임</option> <!-- 닉네임으로 검색 -->
						<option value="w" <c:out value="${scri.searchType eq 'w'?'selected':'' }"/>>이메일</option> <!-- 이메일로 검색 -->
					</select>
					<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" placeholder="검색어">
					<button id="searchBtn">검색</button>
				</div>
			<table>
				<tr>
					<th width=50>ID</th>
					<th width=200>닉네임</th>
					<th width=150>EMAIL</th>
					<th width=250>가입날짜</th>
					<th width=>포인트</th>
					<th width=>상태</th>
				</tr>
				<c:forEach items="${list}" var="userVO">
					<tr>
						<td>${userVO.uid}</td>
						<td>${userVO.uname}</td>
						<td>${userVO.uemail}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${userVO.udate}"/></td>
						<td>0</td>
						<td>회원</td>
					</tr>
				</c:forEach>
			</table>
			<div class="paging1">
				<div class="paging">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li><a href="userList${pageMaker.makeSearch(pageMaker.startPage -1) }" style="color: #3d449c; font-weight: 600;">이전</a></li> 
							<%-- <li><a href="${pageMaker.startPage -1}" style="color: #3d449c; font-weight: 600;">이전</a></li> --%>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li <c:out value="${pageMaker.cri.page == idx ?'class = active':''}"/>>
							<a href="userList${pageMaker.makeSearch(idx)}">${idx}</a></li>
							<%-- <a href="${idx}">${idx}</a> --%>
						</c:forEach>
						
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li><a href="userList${pageMaker.makeSearch(pageMaker.endPage +1) }" style="color: #3d449c; font-weight: 600;">다음</a></li> 
							<%-- <li><a href="${pageMaker.endPage +1}" style="color: #3d449c; font-weight: 600;">다음</a></li> --%>
						</c:if>
					</ul>
					<form id="listPageForm">
						<input type="hidden" name="page" value="${pageMaker.cri.page}">
						<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
					</form>
				</div>
			</div>
				
			</div>
		</div>
	</div>	
</div>
<script>
$(document).ready(function(){
	$("#searchBtn").on("click", function(event){
		self.location = "userList${pageMaker.makeQuery(1)}" + "&searchType=" + $("select option:selected").val()
						+"&keyword=" + encodeURIComponent($("#keywordInput").val());
	});
	
});
</script>
</body>
</html>