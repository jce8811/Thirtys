<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리</title>
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
			<!-- 검색탭 -->
			<div class="search-tap">
				<select name="searchType">
					<option value="n" <c:out value="${scri.searchType == null?'selected':'' }"/>>---</option> <!-- 검색조건이 없음 -->
					<option value="t" <c:out value="${scri.searchType eq 't'?'selected':'' }"/>>제목</option> <!-- 제목으로 검색 -->
					<option value="c" <c:out value="${scri.searchType eq 'c'?'selected':'' }"/>>내용</option> <!-- 내용으로 검색 -->
					<option value="w" <c:out value="${scri.searchType eq 'w'?'selected':'' }"/>>작성자</option> <!-- 작성자로 검색 -->
				</select>
				<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" placeholder="검색어">
				<button id="searchBtn">검색</button>
			</div>
			<!-- //검색탭 -->
				<table>
					<tr>
						<th width=50>No</th>
						<th width=600>글제목</th>
						<th width=100>글쓴이</th>
						<th width=230>글작성시간</th>
						<th>조회수</th>
					</tr>
					<c:forEach items="${list}" var="boardVO">
					<c:choose>
						<c:when test="${boardVO.state == 'R'}">
							<tr>
								<td>${boardVO.bno}</td>
								<td><a href="${path}/gallery/read${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}">${boardVO.title}</a>
									<span style="color:#FF0080;">[${boardVO.replycnt}]</span>
								</td>
								<td>${boardVO.writer}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
								<td>${boardVO.viewcnt}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${boardVO.bno}</td>
								<td><strong style="color: #FF0000;">-삭제된 게시글 입니다.-</strong></td>
								<td>${boardVO.writer}</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
								<td>${boardVO.viewcnt}</td>
							</tr>
						</c:otherwise>
					</c:choose>		
				</c:forEach>
				</table>
				
			<div class="paging1">
				<div class="paging">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li><a href="list${pageMaker.makeSearch(pageMaker.startPage -1) }" style="color: #3d449c; font-weight: 600;">이전</a></li> 
							<%-- <li><a href="${pageMaker.startPage -1}" style="color: #3d449c; font-weight: 600;">이전</a></li> --%>
						</c:if>
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li <c:out value="${pageMaker.cri.page == idx ?'class = active':''}"/>>
							<a href="list${pageMaker.makeSearch(idx)}">${idx}</a></li>
							<%-- <a href="${idx}">${idx}</a> --%>
						</c:forEach>
						
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li><a href="list${pageMaker.makeSearch(pageMaker.endPage +1) }" style="color: #3d449c; font-weight: 600;">다음</a></li> 
							<%-- <li><a href="${pageMaker.endPage +1}" style="color: #3d449c; font-weight: 600;">다음</a></li> --%>
						</c:if>
					</ul>
					<form id="listPageForm">
						<input type="hidden" name="page" value="${pageMaker.cri.page}">
						<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
					</form>
				</div>
				<div class="btn-box01"><a class="btn01" href="${path}/gallery/write">글쓰기</a></div>
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
}else if(result == "Msuccess"){
	alert("수정이 완료되었습니다.");
}else if(result == "Dsuccess"){
	alert("삭제가 완료되었습니다.");
}

$(document).ready(function(){
	$("#searchBtn").on("click", function(event){
		self.location = "list${pageMaker.makeQuery(1)}" + "&searchType=" + $("select option:selected").val()
						+"&keyword=" + encodeURIComponent($("#keywordInput").val());
	});
	
});
</script>
</body>
</html>