package util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

/**
 * jdbc连接数据库
 * @author Az
 * 
 */
public class DBUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			//注册jdbc驱动
			Class.forName("com.mysql.jdbc.Driver");
			String urlString = "jdbc:mysql://localhost:3306/erp_create";
			String userString = "root";
			String passwordString = "123";
			//打开连接
			connection = (Connection) DriverManager.getConnection(urlString, userString, passwordString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//方法重载
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*public static void main(String[] args) {
		System.out.println(getConnection());//测试数据库连接是否成功,不报错即为成功;
	}*/
}
