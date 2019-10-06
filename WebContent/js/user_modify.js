/**
 * Author:Az
 * Date:2019-9-16
 */
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
					