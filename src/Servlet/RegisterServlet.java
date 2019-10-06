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
				// �����������
				//����д��getParameter()����ȡֵ��ǰ�棬��Ȼ��Ч
				request.setCharacterEncoding("utf-8");// ����ˣ�����̨
				response.setContentType("text/html;charset=utf-8");// �ͻ��ˣ�ҳ��
				
				String path = request.getContextPath();//ָ��Ӧ��������;������ȡ�������Ӧ�ó�����������������β�������·��������ȷ�ġ�

				
				String username=request.getParameter("username");
				String age=request.getParameter("age");
				String sex=request.getParameter("sex");
				String password=request.getParameter("password");
				String repassword=request.getParameter("repassword");
				
				Userdao userdao = new Userdao();
				int flag = userdao.register(username, age, sex, password);
				if (flag==2) {
					//request.setAttribute("message", "�û����Ѵ��ڣ���");
					//request.getRequestDispatcher("/register.jsp").forward(request, response);//����ת��,��ַ������ı�
					response.sendRedirect(path+"/registerError.jsp");
				}
				if (flag==3) {
					response.sendRedirect(path+"/registerSuccess.jsp");
				}
				
				
	}

}
