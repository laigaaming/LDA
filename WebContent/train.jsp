<%@page import="bll.AdminBLL"
		import="java.io.*"
%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	AdminBLL adminBLL = new AdminBLL();
	response.setContentType("text/html; charset=utf-8");
	PrintWriter pw = response.getWriter();
	if (adminBLL.trainSample()) {
		pw.println("<script>");
		pw.println("alert('更新成功');");
		pw.println("history.back();");
		pw.println("</script>");
	} else {
		pw.println("<script>");
		pw.println("alert('更新失败');");
		pw.println("history.back();");
		pw.println("</script>");
	}
%>