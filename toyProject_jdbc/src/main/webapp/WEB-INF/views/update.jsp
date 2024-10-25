<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${student.name} 수정 페이지</title>
</head>
<body>
  
  <h4>학생 정보 수정</h4>

  <%-- 
    /student/update - POST 방식 요청
                -> Servlet의 doPost() 오버라이딩
  --%>
  <form action="/student/update" method="post" id="updateForm">
    <div>
      이름 : <input type="text" name="studentName" value="${student.name}">
    </div>
    <div>
      나이 : <input type="text" name="studentAge" value="${student.age}">
    </div>
    <div>
      성적 : <input type="text" name="studentScore" value="${student.score}">
    </div>

    <input type="hidden" name="studentNo" value="${param.studentNo}">
    
    <button>수정</button>
  </form>

	<%-- session 스코프에 message 있으면 alert 출력하기 --%>
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>
	
		<c:remove var="message" scope="session"/>
	</c:if>
	
</body>
</html>