
$(document).ready(function(){
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
var bno = ${boardVO.bno};
var replyPageNum = 1;
getPageList(1);
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
			getPageList(1);
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