package util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

/**
 * jdbc�������ݿ�
 * @author Az
 * 
 */
public class DBUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			//ע��jdbc����
			Class.forName("com.mysql.jdbc.Driver");
			String urlString = "jdbc:mysql://localhost:3306/erp_create";
			String userString = "root";
			String passwordString = "123";
			//������
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
	
	//��������
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
		System.out.println(getConnection());//�������ݿ������Ƿ�ɹ�,������Ϊ�ɹ�;
	}*/
}
