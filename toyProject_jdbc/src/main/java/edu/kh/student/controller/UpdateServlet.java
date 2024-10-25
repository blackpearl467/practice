package edu.kh.student.controller;

import java.io.IOException;

import edu.kh.student.dto.Student;
import edu.kh.student.service.StudentService;
import edu.kh.student.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/update")
public class UpdateServlet extends HttpServlet {
	
	// 수정 화면 전환 GET 요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int studentNo = Integer.parseInt(req.getParameter("studentNo"));
			
			// 수정 화면에는 기존의 제목, 상세내용이 
			// input, textarea에 채워져있는 상태여야한다!
			// -> 수정 전 제목/내용 조회 == 상세조회 서비스 재호출
			StudentService service = new StudentServiceImpl();
			Student student = service.studentDetailView(studentNo);
			
			if(student == null) {
				resp.sendRedirect("/"); // 메인페이지로 redirect 
				return;
			}
			
			// request scope에 todo 객체 세팅
			req.setAttribute("student", student);
			
			// 요청발송자를 통해 forward
			String path = "/WEB-INF/views/update.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	
	// 학생 정보 수정 POST 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			String studentName = req.getParameter("studentName");
			String studentAge = req.getParameter("studentAge");
			String studentScore = req.getParameter("studentScore");
			int studentNo = Integer.parseInt(req.getParameter("studentNo"));
			
			StudentService service = new StudentServiceImpl();
			int result = service.studentUpdate(studentNo, studentName, studentAge, studentScore);
		
			//수정 성공&실패
			String url = null;
			String message = null;
			
			if(result > 0) {
				url = "/student/detail?studentNo=" + studentNo;
				message = "수정 되었습니다";
				
			} else { // 실패
				url = "/student/update?studentNo=" + studentNo;
				message = "수정 실패";
			}
			
			// session 객체에 속성 추가 
			req.getSession().setAttribute("message", message);
			
			// redirect는 GET 방식 요청
			resp.sendRedirect(url);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


