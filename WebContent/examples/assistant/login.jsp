<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	session.setAttribute("userId", 1L); 	
	String key = "init";
	Object value = session.getAttribute(key);
	Integer times = (value != null)?(Integer)value:0;
	times++;
	session.setAttribute(key, times);
	DateFormat format = new SimpleDateFormat("HH:mm:ss");
	out.println("第" + times + "次登录, 当前时间: " + format.format(new java.util.Date()));
%>
</body>
</html>