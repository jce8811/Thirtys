<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>30's</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="board">
		<div class="container">
			<div class="board-header">
			<h2>갤러리</h2>
			</div>
			<div class="write-body">
				<div class="write">
				<form role="form" id="writeForm" method="post" action="${path}/board/write">
					<div class="w-writer">
						<input type="text" id="writer" name="writer" >
					</div>
					<div class="w-title">
						<input type="text" id="title" name="title" placeholder="제목">
					</div>
					<div class="w-insert">
						<textarea id="content" name="content" rows="30" placeholder="내용"></textarea>
					</div>
					<!-- 첨부파일 영역 추가 -->
					<div class="uploadedList"></div>
					<div class="fileDrop">파일을 드래그 해 주세요</div>
					<!-- 첨부파일 영역 추가 -->
					<div class="btn-box02">
						<input type="hidden" name="bno" value="${boardVO.bno}">
						<input type="hidden" name="page" value="${cri.page}">
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
						<a class="btn01" href="${path}/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}">목록</a>
						<button type="reset">초기화</button>
						<button type="submit">작성</button>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});
$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	var files = event.originalEvent.dataTransfer.files;
	var file = files[0];
	var formData = new FormData();
	formData.append("file", file);
	
	$.ajax({
		type : "POST",
		url : "/upload",
		data : formData,
		dataType : "text",
		processData : false,
		contentType : false,
		success:function(data){
			var str = "";
			var fileInfo = getFileInfo(data);
			
			  if(checkImageType(data)){
				str = "<div><a href ='"+fileInfo.getLink+"'>"+fileInfo.fileName+"<br/>"
				    + "<img src='/displayFile?fileName=" +data+"'/>" 
				    + "<input type='hidden' class='file' value='"+fileInfo.fullname+"'>";		
				    + "</a><small data-src="+data+">[삭제]</small></div>";		
			}else{
				str = "<div><a href='${path}/displayFile?fileName="+data+"'>"
					+ getOriginalName(data) + "</a>"
					+ "<input type='hidden' class='file' value='"+fileInfo.fullname+"'>";
					+ "<small data-src="+data+">[삭제]</small></div>";
			} 
			$(".uploadedList").append(str);
		}
	});
});

 function fileSubmit(that) {
	
} 

 $("#writeForm").submit(function(event){
	event.preventDefault();

	var that = $(this);
	var str = "";
	$(".uploadedList .file").each(function(i){
		str += "<input type='hidden' name='files["+i+"]' value='"+$(this).val()+"'>";
	});
	that.append(str);
	that.get(0).submit();
	
}); 

$(".uploadedList").on("click", "small", function(event){
	
	var that = $(this);
	
	$.ajax({
		url:"/deleteFile",
		type:"POST",
		data: {fileName:$(this).attr("data-src")},
		dataType : "text",
		success:function(result){
			if(result == 'deleted'){
				alert("삭제되었습니다.");
				that.parent("div").remove();
			}
		}
	});
});


function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getImageLink(fileName){
	
	if(!checkImageType(fileName)){
		return;
	}
	var front = fileName.substr(0,12);
	var end = fileName.substr(14);
	
	return front + end;
}

function getOriginalName(fileName){
	if(checkImageType(fileName)){
		return;
	}
	var idx = fileName.indexOf("_") + 1;
	return fileName.substr(idx);
}

function getFileInfo(fullname){
	
	var filename, imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullname)){
		imgsrc = "/displayFile?fileName="+fullname;
		fileLink = fullname.substr(14);
		
		var front = fullname.substr(0,12)
		var end = fullname.substr(14);
		
		getLink = "/displayFile?fileName="+front+end;
	}else {
		imgsrc = "/resources/image/file.png";
		fileLink = fullname.substr(12);
		getLink = "/displayFile?filename="+fullname;
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullname:fullname};
}

</script>
</body>
</html>