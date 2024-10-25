package edu.kh.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

	private int studentNo; //학생 번호
	private String studentName; //학생 이름
	private String studentAge; //학생 나이
	private String studentGender; //학생 성별('M' , 'F' 제한)
	private String studentScore; //학생 성적
}
