<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 사용자의 클라이언트 세션정보 모두 파기
	session.invalidate();
%>
<script>
	location.href='index.jsp';
</script>