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
				<!-- 답글 -->
				<div class="reply">
					<h2>댓글 목록</h2>
					
					<ul id="reply">
				
					</ul>
					<div class="paging1">
						<div class="paging">
							<ul class="pagination">
							
							</ul>
						</div>
					</div>
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
				<!-- //답글 종료 -->
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
				<!-- 답글 수정 모달 -->
				<div id="modBox" style="display:none;">
					<div class="modal-title"> 댓글 수정</div>
					<div>
						<input id="modalRno" name="modalRno" readonly>
					</div>
					<div>
						<input id="modalRwriter" name="modalRwriter" readonly>
					</div>
					<div>
						<textarea id="modalRcontent" name="modalRcontent" maxlength="10000" title="내용" required></textarea>
					</div>
					<div>
						<button type="button" id="replyModBtn">수정</button>
						<button type="button" id="replyDelBtn">삭제</button>
						<button type="button" id="closeBtn">닫기</button>
					</div>
				</div>
				<!-- //답글 수정 모달 -->
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

$(document).ready(function(){
	
var bno = ${boardVO.bno};
var replyPageNum = 1;
getPageList(1);
//댓글 목록 출력 함수
/* function getAllList(){
	$.getJSON("/reply/all/" + bno, function(data){
		var str = "";
		console.log(data.length);
		
		$(data).each(function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
					+ "<p class='rwriter'>" + this.rwriter + "</p>"  
					+ "<p class='rcontent'>" + this.rcontent + "</p>"
					+ "<button>수정</button>"
					+ "</li>"
					+ "<hr/>";
			});		
		$("#reply").html(str);
	});
}  */

// 댓글 작성
$("#replyAddBtn").on("click", function(){
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
			getPageList(page);
		}
	});
});

// 댓글 수정 모달창
$("#reply").on("click", ".replyLi .rmodify", function(){
	var reply = $(this).parent();
	
	var rno = reply.attr("data-rno");
	var rcontent = reply.find(".rcontent").text();
	var rwriter = reply.find(".rwriter").text();
	
	$("#modalRno").val(rno);
	$("#modalRwriter").val(rwriter);
	$("#modalRcontent").val(rcontent);
	$("#modBox").show("slow");
});
// 댓글 수정
$("#replyModBtn").on("click", function(){
	var reply = $(this).parent().parent();
	var rno = reply.find("#modalRno").val();
	var rcontent = reply.find('#modalRcontent').val();
	$.ajax({
		type : "put",
		url : "/reply/" + rno,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "PUT"
		},
		data : JSON.stringify(
			{rcontent : rcontent}	
		),
		dataType : "text",
		success : function(result) {
			console.log("result : " + result);
			if(result == "modifySuccess"){
				alert("댓글 수정 완료");
				getPageList(replyPage);
				$("#modBox").hide("slow");
			}
		}
	});
});
// 댓글 삭제
$("#replyDelBtn").on("click",function(){
	var reply = $(this).parent().parent();
	var rno = reply.find("#modalRno").val();
	var state = $(this).parent().find("#state").val();
		$.ajax({
			type : "post",
			url : "/reply/" + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			data : JSON.stringify(
					{state : state}	
				),
			dataType : "text",
			success : function(result) {
				console.log("result : " + result);
				if(result == "deleteSuccess"){
					alert("댓글 삭제 완료");
					getPageList(replyPage);
					$("#modBox").hide("slow");
				}
			}
		});
});
function getFormatDate(date){
	date = new Date(parseInt(date));
	var year = date.getFullYear();
	var month = date.getMonth();
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	return year + "-" +  month + "-" + day +"  "+ hour + ":" + minute + ":" + second;
}
function getPageList(page){
	$.getJSON("/reply/"+bno+"/"+page, function(data){
		console.log(data.list.length);
		
		var str = "";
		
		$(data.list).each(function(){
			if(this.state == 'R'){
			str += "<li data-rno='"+this.rno+"' class='replyLi'>"
				+ "<span class='rwriter' style='font-size:20px;'>" + this.rwriter + "</span>" 
				+ "<span class='regdate' style='font-size:13px;'>" + getFormatDate(this.regdate) + "</span>"  
				+ "<p class='rcontent'>" + this.rcontent + "</p>"
				+ "<button class='rmodify'>수정</button>"
				+ "</li>"
				+ "<hr/>";
			}else if(this.state == 'D'){
			str += "<li data-rno='"+this.rno+"' class='replyLi'>"
				+ "<span class='rwriter' style='font-size:20px;'>" + this.rwriter + "</span>"
				+ "<p style='color:#FF0000; font-size:20px; text-align:center;'><strong>-삭제된 답글입니다.-</strong></p>"
				+ "</li>"
				+ "<hr/>";
			}

		});	
		$("#reply").html(str);
		printPaging(data.pageMaker);
	});
}
function printPaging(pageMaker) {
	var str = "";
	
	if(pageMaker.prev) {
		str += "<li><a href='"+(pageMaker.startPage-1)+ "'> << </a></li>";
	}
	for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
		var strClass = pageMaker.cri.page == i?'class=active':'';
		str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
	}
	$('.pagination').html(str);
}

var replyPage = 1;
$(".pagination").on("click", "li a", function(event){
	event.preventDefault();
	replyPage = $(this).attr("href");
	getPageList(replyPage);
}

);

});
</script>
</body>
</html>