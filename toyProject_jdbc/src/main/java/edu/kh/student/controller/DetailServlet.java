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
import jakarta.servlet.http.HttpSession;

//학생 상세조회 요청 처리 Servlet
@WebServlet("/student/detail")
public class DetailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int studentNo = Integer.parseInt( req.getParameter("studentNo") );
			//int형으로 형변환 해주어야함
			
			StudentService service = new StudentServiceImpl();
			
			Student student = service.studentDetailView(studentNo);
			
			//student가 존재하지 않을 경우
			if(student == null) {
				HttpSession session = req.getSession();
				session.setAttribute("message", "학생이 존재하지 않습니다");
				
				resp.sendRedirect("/");//재요청할 주소
				return;//if문 밑에 있는거 수행되지않도록 리턴
			}
			
			req.setAttribute("student", student);
			
			String path = "/WEB-INF/views/detail.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp); //요청 위임
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
