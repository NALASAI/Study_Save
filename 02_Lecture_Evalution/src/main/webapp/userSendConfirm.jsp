<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="user.UserDAO"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 부트스트랩의 경우 기본적으로 반응형 웹이라고 볼 수 있기때문에 아래와 같은 내용을 추가한다. -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>강의평가 웹사이트</title>
		<!-- 부트스트랩 css 추가하기 -->
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
		<!-- 커스텀 css 추가하기 -->
		<link rel="stylesheet" type="text/css" href="./css/font_custom.css">	
	</head>
<body>
	<% 
		String userID = null;
	
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 해주세요.');");
			script.println("location.href = 'userLogin.jsp';");
			script.println("</script>");
			script.close();
			return;
		}
	%>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a class="nav-link" href="index.jsp">메인</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" id="dropdown" data-bs-toggle="dropdown">
							회원관리
						</a>
						<div class="dropdown-menu" aria-labelledby="dropdown">
						<%
							if(userID == null){
						%>
							<a class="dropdown-item" href="userLogin.jsp">로그인</a>
							<a class="dropdown-item" href="userJoin.jsp">회원가입</a>
						<%
							}else{
						%>
							<a class="dropdown-item" href="userLogout.jsp">로그아웃</a>
						<%
							}
						%>
						</div>
					</li>
				</ul>
				<form id="search_line" class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search" placeholder="내용을 입력하세요" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
				</form>
			</div>
		</nav>
	<section class="container mt-3" style="max-width: 560px; height: 200px;">
		<div class="alert alert-warning mt-4" role="alert">
			이메일 주소 인증을 하셔야 이용이 가능합니다.
		</div>
		<a href="emailSendAction.jsp" class btn btn-primary>인증 메일 다시받기</a>
	</section>
	<footer class="bg-dark mt-4 p-5 text-center" style="color:#FFFFFF;">
		Copyright &copy, 2025강동욱All Rights Reserved.
	</footer>
	<!-- 제이쿼리 자바스크립트 추가하기 -->
	<script type="text/javascript" src="./js/jquery.min.js"></script>
	<!-- 파퍼 자바스크립트 추가하기 -->
	<script type="text/javascript" src="./js/popper.js"></script>
	<!-- 부트스트랩 자바스크립트 추가하기 -->
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
</body>
</html>