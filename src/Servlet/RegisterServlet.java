package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Userdao;

public class RegisterServlet extends HttpServlet{
	@Override
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

				
				String username=request.getParameter("username");
				String age=request.getParameter("age");
				String sex=request.getParameter("sex");
				String password=request.getParameter("password");
				String repassword=request.getParameter("repassword");
				
				Userdao userdao = new Userdao();
				int flag = userdao.register(username, age, sex, password);
				if (flag==2) {
					//request.setAttribute("message", "用户名已存在！！");
					//request.getRequestDispatcher("/register.jsp").forward(request, response);//请求转发,地址栏不会改变
					response.sendRedirect(path+"/registerError.jsp");
				}
				if (flag==3) {
					response.sendRedirect(path+"/registerSuccess.jsp");
				}
				
				
	}

}
