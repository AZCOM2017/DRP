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
		Connection connection = DBUtil.getConnection();//与db建立一个连接通道
		Statement statement = null;//Statement用于执行静态SQL语句，并返回它所生成结果的对象。
		ResultSet resultSet = null;//结果集
		int flag = 0;
		try {
		String sqlString="select * from users where username = '"+username+"'";//查找与我输入的username相等的用户名
		statement = (Statement) connection.createStatement();//根据连接获取一个执行SQL语句的对象
		resultSet = statement.executeQuery(sqlString);//执行SQL语句
		if (resultSet.next()) {
			if (resultSet.getString("password").equals(password)) {
				flag = 1;
			}
		}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			try {
				if (connection!=null) {
					connection.close();
				}
				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
		return flag;
		
	}
	public int register(String username,String age,String sex,String password) {
		Connection connection = DBUtil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;//PreparedStatement比Statement有优势
		int flag = 0;
		//创建用户名集合
		List<String> usernameList = new ArrayList<String>();
		String sqlString = "select username from users";
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			
			//将username字段的所有数据存入集合中
			while (resultSet.next()) {
				usernameList.add(resultSet.getString("username"));//将username字段的所有数据存入集合中
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (usernameList.contains(username)) {
			flag = 2;
		}else {
			String insertUser = "insert into users(username,age,sex,password) values(?,?,?,?)";
			try {
				//设置preparedStatement参数
				preparedStatement = (PreparedStatement) connection.prepareStatement(insertUser);//PreparedStatement能够执行参数化的SQL语句
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, age);
				preparedStatement.setString(3, sex);
				preparedStatement.setString(4, password);
				//执行sql语句
				preparedStatement.execute();
				flag = 3;
				preparedStatement.close();
				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally {
				if (connection!=null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
		
	}
	
}


