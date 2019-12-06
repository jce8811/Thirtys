<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<style>
.mypage_img{
border: 1px solid #000;
    float: left;
    width: 36%;
    /* padding: 19px; */
    margin-left: 30px;
    height: 280px;
    background-color:#666;
    margin-right: 40px;
    }
 .mypage_info{
     overflow: hidden;
    padding: 50px;
    margin-left: 30px;
    height: 180px;
    background-color:#e5e8ec;
    }   
</style>
<div id="wrap">
	<div id="info">
		<div class="container">
			<div class="user-side">
				<h2>마이페이지</h2>
			<c:import url="../sidebar/sidebar.jsp"/>	
			</div>
			<div class="mypage-body" style="overflow:hidden; padding-left: 20px;">
				<div class="mypage">
					<div class="mypage_img">
					
					</div>
					<div class="mypage_info">
						<h2 style="font-size:30px;">아이디 : ${userVO.uid}</h2> 
						<h2 style="font-size:30px;">E-MAIL : ${userVO.uemail}</h2>
						<h2 style="font-size:30px;">포인트  : ${userVO.upoint}</h2>
						<h2 style="font-size:30px;">가입날짜 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${userVO.udate}"/></h2>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>
</body>
</html>