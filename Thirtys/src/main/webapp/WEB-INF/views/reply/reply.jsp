<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
</body>
</html>