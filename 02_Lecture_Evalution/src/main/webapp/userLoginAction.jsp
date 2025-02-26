<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
	String userID = null;
	String userPW = null;

	if(request.getParameter("userID") != null){
		userID = request.getParameter("userID");
	}
	if(request.getParameter("userPW") != null){
		userPW = request.getParameter("userPW");
	}
	if(userID == null || userPW == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	
	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(userID, userPW);
	
	if(result == 1){ // 로그인 성공시
		session.setAttribute("userID", userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}else if(result == 0){ // 비밀번호를 잘못 입력했을 때
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다.')");
		script.println("history.back();'");
		script.println("</script>");
		script.close();
		return;
	}else if(result == -1){ // 아이디가 없을 때
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.')");
		script.println("history.back();'");
		script.println("</script>");
		script.close();
		return;
	}else if(result == -2){ // DB 오류 발생
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.')");
		script.println("history.back();'");
		script.println("</script>");
		script.close();
		return;
	}
%>