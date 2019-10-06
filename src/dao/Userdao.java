package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import util.DBUtil;

public class Userdao {
	public int login(String username,String password) {
		Connection connection = DBUtil.getConnection();//��db����һ������ͨ��
		Statement statement = null;//Statement����ִ�о�̬SQL��䣬�������������ɽ���Ķ���
		ResultSet resultSet = null;//�����
		int flag = 0;
		try {
		String sqlString="select * from users where username = '"+username+"'";//�������������username��ȵ��û���
		statement = (Statement) connection.createStatement();//�������ӻ�ȡһ��ִ��SQL���Ķ���
		resultSet = statement.executeQuery(sqlString);//ִ��SQL���
		if (resultSet.next()) {
			if (resultSet.getString("password").equals(password)) {
				flag = 1;
			}
		}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {
				if (connection!=null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
		return flag;
		
	}
	public int register(String username,String age,String sex,String password) {
		Connection connection = DBUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;//PreparedStatement��Statement������
		int flag = 0;
		//�����û�������
		List<String> usernameList = new ArrayList<String>();
		String sqlString = "select username from users";
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			
			//��username�ֶε��������ݴ��뼯����
			while (resultSet.next()) {
				usernameList.add(resultSet.getString("username"));//��username�ֶε��������ݴ��뼯����
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		if (usernameList.contains(username)) {
			flag = 2;
		}else {
			String insertUser = "insert into users(username,age,sex,password) values(?,?,?,?)";
			try {
				//����preparedStatement����
				preparedStatement = (PreparedStatement) connection.prepareStatement(insertUser);//PreparedStatement�ܹ�ִ�в�������SQL���
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, age);
				preparedStatement.setString(3, sex);
				preparedStatement.setString(4, password);
				//ִ��sql���
				preparedStatement.execute();
				flag = 3;
				preparedStatement.close();
				
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally {
				if (connection!=null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
		
	}
	
}


