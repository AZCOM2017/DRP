package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Session.ShowSessionServlet;
import dao.Userdao;
import manager.PasswordNotCorrectException;
import manager.UserManager;
import manager.UserNotFoundException;
import sun.awt.RepaintArea;
import user.User;

public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决乱码问题
		//必须写在getParameter()方法取值的前面，不然无效
		request.setCharacterEncoding("utf-8");// 服务端，控制台
		response.setContentType("text/html;charset=utf-8");// 客户端，页面
		
		String path = request.getContextPath();//指定应用上下文;作用是取出部署的应用程序名，这样不管如何部署，所用路径都是正确的。
		
		String command = request.getParameter("command");
		if ("login".equals(command)) {
			String userId = request.getParameter("userId");	
			String password =request.getParameter("password");
			try {
				User user = UserManager.getInstance().login(userId, password);
				//将user用户信息保存到session中
				HttpSession session = request.getSession();
				session.setAttribute("user_info", user);//user_info:名字随便设置
				//这里的60单位秒，配置文件里也可以设置，单位分钟
				//设置session过期时间为60分钟
				session.setMaxInactiveInterval(60*60);
				response.sendRedirect(path+"/main.jsp");//重定向
				
				//用自己写的两个运行时异常解决账号或密码输入错误的跳转页面
				//注意看login方法里的自定义抛出的两个异常（关键点）
			} catch (PasswordNotCorrectException e) {
				response.sendRedirect(path+"/loginError.jsp");
			}catch (UserNotFoundException e) {
				response.sendRedirect(path+"/loginError.jsp");
			}
			
		}
		
	
	/*	Userdao userdao = new Userdao();
		int flag = userdao.login(username, password);//执行Userdao里面的login方法判断用户名密码是否正确
		
		if (flag==1) {
			
			//将flag保存到session中
			HttpSession session = request.getSession();
			session.setAttribute("user_info", flag);//user_info:名字随便设置
			
			//这里的60单位秒，配置文件里也可以设置，单位分钟
			//设置session过期时间为60分钟
			session.setMaxInactiveInterval(60*60);
			
			
			response.sendRedirect(path+"/main.jsp");//重定向
		}else {
			response.sendRedirect(path+"/loginError.jsp");
		}*/
		
		/*int password = Integer.parseInt(p);
		response.getWriter().print("欢迎您"+username);
		
		String adminUsername = "admin";
		int adminPassword = 123;
		
		if (username.equals(adminUsername)&&password==adminPassword) {
			response.sendRedirect(path+"/loginSuccess.jsp");//重定向
		}else {
			response.sendRedirect(path+"/loginError.jsp");//重定向;
			request.setAttribute("message", "账号或密码错误，请重新登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);//请求转发,地址栏不会改变

		}*/
	}

	

}
