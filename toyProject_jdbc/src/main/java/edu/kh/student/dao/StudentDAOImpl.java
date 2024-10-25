package edu.kh.student.dao;

import static edu.kh.student.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.student.dto.Student;

public class StudentDAOImpl implements StudentDAO{
	
	// JDBC 객체 참조변수 + Properties 참조 변수 선언
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		private Properties prop;
		
		public StudentDAOImpl() {
			try {
				
				String filePath = StudentDAOImpl.class
						.getResource("/xml/sql.xml").getPath();
				
				prop = new Properties();
				prop.loadFromXML(new FileInputStream(filePath));
				
				
			} catch (Exception e) {
				System.out.println("sql.xml 로드 중 예외발생");
				e.printStackTrace();
				
			}
			
		}

		@Override
		public List<Student> studentServiceFullView(Connection conn) throws Exception {

			List<Student> studentList = new ArrayList<Student>();
			
			try {
				String sql = prop.getProperty("studentServiceFullview");
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
							
					Student student = Student.builder()
									.studentNo(rs.getInt("STUDENT_NO"))
									.studentName(rs.getString("STUDENT_NAME"))
									.studentAge(rs.getString("STUDENT_AGE"))
									.studentGender(rs.getString("STUDENT_GENDER"))
									.studentScore(rs.getString("STUDENT_SCORE"))
									.build();
					
					studentList.add(student);
					
				}
				
				
			} finally {
				close(rs);
				close(stmt);
			}
			return studentList;
		}

		@Override
		public int getCompleteCount(Connection conn) throws Exception {
			
			int completeCount = 0;
			
			try {
				String sql = prop.getProperty("getCompleteCount");
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					completeCount = rs.getInt(1);
				}
				
				
			} finally {
				close(rs);
				close(stmt);
			}
			
			return completeCount;
		}

		@Override
		public Student studentDetailView(Connection conn, int studentNo) throws Exception{
			
			Student student = null;
			
			try {
				String sql = prop.getProperty("studentDetailView");
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, studentNo);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					student = student.builder()
							.studentNo(rs.getInt("STUDENT_NO"))
							.studentName(rs.getString("STUDENT_NAME"))
							.studentAge(rs.getString("STUDENT_AGE"))
							.studentGender(rs.getString("STUDENT_GENDER"))
							.studentScore(rs.getString("STUDENT_SCORE"))
							.build();
					
				}
				
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return student;
		}

		@Override
		public int studentAdd(Connection conn, String studentName, String studentAge, String studentGender,
				String studentScore) throws Exception{
		
			int result = 0;
			
			try {
				String sql = prop.getProperty("studentAdd");
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studentName);
				pstmt.setString(2, studentAge);
				pstmt.setString(3, studentGender);
				pstmt.setString(4, studentScore);
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		@Override
		public int studentUpdate(Connection conn, int studentNo, String studentName, String studentAge, String studentScore) throws Exception{
			
			int result = 0;
			
			try {
				String sql = prop.getProperty("studentUpdate");
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studentName);
				pstmt.setString(2, studentAge);
				pstmt.setString(3, studentScore);
				pstmt.setInt(4, studentNo);
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		
}