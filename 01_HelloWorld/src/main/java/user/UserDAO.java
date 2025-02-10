package user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DatabaseUtil;

public class UserDAO {
	/*DB에 들어갈 쿼리*/
	public int join(String userID, String userPW) {
		String SQL = "insert into user value (?, ?)";
		try {
			Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
}
