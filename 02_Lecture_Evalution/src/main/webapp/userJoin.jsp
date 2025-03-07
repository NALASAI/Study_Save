<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
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
		if(userID != null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인한 상태입니다.');");
			script.println("location.href = 'index.jsp';");
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
	<section class="container mt-3" style="max-width: 560px; height: 250px;">
		<form method="post" action="./userRegisterAction.jsp">
			<div class="form-group">
				<label>아이디</label>
				<input type="text" name="userID" class="form-control">
			</div>
			<div class="form-group">
				<label>비밀번호</label>
				<input type="password" name="userPW" class="form-control">
			</div>
			<div class="form-group">
				<label>이메일</label>
				<input type="email" name="userEmail" class="form-control">
			</div>
			<button type="submit" class="btn btn-primary" style="margin-top: 1rem;">회원가입</button>
		</form>
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