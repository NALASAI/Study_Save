package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DatabaseUtil;

public class EvaluationDAO {
	
	public int write(EvaluationDTO evaluationDTO) { //강의 평가 등록 함수
		// evaluationID는 auto_increment 기능때문에 자동으로 숫자가 상승되므로 null값을 지정, likeCount 는 갓 만들어진 평가등록을 위해 0으로 지정
		String SQL = "insert into evaluation values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, evaluationDTO.getUserID());
			pstmt.setString(2, evaluationDTO.getLectureName());
			pstmt.setString(3, evaluationDTO.getProfessorName());
			pstmt.setInt(4, evaluationDTO.getLectureYear());
			pstmt.setString(5, evaluationDTO.getSemeterDivide());
			pstmt.setString(6, evaluationDTO.getLectureDivide());
			pstmt.setString(7, evaluationDTO.getEvaluationTitle());
			pstmt.setString(8, evaluationDTO.getEvaluationContent());
			pstmt.setString(9, evaluationDTO.getTotalScore());
			pstmt.setString(10, evaluationDTO.getCreditScore());
			pstmt.setString(11, evaluationDTO.getComfortableScore());
			pstmt.setString(12, evaluationDTO.getLectureScore());
			
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); } // conn 객체 초기화
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); } // pstmt 객체 초기화
			try { if(rs != null) rs.close(); }catch (Exception e) { e.printStackTrace(); } // rs 객체 초기화
		}

		return -2;
	}
	
	public ArrayList<EvaluationDTO> view(){
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		String SQL = "select * from evaluation;";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new EvaluationDTO(
						rs.getInt("evaluationID"), rs.getString("userID"),
						rs.getString("lectureName"), rs.getString("professorName"),
						rs.getInt("lectureYear"), rs.getString("semeterDivide"),
						rs.getString("lectureDivide"), rs.getString("evaluationTitle"),
						rs.getString("evaluationContent"), rs.getString("totalScore"),
						rs.getString("creditScore"), rs.getString("comfortableScore"),
						rs.getString("lectureScore"), rs.getInt("likeCount")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
