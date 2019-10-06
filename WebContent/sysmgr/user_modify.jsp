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
if("modify".equals(command)){
	User user = new User();
	user.setUserId(request.getParameter("userId"));
	user.setUserName(request.getParameter("userName"));
	user.setPassword(request.getParameter("password"));
	user.setContactTel(request.getParameter("contactTel"));
	user.setEmail(request.getParameter("email"));
	//调用修改用户方法
	UserManager.getInstance().modifyUser(user);
	out.print("修改用户成功");
}

String userId = request.getParameter("userId");
User user = UserManager.getInstance().findUserById(userId);


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

<!-- 引入user_modify.js -->
<script src="../js/user_modify.js"></script>

	</head>
	<body class="body1">
		<div style="margin-left: 100px;margin-top: 50px;margin-right: 150px;">
			<span class="glyphicon glyphicon-forward" style="font-size: 20px;"> <span style="font-size: 25px;font-weight: bold;">
			系统管理>>用户维护>>修改</span></span>
			<hr style="height:3px;border:none;border-top:3px double black;" />
		</div>
		
		<div style="margin-left: 500px;">
			<form id="userForm">
			<input type="hidden" name="command" value="modify"/>
			用户代码：<input id="userId" name="userId" value="<%=userId%>" readonly="true	"/><span style="color:#F00;">用户代码不能修改</span></p>
			用户名称：<input id="userName" name="userName" value="<%=user.getUserName()%>"/></p>
			密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：<input type="password" id="password" name="password" value="<%=user.getPassword()%>"/></p>
			联系电话：<input maxlength="11" id="contactTel" name="contactTel" value="<%=user.getContactTel()%>"/></p>
			email：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input id="email" name="email" value="<%=user.getEmail()%>"/>

		</div>
		<div style="margin-top: 50px;margin-left: 600px;">
			<span><button type="button" class="btn btn-default" onclick="modifyUser()" id="modify_button">修改</button></span>
			<span style="margin-left: 30px;"><button type="button" class="btn btn-default"><a href="user_maint.jsp">返回</a></button></span>
			
		</div>
		</form>
		
		
		
		<script>
		function modifyUser(){
								var vUserId = document.getElementById("userId");
								//用户代码不能为空
								if (trim(vUserId.value) == "") {
									alert("用户代码不能为空");
									vUserId.focus();
									return;
								}
								
								//用户代码必须为数字或字母,只能为4~6位
								var reg = new RegExp(/^[a-zA-Z0-9]{4,6}$/);//正则表达式
								if(!reg.test(vUserId.value)){
									alert("用户代码必须为数字或字母,只能为4~6位");
									vUserId.focus();
									return;
								}
								
								//用户代码第一个字符必须为字母
								if(!(vUserId.value.charAt(0) >= "a" && vUserId.value.charAt(0) <= "z")){
									alert("用户代码第一个字符必须为字母");
									vUserId.focus();
									return;
								}
					
								
								//用户名称不能为空
								if (trim(document.getElementById("userName").value).length == 0) {
									alert("用户名不能为空s");
									document.getElementById("userName").focus();
									return;
								}
								//密码至少6位
								if (trim(document.getElementById("password").value).length < 6) {
									alert("密码至少6位");
									document.getElementById("password").focus();
									return;
								}
								//电话号码必须为数字
								var contactTel = document.getElementById("contactTel");
								reg.compile(/^[0-9]*$/);//因为前面定义了reg变量,所以采用compile方法重新编译一下.再传入新的正则表达式.
								if (!(reg.test(contactTel.value))) {
									alert("电话号码不合法");
									contactTel.focus();
									return;
								}
								//判断email
								var vEmail = document.getElementById("email");
								reg.compile(/[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/);
								if (!(reg.test(vEmail.value))) {
									alert("电子邮件不合法！");
									vEmail.focus();
									return;
								}
								
								//使用js的方式提交表单
								/* document.getElementById("userForm").action="user_add.jsp";//提交到对应的servlet
								document.getElementById("userForm").method="post";
								document.getElementById("userForm").submit(); */
								
								//使用with关键字可以代替相同的部分,等同上面的写法
								with (document.getElementById("userForm")){
									action="user_modify.jsp";
									method="post";
									submit();
								}
								
								
								
							}
							
							//删除左右两边空格
							function trim(str){
								return str.replace(/(^\s*)|(\s*$)/g,"");
							}
							
							
							//进入页面鼠标定位到userID的焦点
							function init(){
								document.getElementById("userName").focus();
							}
							
		</script>
	</body>
</html>
