package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.cosnaming.IiopUrl.Address;

/*@WebServlet("/Servlet/StudentServlet")*///��ע������õȼ��� ��web.xml�����õĸ�servlet��<servlet-mapping>Ԫ����<url-pattern>������	
public class StudentServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �����������
		//����д��getParameter()����ȡֵ��ǰ�棬��Ȼ��Ч
		req.setCharacterEncoding("utf-8");// ����ˣ�����̨
		resp.setContentType("text/html;charset=utf-8");// �ͻ��ˣ�ҳ��

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		// ����Ļ�ϴ�ӡusername��password
		resp.getWriter().print(username);
		resp.getWriter().println(password);
		
		resp.getWriter().println("�ɹ���¼");
		
		

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
