package edu.kh.student.controller;

import java.io.IOException;

import edu.kh.student.service.StudentService;
import edu.kh.student.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/add")
public class AddServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//학생 추가
		try {
			StudentService service = new StudentServiceImpl();
			
			String studentName = req.getParameter("studentName");
			String studentAge = req.getParameter("studentAge");
			String studentGender = req.getParameter("studentGender");
			String studentScore = req.getParameter("studentScore");
			
			int result = service.studentAdd(studentName, studentAge, studentGender, studentScore);
			
			//성공/실패 메시지 세팅하기
			String message = null;
			if(result > 0) message = "추가 성공!";
			else			message = "추가 실패...";
			
			//session을 이용해서 message를 세팅
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			//메인 페이지로 redirect(재요청)
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
