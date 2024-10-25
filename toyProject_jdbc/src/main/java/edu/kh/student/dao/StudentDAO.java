package edu.kh.student.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.student.dto.Student;

public interface StudentDAO {

	/**학생 목록 전체 조회
	 * */
	List<Student> studentServiceFullView(Connection conn) throws Exception;

	/**완료 목록 조회
	 * */
	int getCompleteCount(Connection conn) throws Exception;

	/**학생 상세 조회
	 * */
	Student studentDetailView(Connection conn, int studentNo) throws Exception;

	/**학생 추가
	 * */
	int studentAdd(Connection conn, String studentName, String studentAge, String studentGender, String studentScore)
			throws Exception;

	/**학생 수정
	 * */
	int studentUpdate(Connection conn, int studentNo, String studentName, String studentAge,
			String studentScore) throws Exception;

	
	
	
	
	
}