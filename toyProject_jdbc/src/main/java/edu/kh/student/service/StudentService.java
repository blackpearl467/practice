package edu.kh.student.service;

import java.util.Map;

import edu.kh.student.dto.Student;

public interface StudentService {

	
	
	/**모든학생 출력 서비스(메인)
	 * @return
	 * @throws Exception 
	 * */
	Map<String, Object> studentServiceFullView() throws Exception;

	/**학생 상세조회 요청 처리
	 * @param studentNo
	 * @return
	 */
	Student studentDetailView(int studentNo) throws Exception;

	/**학생 추가 화면
	 * 
	 * */
	int studentAdd(String studentName, String studentAge, String studentGender, String studentScore)
			throws Exception;

	/**학생 정보 수정 화면
	 * */
	int studentUpdate(int studentNo, String studentName, String studentAge, String studentScore)
			throws Exception;
	
	

}
