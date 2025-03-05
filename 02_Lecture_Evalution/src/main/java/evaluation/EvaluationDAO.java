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
	
	public ArrayList<EvaluationDTO> view(){ // 강의 평가 출력 함수
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
				list.add(new EvaluationDTO( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14)
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 검색한 내용이 없을때 에러가 발생하는것을 방지하기 위해 list의 시작은 null값으로 잡고, 이후 SQL문을 통해 해당 내용을 검색했다면 그 내용을 list에 담을 수 있도록한다.
	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber){
		if(lectureDivide.equals("전체")) { lectureDivide = ""; }
		ArrayList<EvaluationDTO> list = null;
		String SQL = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if(searchType.equals("최신순")) {
				 // LIKE : Mysql 문법 중 하나로 특정한 문자를 포함하는지 확인할 때 많이 사용
				SQL = "select * from evaluation where lectureDivide LIKE ?"
						+ " and concat(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ?"
						+ " order by evaluationID desc limit " + pageNumber * 5 + ", " + pageNumber * 5 + 6; 
			}else if(searchType.equals("추천순")) {
				SQL = "select * from evaluation where lectureDivide LIKE ?"
						+ " and concat(lectureName, professorName, evaluationTitle, evaluationContent) LIKE ?"
						+ " order by likeCount desc limit " + pageNumber * 5 + ", " + pageNumber * 5 + 6; 
			}
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + lectureDivide + "%"); // LIKE문을 사용할 때 %를 특정한 문자 앞쪽과 뒤쪽에 각각 넣어야 포함되는 문자인지 확인이 가능하다.
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();
			
			list = new ArrayList<EvaluationDTO>();
			
			while(rs.next()) {
				EvaluationDTO dto = new EvaluationDTO(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getInt(14)
						);
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); }catch (Exception e) { e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); }catch (Exception e) { e.printStackTrace(); }
			try { if(rs != null) rs.close(); }catch (Exception e) { e.printStackTrace(); } //
		}
		
		return list;
	}
}
