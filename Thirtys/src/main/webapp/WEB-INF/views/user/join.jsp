<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:import url="../header/header.jsp"/>
<c:import url="../nav/nav.jsp"/>
<div id="wrap">
	<div id="user">
		<div class="container">
			<div class="user-side">
				<h2>회원가입</h2>
			</div>
			<div class="user-body">
				<div class="join">
					<form class="user-form" action="${path}/user/join" method="post">
						<fieldset>
							<input type="text" name="uemail" id="uemail" class="uemail" placeholder="이메일">
							<input type="text" name="uname" id="uname" class="uname" placeholder="닉네임">
							<input type="password" name="upw" id="upw" class="upw" placeholder="비밀번호">
							<input type="password" name="upwchk" id="upwchk" class="upwchk" placeholder="비밀번호 확인">
						</fieldset>
						<ul class="agree">
							<li>
							<h4>개인정보처리방침</h4>
							<p class="checkbox">
								<label>
									<input type="checkbox">
									<span>동의합니다</span>
								</label>
							</p>
							</li>
							<li>
							<h4>이용약관보기</h4>
							<p class="checkbox">
								<label>
									<input type="checkbox">
									<span>동의합니다</span>
								</label>
							</p>
							</li>
						</ul>
					<div class="btn">
						<button class="btn-join" type="submit">가입하기</button>
						<a class="btn-back">돌아가기</a>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>