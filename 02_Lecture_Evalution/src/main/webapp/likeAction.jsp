<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="likey.LikeyDAO"%>
<%!// 사용자의 현재 IP 주소를 알아내기 위한 함수
	public static String getClientIP(HttpServletRequest request){

	String ip = request.getHeader("X-FORWARDED-FOR");
	
	// Proxy 서버를 사용한 클라이언트에서도 IP 주소를 가져오도록 설정
	if(ip == null || ip.length() == 0){
	 	ip = request.getHeader("Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0){
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0){
		ip = request.getRemoteAddr();
	}
	
	return ip;
	}
%>
<%
	String userID = null;

	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요');");
		script.println("location.href = 'userLogin.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
	
	String evaluationID = null;

	if(request.getParameter("evaluationID") != null){
		evaluationID = request.getParameter("evaluationID");
	}
	
	EvaluationDAO evaluationDAO = new EvaluationDAO();
	
	LikeyDAO likeyDAO = new LikeyDAO();
	
	int result = likeyDAO.like(userID, evaluationID, getClientIP(request));
	
	if( result == 1 ){
		result = evaluationDAO.like(evaluationID);
		if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('추천이 완료되었습니다.');");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();
			return;	
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 추천한 내용입니다..');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
%>