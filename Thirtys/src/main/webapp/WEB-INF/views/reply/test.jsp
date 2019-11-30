<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Ajax Test Page</h2>
	<div>
		<div>
			RWIRTER <input type="text" name="rwriter" id="rwriter">
		</div>
		<div>
			RCONTENT <input type="text" name="rcontent" id="rcontent">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="reply">
	
	</ul>
<script src="${path}/resources/js/jquery.min_1.12.4.js"></script>	
<script>

// 8177번째 게시물

//댓글 목록 출력 함수
function getAllList(){

	var bno = 8177;
	$.getJSON("${path}/reply/all/" + bno, function(data){
		var str = "";
		console.log(data.length);
		
		$(data).each(function(){
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
					+ this.rno + ":" + this.rcontent + "</li>";
			});
		$("#reply").html(str);
	});
}
var bno = 8177;
getAllList();
$("#replyAddBtn").on("click", function(){
	
	var rwriter = $("#rwriter").val();
	var rcontent = $("#rcontent").val();
	
	$.ajax({
		type : "post",
		url : "${path}/reply",
		header : {
			"Content-Type" : "application/json; charset=UTF-8",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : "text",
		data : JSON.stringify({
			bno : bno,
			rwriter : rwriter,
			rcontent : rcontent
		}),
		success : function(result) {
			if(result == "regSuccess") {
				alert("등로 완료.");
			}
			getAllList();
		}
	});
});
</script>

</body>
</html>