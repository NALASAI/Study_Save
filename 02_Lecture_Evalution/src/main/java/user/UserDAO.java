package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int login(String userID, String userPW) {
		String SQL = "select userPW from user where userID = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			
			rs = pstmt.executeQuery(); //executeQuery() : DB에서 데이터를 검색할때 사용
			
			if(rs.next()) {
				if(rs.getString(1).equals(userID)) {
					return 1; // 로그인 성공
				}else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디 없음
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
			try { if(rs != null) rs.close(); }catch (Exception e) { e.printStackTrace(); } // rs 객체 초기화
		}
		
		return -2; // DB 오류
	}

	public int join(UserDTO user) { //UserDTO 객체를 통해 쉽게 사용자를 생성할 수 있도록 설정
		String SQL = "insert into user value (?, ?, ?, ?, fasle)";
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPW());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			
			return pstmt.executeUpdate(); //executeUpdate() : DB에 데이터를 넣거나 수정 및 삭제할때 사용
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
		}
		
		return -2; // DB 오류
	}
	
	public String getUserEmail(String userID) { // 특정 사용자의 이메일 자체를 반환해주는 함수
		String SQL = "select userEmail from user where userID = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { return rs.getString(1); }
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
			try { if(rs != null) rs.close(); }catch (Exception e) { e.printStackTrace(); } // rs 객체 초기화
		}

		return null;
	}
	
	public boolean getUserEmailChecked(String userID) { // 현재 사용자가 이메일 검증이 되었는지 확인하는 함수
		String SQL = "select userEmailChecked from user where userID = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { return rs.getBoolean(1); }
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
			try { if(rs != null) rs.close(); }catch (Exception e) { e.printStackTrace(); } // rs 객체 초기화
		}

		return false;
	}
	
	public boolean setUserEmailChecked(String userID) { // 이메일 검증이 완료됬음을 확인해주는 함수
		String SQL = "update user set userEmailChecked = true where userID = ?";
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
		}

		return false;
	}
}
