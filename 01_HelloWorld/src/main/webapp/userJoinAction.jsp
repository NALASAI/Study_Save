<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
	String userID = null;
	String userPW = null;
	if(request.getParameter("userID") != null){/*아이디에 값이 있다면 발동*/
		userID = (String) request.getParameter("userID");
	}
	if(request.getParameter("userPW") != null){/*비밀번호에 값이 있다면 발동*/
		userPW = (String) request.getParameter("userPW");
	}
	if(userID == null || userPW == null){/*아이디와 비밀번호 중 하나라도 없으면 발동*/
		PrintWriter script = response.getWriter(); /*스크립트 처리*/
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	UserDAO userDAO = new UserDAO();
	int result = userDAO.join(userID, userPW);
	if(result == 1){/*결과가 제대로 들어갈 시 발동*/
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입에 성공했습니다.');");
		script.println("location.href = 'index.jsp';");
		script.println("</script>");
		script.close();
		return;
	}
%>