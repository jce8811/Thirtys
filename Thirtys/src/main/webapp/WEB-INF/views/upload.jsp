<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="header/header.jsp"/>
<c:import url="nav/nav.jsp"/>
<body>
<!-- 첨부파일 영역 추가 -->
<div class="fileDrop"></div>
<div class="uploadedList"></div>
<!-- 첨부파일 영역 추가 -->
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
			
			
			if(checkImageType(data)){
				str = "<div><a href='displayFile?fileName="+getImageLink(data)+"'>"
				    + "<img src='displayFile?fileName=" +data+"'/>" 
				    + "</a><small data-src="+data+">[삭제]</small></div>";		
			}else{
				str = "<div><a href='displayFile?fileName="+data+"'>"
					+ getOriginalName(data) + "</a>"
					+ "<small data-src="+data+">[삭제]</small></div>";
			}
			$(".uploadedList").append(str);
		}
	});
});

$(".uploadedList").on("click", "small", function(event){
	
	var that = $(this);
	
	$.ajax({
		url:"deleteFile",
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

</script>
</body>
</html>