package edu.kh.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.student.dto.Student;
import edu.kh.student.service.StudentService;
import edu.kh.student.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//"/main" 요청을 매핑하여 처리하는 서블릿
@WebServlet("/main")
public class MainServlet extends HttpServlet{
 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			StudentService service = new StudentServiceImpl();
			
			//Map을
			//Service 호출해서 얻어오기
			Map<String, Object> map = service.studentServiceFullView();
			
			//Map에 저장된값 풀어내기
			List<Student> studentList = (List<Student>)map.get("studentList");
			int completeCount = (int)map.get("completeCount");
			
			//request scope에 객체값 추가하기
			req.setAttribute("studentList", studentList);
			req.setAttribute("completeCount", completeCount);
			
			//메인페이지 응답을 담당하는 jsp에 요청 위임
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
