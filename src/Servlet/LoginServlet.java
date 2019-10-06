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
		// �����������
		//����д��getParameter()����ȡֵ��ǰ�棬��Ȼ��Ч
		request.setCharacterEncoding("utf-8");// ����ˣ�����̨
		response.setContentType("text/html;charset=utf-8");// �ͻ��ˣ�ҳ��
		
		String path = request.getContextPath();//ָ��Ӧ��������;������ȡ�������Ӧ�ó�����������������β�������·��������ȷ�ġ�
		
		String command = request.getParameter("command");
		if ("login".equals(command)) {
			String userId = request.getParameter("userId");	
			String password =request.getParameter("password");
			try {
				User user = UserManager.getInstance().login(userId, password);
				//��user�û���Ϣ���浽session��
				HttpSession session = request.getSession();
				session.setAttribute("user_info", user);//user_info:�����������
				//�����60��λ�룬�����ļ���Ҳ�������ã���λ����
				//����session����ʱ��Ϊ60����
				session.setMaxInactiveInterval(60*60);
				response.sendRedirect(path+"/main.jsp");//�ض���
				
				//���Լ�д����������ʱ�쳣����˺Ż���������������תҳ��
				//ע�⿴login��������Զ����׳��������쳣���ؼ��㣩
			} catch (PasswordNotCorrectException e) {
				response.sendRedirect(path+"/loginError.jsp");
			}catch (UserNotFoundException e) {
				response.sendRedirect(path+"/loginError.jsp");
			}
			
		}
		
	
	/*	Userdao userdao = new Userdao();
		int flag = userdao.login(username, password);//ִ��Userdao�����login�����ж��û��������Ƿ���ȷ
		
		if (flag==1) {
			
			//��flag���浽session��
			HttpSession session = request.getSession();
			session.setAttribute("user_info", flag);//user_info:�����������
			
			//�����60��λ�룬�����ļ���Ҳ�������ã���λ����
			//����session����ʱ��Ϊ60����
			session.setMaxInactiveInterval(60*60);
			
			
			response.sendRedirect(path+"/main.jsp");//�ض���
		}else {
			response.sendRedirect(path+"/loginError.jsp");
		}*/
		
		/*int password = Integer.parseInt(p);
		response.getWriter().print("��ӭ��"+username);
		
		String adminUsername = "admin";
		int adminPassword = 123;
		
		if (username.equals(adminUsername)&&password==adminPassword) {
			response.sendRedirect(path+"/loginSuccess.jsp");//�ض���
		}else {
			response.sendRedirect(path+"/loginError.jsp");//�ض���;
			request.setAttribute("message", "�˺Ż�������������µ�¼");
			request.getRequestDispatcher("/login.jsp").forward(request, response);//����ת��,��ַ������ı�

		}*/
	}

	

}
