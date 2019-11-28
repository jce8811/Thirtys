<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div id="nav">
	<div class="container">
		<div class="logo"><a href="${path}/">30's</a></div>
		<div class="nav-total">
			<div class="nav">	
				<ul>
					<li><a href="#">공지사항</a></li>
					<li><a href="${path}/board/listPage">게시판</a></li>
					<li><a href="#">질문게시판</a></li>
					<li><a href="#">아이콘 구매</a></li>
				</ul>
			</div>	
			<div class="nav-sub">
				<ul>
					<li><a href="#">공지사항</a></li>
					<li><a href="#">이벤트</a></li>
					<li><a href="#"></a></li>
					<li><a href="#"></a></li>
				</ul>
				<ul>
					<li><a href="${path}/board/listPage">자유게시판</a></li>
					<li><a href="#">갤러리</a></li>
					<li><a href="#">고민게시판</a></li>
				</ul>
				<ul>
					<li><a href="#">질문게시판</a></li>
					<li><a href="#">QnA</a></li>
					<li><a href="#">관리자문의</a></li>
				</ul>
				<ul>
					<li><a href="#">아이콘 구매</a></li>
					<li><a href="#">회원 랭킹</a></li>
				</ul>
			</div>
		</div> 
		
	</div>	
</div>
	<!-- //nav -->
<script>
function nav_dropdown() {
	$(".nav-total").hover(function() {
		$(".nav-sub").fadeIn();
	},
	function() {
		$(".nav-sub").hide();
	});
}
$(document).ready(function() {
	nav_dropdown();
});
</script>