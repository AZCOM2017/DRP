<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User user = (User)session.getAttribute("user_info");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
<style>
body {
  padding: 0;
  margin: 0;
  background-color: #324252;
}

h1 {
  font-family: 'Century Gothic';
  margin: 30px auto 30px auto;
  color: #09fbd2;
  width: 100%;
  text-align: center;
}

ul.menu {
  padding: 0;
  list-style: none;
  width: 400px;
  margin: 20px auto;
  font-family: 'Century Gothic';
  box-shadow: 0px 0px 25px #00000070;
  clear: both;
  display: table;
}
ul.menu .list {
  font-size: 14px;
  border-bottom: 1px solid #324252;
  position: relative;
  width: 100%;
  box-sizing: border-box;
  height: 50px;
  vertical-align: sub;
  background: #3e5165;
  clear: both;
}
ul.menu .list:after {
  content: "\f107";
  font-family: FontAwesome;
  position: absolute;
  right: 17px;
  top: 17px;
  padding: 0px 5px;
  color: #fff;
}
ul.menu .list:before {
  content: '\f07b';
  font-family: FontAwesome;
  position: absolute;
  left: 17px;
  top: 17px;
  padding: 0px 5px;
  color: #fff;
}
ul.menu .list a {
  text-decoration: none;
  color: #fff;
  padding: 17px 0px 17px 45px;
  display: block;
  height: 100%;
  box-sizing: border-box;
}
ul.menu .list a:hover {
  background-color: #324252;
  transition: 300ms all;
  color: #09fbd2;
}
ul.menu .list .items {
  height: 0px;
  overflow: hidden;
}
ul.menu .list .items a {
  padding: 17px;
}
ul.menu .list .items a:hover {
  background-color: #3f5d79;
  color: #fff;
  transition: 300ms all;
}
ul.menu .list:last-child {
  border-bottom: none;
}
ul.menu .active:after {
  content: "\f106";
  font-family: FontAwesome;
  position: absolute;
  right: 17px;
  top: 17px;
  padding: 0px 5px;
  color: #fff;
}
ul.menu .active:before {
  content: '\f07c';
  font-family: FontAwesome;
  position: absolute;
  left: 17px;
  top: 17px;
  padding: 0px 5px;
  color: #fff;
}
ul.menu .active > .items {
  display: block;
  background: #23313f;
  padding: 0px;
  height: auto;
  color: #fff;
  transition-timing-function: cubic-bezier(0.075, 0.82, 0.165, 1);
  transition: all 200ms;
  clear: both;
  float: left;
  width: 100%;
}
ul.menu .active > .items li {
  padding: 0px;
  border-bottom: 1px solid #324252;
  list-style: none;
}
ul.menu .active > .items li:last-child {
  border-color: transparent;
  padding-bottom: 0px;
}
ul.menu .active > .items .active > .items {
  background-color: #2f4b67;
}
ul.menu .active > a {
  color: #46efa4;
  text-transform: uppercase;
  font-weight: bold;
}
ul.menu .active .list {
  background: #697d92;
}
ul.menu .active .list a {
  padding: 17px 0px 17px 45px;
}

#reLoginClick{
 text-decoration:none;
	background:#009ad6;
	color:#f2f2f2;
	padding: 10px 170px 10px 170px;
	font-size:16px;
	font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;
	font-weight:bold;
	border-radius:3px;
	
	-webkit-transition:all linear 0.30s;
	-moz-transition:all linear 0.30s;
	transition:all linear 0.30s;
	
	}
   #reLoginClick a:hover { background:#385f9e; }
   
   #div1{
   text-align:center;
   margin-top:40px;
   }
   
   #h1{
   margin-top:130px;
   }
</style>
</head>
<body>

<%
request.setCharacterEncoding("utf-8");// 服务端，控制台
response.setContentType("text/html;charset=utf-8");// 客户端，页面
%>


<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">





<h1 id="h1"><%=user.getUserName() %>！欢迎您！！</h1>

<ul class="menu">
   <li class="list"><a href="#">系统管理 </a> 
      <ul class="items">
         <li><a href="sysmgr/user_maint.jsp"> 用户维护</a></li>
         <li><a href="#"> 修改密码</a></li>
      </ul>
   </li>
   <li class="list"><a href="#">List 2</a> 
      <ul class="items">
         <li> <a href="#" > Item 2-1 </a></li>
         <li> <a href="#" > Item 2-2 </a></li>
         <li> <a href="#" > Item 2-3 </a></li>
      </ul>
   </li>
   <li class="list"><a href="#">List 3</a> 
      <ul class="items">
         <li> <a href="#" > Item 3-1 </a></li>
         <li> <a href="#" > Item 3-2 </a></li>
         <li> <a href="#" > Item 3-2 </a></li>
      </ul>
   </li>
   <li class="list"><a href="#">List 4</a> 
      <ul class="items">
         <li> <a href="#" > Item 4-1 </a></li>
         <li> <a href="#" > Item 4-2 </a></li>
         <li class="list"><a href="#">List 4-1</a> 
            <ul  class="items">
               <li> <a href="#" > Item 4-1-1</a></li>
               <li> <a href="#" > Item 4-1-2</a></li>
               <li class="list"><a href="#">List 4-2</a> 
                  <ul  class="items">
                     <li> <a href="#" > Item 4-2-1</a></li>
                     <li> <a href="#" > Item 4-2-2</a></li>
                  </ul>
               </li>
            </ul>
         </li>
      </ul>
   </li>
</ul>




<div id="div1">
<a id="reLoginClick" href="login.jsp"><font color="white">重新登录</font></a>
</div>


<script>
var list = document.querySelectorAll('.list');

function accordion(e) {
    e.stopPropagation();
    if (this.classList.contains('active')) {
        this.classList.remove('active');
    } else
    if (this.parentElement.parentElement.classList.contains('active')) {
        this.classList.add('active');
    } else
    {
        for (i = 0; i < list.length; i++) {
            list[i].classList.remove('active');

        }
        this.classList.add('active');
    }
}
for (i = 0; i < list.length; i++) {
    list[i].addEventListener('click', accordion);
}
</script>

<!-- <div style="text-align:center;clear:both;">
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div> -->
</body>
</html>