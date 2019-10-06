package manager;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import user.User;
import util.DBUtil;
import util.PageModel;

import com.mysql.jdbc.Statement;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;
/**
 * ���õ��������û�
 * @author Az
 *
 */

public class UserManager {
	
	private static UserManager instance = new UserManager();
	
	public UserManager() {}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	/**
	 * ����û�
	 * @param user
	 */
	public void addUser(User user) {
		String sql = "insert into t_user (user_id,user_name,password,contact_tel,email,create_date) "
				+ "values(?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getContactTel());
			preparedStatement.setString(5,user.getEmail());
			preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			DBUtil.close(connection);
			DBUtil.close(preparedStatement);
		}
		
	}
	
	/**
	 * �����û���ѯ����
	 * @param userId
	 * @return ������ڷ���User���󣬷��򷵻�null
	 */
	public User findUserById(String userId) {
		String sql = "select user_id,user_name,password,contact_tel,email,create_date from t_user "
				+ "where user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		
	
		try {
			connection = DBUtil.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getString("user_id"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPassword(resultSet.getString("password"));
				user.setContactTel(resultSet.getString("contact_tel"));
				user.setEmail(resultSet.getString("email"));
				user.setCreateDate(resultSet.getTimestamp("create_date"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return user;
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param pageNo �ڼ�ҳ
	 * @param pageSize ÿҳ����������
	 * @return PageModel
	 */
	public PageModel findUserList(int pageNo,int pageSize) {
		/*
		 * Oracle���ݿ��з�ҳ��ѯ
		 * 
		 * StringBuffer sbSql = new StringBuffer();
		sbSql.append("select user_id,user_name,password,contact_tel,email,create_date")
		.append("from")
		.append("(")
		.append("select rownum rn,user_id,user_name,password,contact_tel,email,create_date" )
		.append("from")
		.append("(")
		.append("select user_id,user_name,password,contact_tel,email,create_date from t_user where user_id <> 'root' order by user_id")
		.append(")where rownum <= ?")
		.append(")where rn > ?");*/
		
		String sql = "select * from t_user where user_id <> 'root' order by user_id limit ?, ?;";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	PageModel pageModel = null;
	

	try {
		connection = DBUtil.getConnection();
		/*preparedStatement = (PreparedStatement) connection.prepareStatement(sbSql.toString());*/
		preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
		//���?ռλ��
		/*preparedStatement.setInt(1, pageNo*pageSize);
		preparedStatement.setInt(2, (pageNo-1)*pageSize);*/
		preparedStatement.setInt(1, (pageNo-1)*pageSize);
		preparedStatement.setInt(2, pageSize);
		resultSet = preparedStatement.executeQuery();
		List userList = new ArrayList();
		while (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getString("user_id"));//��ǰ����ָ���е�ֵ,ע�����е�ֵ
			user.setUserName(resultSet.getString("user_name"));
			user.setPassword(resultSet.getString("password"));
			user.setContactTel(resultSet.getString("contact_tel"));
			user.setEmail(resultSet.getString("email"));
			user.setCreateDate(resultSet.getTimestamp("create_date"));
			userList.add(user);
			
		}
		pageModel = new PageModel();
		pageModel.setList(userList);
		pageModel.setTotalRecords(getTotalRecords(connection));
		pageModel.setPageSize(pageSize);
		pageModel.setPageNo(pageNo);
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		DBUtil.close(resultSet);
		DBUtil.close(preparedStatement);
		DBUtil.close(connection);
	}
	return pageModel;
	}
	
	/**
	 * ȡ���ܼ�¼��
	 * @param connection
	 * @return
	 */
	private int getTotalRecords(Connection connection) {
		String sql = "select count(*) from t_user where user_id <> 'root'";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
		}
		
		
		return count;
	}
	
	/**
	 * �޸��û�
	 * @param user
	 */
	public void modifyUser(User user) {
		//StringBuffer stringBuffer = new StringBuffer();
		//StringBuilder��StringBufferЧ�ʸ��ߣ���Ϊ�������̰߳�ȫ(����Ҫ�����߳�ִ��������ִ��)����Щ�ط�����Ҫ�̰߳�ȫ����������
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update t_user set user_name = ?,password = ?,contact_tel = ?,email = ?")
		.append("where user_id = ?");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBUtil.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(stringBuilder.toString());
			
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getContactTel());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUserId());
			//ִ�и���
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
	/**
	 * �����û�����ɾ���û�
	 * @param userId
	 */
	public void delUser(String userId) {
		String sql = "delete from t_user where user_id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
			
			preparedStatement.setString(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
	/**
	 * ����ɾ���û�
	 * 
	 * ����һ��������ɾ��
	 * ֻ�ύһ��
	 * @param userIds
	 */
	public void delUser(String userIds[]) {
		
	}
	
	/**
	 * �û���¼
	 * @param userId
	 * @param password
	 * @return
	 */
	public User login(String userId,String password) {
		User user = findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("�û��������");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new PasswordNotCorrectException("�û��������");
		}
		return user;
		
	}

}
