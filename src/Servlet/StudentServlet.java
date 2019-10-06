package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.cosnaming.IiopUrl.Address;

/*@WebServlet("/Servlet/StudentServlet")*///该注解的作用等价于 在web.xml中配置的该servlet的<servlet-mapping>元素中<url-pattern>的配置	
public class StudentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 解决乱码问题
		//必须写在getParameter()方法取值的前面，不然无效
		req.setCharacterEncoding("utf-8");// 服务端，控制台
		resp.setContentType("text/html;charset=utf-8");// 客户端，页面

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		// 在屏幕上打印username和password
		resp.getWriter().print(username);
		resp.getWriter().println(password);
		
		resp.getWriter().println("成功登录");
		
		

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
