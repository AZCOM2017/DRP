<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body style="opacity: 0.8999;background-image: url(img/login.jpg);background-repeat: no-repeat;">
			<div  style="text-align: center;margin:auto;margin-top: 200px;">
				<!-- margin:auto让div在body内居中 -->
		<form action="Servlet/LoginServlet" method="post">
		<input type="hidden" name="command" value="login">
			<div style="margin-right: 50px;">欢迎登录DRP管理系统！</div>
			<div style="margin-top: 20px;">用户名：<input type="text" name="userId" required="true"></div>
			<div style="margin-top: 10px;">密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" required="true"></div>
			<div style="margin-top: 10px;">验证码：<input type="text" maxlength="6"/></div>
			<div style="margin-top: 50px;"><button type="submit" style="margin-left: 70px;">登录</button>
			<span style="margin-left: 50px;">
			<a href="register.jsp">用户注册</a>
			</span>
			</div>
		</form>
		
		</div>
	</body>
</html>