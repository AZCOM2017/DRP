<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="manager.*" %>
<%@ page import="util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="user.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

//删除用户
String command = request.getParameter("command");
if("delete".equals(command)){
	String[] userIds = request.getParameterValues("selectFlag");
	for(int i=0;i<userIds.length;i++){
		UserManager.getInstance().delUser(userIds[i]);
		//out.println(userIds[i]);
	}
	out.print("成功删除用户");
} 

int pageNo = 1;//刚进页面是第1页
int pageSize = 2;//1页显示2条数据
String pageNoString = request.getParameter("pageNo");
if(pageNoString != null){
	pageNo = Integer.parseInt(pageNoString);//String转成int类型
}

PageModel pageModel = UserManager.getInstance().findUserList(pageNo, pageSize);

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
	</head>
	<body>
		
	<div style="margin-left: 100px;margin-top: 50px;margin-right: 150px;">
		<span class="glyphicon glyphicon-forward" style="font-size: 20px;"> <span style="font-size: 25px;font-weight: bold;">
		系统管理>>用户维护</span></span><span style="margin-left: 860px;font-size: 20px;text-decoration: none;"><a href="../main.jsp">返回主界面</a></span>
		<hr style="height:3px;border:none;border-top:3px double black;" />
		

		<table class="table table-striped">
  <caption>查询列表</caption>
  <thead>
    <tr>
      <th><input type="checkbox" onclick="checkAll()" id="checkAll"></th>
      <th>用户代码</th>
      <th>用户名称</th>
	  <th>联系电话</th>
	  <th>Email</th>
	  <th>创建日期</th>
    </tr>
  </thead>
  <tbody>
  		<form name="userForm" id="userForm">
  <%
  List userList = pageModel.getList();
  for (int i = 0; i < userList.size(); i++) {
	  User user = (User)userList.get(i);
      //System.out.println(userList.get(i));

  %>
    <tr>
      <td><input type="checkbox" name="selectFlag" value="<%=user.getUserId()%>"></td>
      <td><%=user.getUserId()%></td>
      <td><%=user.getUserName()%></td>
	  <td><%=user.getContactTel()%></td>
      <td><%=user.getEmail()%></td>
	  <td><%=new SimpleDateFormat("yyyy-MM-dd").format(user.getCreateDate())%></td>
    </tr>
    <%} %>
	</form>
	 <tr bgcolor="#f6f5ec">
	  <td style="font-size: 20px;">共<%=pageModel.getTotalPages() %>页</td>
	  <td style="font-size: 20px;">当前第<%=pageModel.getPageNo() %>页</td>
	  <td style="font-size: 15px;"><button title="首页" onClick="topPage()"><<</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button title="上一页" onClick="previousPageNo()"><</button></td>
	  <td style="font-size: 15px;"><button title="下一页" onClick="nextPageNo()">></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button title="尾页" onClick="bottomPage()">>></button></td>
	  
	  <td style="font-size: 15px;"><button><a href="user_add.jsp">添加</a></button>&nbsp;&nbsp;&nbsp;<button onClick="deleteUser()">删除</button>&nbsp;&nbsp;&nbsp;<button onClick="modifyUser()">修改</button></td>
	  
	</tr>
  </tbody>
 
</table>

</div>

<!-- 修改用户验证 -->
<script>
	
	//选择所有用户
	function checkAll(){
		var selectFlags = document.getElementsByName("selectFlag");
		var checkAll = document.getElementById("checkAll");
		for(var i=0;i<selectFlags.length;i++){
				
			if(checkAll.checked == false){
				selectFlags[i].checked = false;
			}else{
				selectFlags[i].checked = true;
			}
			
		
		}
		
	}
	
	//点击跳到首页
	function topPage(){
		window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getTopPageNo() %>";
	}
	
	//点击跳到尾页
	function bottomPage(){
		window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getBottomPageNo()%>";
	}
	
	//点击跳到上一页
	function previousPageNo(){
		window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getPreviousPageNo()%>";
	}
	
	//点击跳到下一页
	function nextPageNo(){
		window.self.location = "user_maint.jsp?pageNo=<%=pageModel.getNextPageNo()%>";
	}
	
	//点击删除用户判断
	function deleteUser(){
		var selectFlags = document.getElementsByName("selectFlag");
		var flag = false;//定义一个标记
		for(var i=0;i<selectFlags.length;i++){
				if(selectFlags[i].checked){
					flag = true;
					if(window.confirm("确认删除吗？")){
						with (document.getElementById("userForm")){
							action="user_maint.jsp?command=delete";
							method="post";
							submit();
						}
					}
					break;//跳出循环
				}
		}
		if(!flag){
			alert("请选择要删除的用户");
		}
		
		
	}
	
	//点击修改用户判断
	function modifyUser(){
		var selectFlags = document.getElementsByName("selectFlag");
		var count = 0;
		var j = 0;
		for(var i=0;i<selectFlags.length;i++){
			if(selectFlags[i].checked){
				var j = i//拿到选中的下标
				count++;
			}
		}
		if(count == 0){
			alert("请选择要修改的用户");
		}
		//选中一个用户修改count==1
		if(count ==1){
			window.location.href = "user_modify.jsp?userId=" + selectFlags[j].value;
		}
		if(count > 1){
			alert("一次只能修改一个用户");
		}
			
	}
	
</script>

	</body>
</html>

