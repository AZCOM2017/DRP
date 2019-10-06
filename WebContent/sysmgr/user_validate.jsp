<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="manager.*" %>
<%
//解决乱码问题(包括解决Navicat_mysql里的乱码)
//必须写在getParameter()方法取值的前面，不然无效
request.setCharacterEncoding("utf-8");// 服务端，控制台
response.setContentType("text/html;charset=utf-8");// 客户端，页面

String userId = request.getParameter("userId");
if(UserManager.getInstance().findUserById(userId) != null){
	out.print("用户代码已经存在");
}
%>
