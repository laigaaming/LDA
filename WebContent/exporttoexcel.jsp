<%@ page import="java.io.*" %>
<%@page import="util.SimpleExcelWrite"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String fname = "成绩列表";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流

	//下面是对中文文件名的处理
	response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	fname = java.net.URLEncoder.encode(fname, "UTF-8");
	response.setHeader("Content-Disposition", "attachment;filename="
			+ new String(fname.getBytes("UTF-8"), "GBK") + ".xls");
	response.setContentType("application/msexcel");//定义输出类型
	SimpleExcelWrite sw = new SimpleExcelWrite();
	sw.createExcel(os);
%>