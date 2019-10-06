/**
 * Author:Az
 * Date:2019-8-31
 */
function addUser(){
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
					//alert("用户名称不能为空");
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
					action="user_add.jsp";
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
				document.getElementById("userId").focus();
			}
			
			
			function validate(filed){
				if(trim(filed.value).length != 0){
					var xmlHttpRequest;
					if(window.ActiveXObject) {//IE8
						xmlHttpRequest = new ActiveXObject("MSXML2.XMLHTTP.3.0");
					} else if(window.XMLHttpRequest) {//其他浏览器如Firefox、Chrome等
						xmlHttpRequest = new XMLHttpRequest();
					}
					
					//创建Ajax核心对象XMlHttpRequest
					var url = "user_validate.jsp?userId="+trim(filed.value)+"&time="+new Date().getTime();
					xmlHttpRequest.open("GET",url,true);//true为异步提交
					
					xmlHttpRequest.onreadystatechange = function(){//匿名函数
						//alert(xmlHttpRequest.readyState);
						if(xmlHttpRequest.readyState == 4){//Ajax引擎状态为成功
							if(xmlHttpRequest.status == 200){//HTTP协议状态为成功
								//alert(xmlHttpRequest.responseText);
								if(trim(xmlHttpRequest.responseText) != ""){
									document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttpRequest.responseText + "</font>"
									document.getElementById("add_button").disabled = true;
								}
							}else{
								alert("请求失败,错误码"+xmlHttpRequest.status);
							}
						}
					};
					//将设置信息发送到Ajax引擎，注意只是发送，上面的一切都还没有执行，发送过后才执行。
					xmlHttpRequest.send(null);//如果是GET请求,参数为null就行
				}else{
					document.getElementById("spanUserId").innerHTML = "";
				}
			}
			