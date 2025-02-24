<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%>
<%
	UserDAO userDAO = new UserDAO();
	String code = null;
	String userID = null;
	if(request.getParameter("code") != null){
		// session.getAttribute()는 오브젝트로 반환되기 때문에 String 변환을 해주어야 한다.
		code = request.getParameter("code");
	}
	if(session.getAttribute("userID") != null){
		// session.getAttribute()는 오브젝트로 반환되기 때문에 String 변환을 해주어야 한다.
		userID = (String)session.getAttribute("userID");
	}
	if(userID == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	String userEmail = userDAO.getUserEmail(userID);
	// 이메일 인증메일이 보내졌는지 확인하는 boolean값
	boolean isRight = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
	
	if(isRight == true){
		userDAO.setUserEmailChecked(userID);
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('인증에 성공하셨습니다.');");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 코드입니다.');");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
%>