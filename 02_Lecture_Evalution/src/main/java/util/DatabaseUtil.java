package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	/*DB 연결*/
	public static Connection getConnection() {
		try {
            String dbURL = "jdbc:mysql://localhost:3306/lectureevaluation?useSSL=false";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
