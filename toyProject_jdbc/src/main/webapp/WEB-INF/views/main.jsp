<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>student List</title>

  <%-- css 파일 연결 (webapp 폴더 기준으로 경로 작성)--%>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
  <h1>Todo List</h1>

  <h3>전체 student 개수 : ${fn:length(studentList)} / 
      완료된 student 개수 : ${completeCount}</h3>

  <hr>

  <h4>학생 추가</h4>
  <form action="/student/add" method="post" id="addForm">
    <div>
      이름 : <input type="text" name="studentName">
    </div>
    <div>
      나이 : <input type="text" name="studentAge">
    </div>
    <div>
      성별 : <input type="text" name="studentGender">
    </div>
    <div>
      성적 : <input type="text" name="studentScore">
    </div>

    <button>추가</button>
  </form>

  <hr>

  <%-- 학생 목록 출력 --%>
  <table id="studentList" border="1">
    <thead>
      <tr>
        <th>학생 번호</th> <!-- 페이지에서 보이는 용도의 단순 출력 번호 -->
        <th>학생 이름</th> <!-- 실제 이 데이터의 studentNo 고유번호 -->
        <th>학생 나이</th>
        <th>학생 성별</th>
        <th>학생 성적</th>
      </tr>
    </thead>
  
    <tbody>
      <c:forEach items="${studentList}" varStatus="vs" var="student">
        <tr>
          <th>${vs.count}</th> <%-- 단순 출력 번호 --%>
          <th>${student.studentNo}</th> <%-- studentNo --%>

          <td>
          	<%-- 이름 클릭 시
          		인덱스 번호를 이용하여 studentList의
          		인덱스 번째 요소 내용을 조회하기
          	--%>
            <a href="/student/detail?studentNo=${student.studentNo}">${student.Name}</a>
          </td>

          <%-- 완료 여부 --%>
          <th>
          	<c:if test="${student.complete}">O</c:if> <%-- student의 complete가 true라면 O 출력 --%>
          	<c:if test="${not student.complete}">X</c:if> <%-- student의 complete가 true가 아니라면 X 출력 --%>
          </th>

        </tr>
       </c:forEach>
    </tbody>
  </table>

  <%-- session 범위에 message가 있을 경우 --%>
  <c:if test="${not empty sessionScope.message}">
  	<script>
  		alert("${message}"); 
  		//JSP 해석 우선순위
  		//1순위 : Java(el/jstl)
  		//2순위 : front(html,css,js)
  	</script>
  	
  	<%-- message를 한번만 출력하고 제거(sessionScope 계속 살아있기때문에 제거)--%>
  	<c:remove var="message" scope="session"/>
  </c:if>

  <%-- JS 연결 --%>
  <script src="/resources/js/main.js"></script>
</body>
</html>