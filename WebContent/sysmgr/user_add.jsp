<%@page import="com.mysql.jdbc.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.*" %>
<%@ page import="manager.*" %>
<%@ page import="util.*" %>
<%@ page import="com.mysql.jdbc.PreparedStatement" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.jdbc.Statement" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

//解决乱码问题(包括解决Navicat_mysql里的乱码)
//必须写在getParameter()方法取值的前面，不然无效
request.setCharacterEncoding("utf-8");// 服务端，控制台
response.setContentType("text/html;charset=utf-8");// 客户端，页面


String command = request.getParameter("command");

String userId ="";
String userName = "";
String password = "";
String contactTel = "";
String email = "";

//if(command != null && command.equals("add")){
	if("add".equals(command)){
		if(UserManager.getInstance().findUserById(request.getParameter("userId")) == null){
			User user = new User();
			user.setUserId(request.getParameter("userId"));
			user.setUserName(request.getParameter("userName"));
			user.setPassword(request.getParameter("password"));
			user.setContactTel(request.getParameter("contactTel"));
			user.setEmail(request.getParameter("email"));
			
				
				/* 普通方法调用 */
				/* UserManager userManager = new UserManager();
				userManager.addUser(user); */
				
				/* 单例模式调用 */
				UserManager.getInstance().addUser(user); 
				out.print("添加用户成功");
		}else{	
			userId = request.getParameter("userId");
			userName = request.getParameter("userName");
			password = request.getParameter("password");
			contactTel = request.getParameter("contactTel");
			email = request.getParameter("email");
			
			out.print("用户代码"+userId+"已经存在");
		}
	
	

}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户维护</title>
		
<!-- --------------------------------------------------------------------------------------------------	 -->	
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- -------------------------------------------------------------------------------------------------- -->

<!-- 引入user_add.js -->
<script src="../js/user_add.js"></script>

	</head>
	<body class="body1" onload="init()">
		<div style="margin-left: 100px;margin-top: 50px;margin-right: 150px;">
			<span class="glyphicon glyphicon-forward" style="font-size: 20px;"> <span style="font-size: 25px;font-weight: bold;">
			系统管理>>用户维护>>添加</span></span>
			<hr style="height:3px;border:none;border-top:3px double black;" />
		</div>
		
		<div style="margin-left: 500px;">
			<form id="userForm">
			<input type="hidden" name="command" value="add"/>
			用户代码：<input id="userId" name="userId" value="<%=userId%>" onblur="validate(this)"/><span id="spanUserId" value=""></span></p>
			用户名称：<input id="userName" name="userName" value="<%=userName%>"/></p>
			密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：<input type="password" id="password" name="password" value="<%=password%>"/></p>
			联系电话：<input maxlength="11" id="contactTel" name="contactTel" value="<%=contactTel%>"/></p>
			email：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input id="email" name="email" value="<%=email%>"/>

		</div>
		<div style="margin-top: 50px;margin-left: 600px;">
			<span><button type="button" class="btn btn-default" onclick="addUser()" id="add_button">添加</button></span>
			<span style="margin-left: 30px;"><button type="button" class="btn btn-default"><a href="user_maint.jsp">返回</a></button></span>
			
		</div>
		</form>
	</body>
</html>
