<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.swing.JOptionPane" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
<div align="center">

<form action="Servlet/RegisterServlet" method="post">
姓名：	<input type="text" name="username" required="true"><p>
年龄：	<input type="text" name="age" required="true"><p>
性别：	<input type="text" name="sex" required="true"><p>
密码：       <input type="password" name="password" id="password" required="true"><p>
确认密码：<input type="password" name="repassword" id="repassword" onkeyup="validate()" required="true"><p>
<div id="hint"></div>
<button type="submit" id="submit">注册</button>&nbsp;&nbsp;&nbsp;&nbsp<button type="reset">重置</button><p><a href="login.jsp">重新登录</a>

</form>
</div>
<%-- <%
if(request.getAttribute("message")!=null){
	String message =request.getAttribute("message").toString();
	String path = request.getContextPath();//指定应用上下文;作用是取出部署的应用程序名，这样不管如何部署，所用路径都是正确的。
	response.sendRedirect(path+"/register.jsp");
	out.write("<script>alert('用户名已经存在！！');</script>");
	/* 第一个参数是控制弹出对话框相对的中心位置，如果是null
	则是在屏幕中间，如果是其它组件参数，则会在其它组件的中心弹出。 */
	//JOptionPane.showConfirmDialog(null, message);//不能用这个方法
	
}
%> --%>

</body>
<script>
/*验证输入的两次密码是否一致 */

	function validate(){
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		if(password!=repassword){
			document.getElementById("hint").innerHTML="<font color='red'>两次输入的密码不一致</font>";
			document.getElementById("submit").disabled = true;
		}else{
			document.getElementById("hint").innerHTML="<font color='green'>两次输入的密码一致</font>";
			document.getElementById("submit").disabled = false;
		}
	
}
</script>
</html>