package edu.kh.student.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.student.dao.StudentDAO;
import edu.kh.student.dao.StudentDAOImpl;
import edu.kh.student.dto.Student;

import static edu.kh.student.common.JDBCTemplate.*;
public class StudentServiceImpl implements StudentService{

	//메인
	private StudentDAO dao = new StudentDAOImpl();
	
	@Override
	public Map<String, Object> studentServiceFullView() throws Exception{

		Connection conn = getConnection();
		
		//학생 목록 얻어오기
		List<Student> studentList = dao.studentServiceFullView(conn);
		
		int completeCount = dao.getCompleteCount(conn);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studentList", studentList);
		map.put("completeCount", completeCount);
		
		return map;
	}

	@Override
	public Student studentDetailView(int studentNo) throws Exception{
		
		Connection conn = getConnection();
		
		Student student = dao.studentDetailView(conn, studentNo);
		
		close(conn);
		
		return student;
	}

	@Override
	public int studentAdd(String studentName, String studentAge, String studentGender, String studentScore) 
			throws Exception{
	
		Connection conn = getConnection();
		
		int result = dao.studentAdd(conn, studentName, studentAge, studentGender, studentScore);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	@Override
	public int studentUpdate(int studentNo, String studentName, String studentAge,
			String studentScore) throws Exception{
	
		Connection conn = getConnection();
		
		int result = dao.studentUpdate(conn, studentNo, studentName, studentAge, studentScore);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
	

}
