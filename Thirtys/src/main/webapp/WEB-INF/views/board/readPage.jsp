<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<div class="reply">
					<h2>댓글 목록</h2>
					<ul id="reply">
		
					</ul>
				</div>
				<div class="reply-write">
					<div class="reply-form">
						<table>
						<tr>
							<th>작성자</th>
							<td>
							<input type="text" name="rwriter" id="rwriter">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
							<textarea id="rcontent" name="rcontent" maxlength="10000" title="내용" required></textarea>
							</td>
						</tr>
						</table>
					</div>			
				</div>
				<div class="btn-reply">
					<input type="button" class="reply-button" id="replyAddBtn" name="replyAddBtn" value="댓글등록">
				</div>
				<form role="form" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno}">
					<input type="hidden" name="page" value="${cri.page}">
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
				</form>
			 <div class="btn-box02">
				 <a class="btn01" href="${path}/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}">목록</a>
				 <c:if test="${login.uname == boardVO.writer}"> 
				 <button type="submit" class="btn-modify">글수정</button>
				 <button type="submit" class="btn-delete">글삭제</button>
				 </c:if>
				 
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
		formObj.attr("action", "/board/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});
	$(".btn-delete").on("click", function(){
		formObj.attr("action", "/board/deletePage");
		formObj.submit();
	});
});

//댓글 목록 출력 함수
function getAllList(){

	var bno = 8177;
	$.getJSON("/reply/all/" + bno, function(data){
		var str = "";
		console.log(data.length);
		
		$(data).each(function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
					+ this.rno + ":" + this.rcontent + "</li>";
			});
		$("#reply").html(str);
	});
}

getAllList();
$("#replyAddBtn").on("click", function(){
	var bno = 8177;
	var rwriter = $("#rwriter").val();
	var rcontent = $("#rcontent").val();
	
	$.ajax({
		type : "post",
		url : "/reply",
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : "text",
		data : JSON.stringify({
			bno : bno,
			rwriter : rwriter,
			rcontent : rcontent
		}),
		success : function(result) {
			if(result == "writeSuccess") {
				alert("등로 완료.");
			}
			getAllList(); // 댓글 목록 출력 함수 호출
		}
	});
});
</script>
</body>
</html>