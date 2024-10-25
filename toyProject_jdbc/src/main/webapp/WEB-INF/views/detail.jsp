<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${student.name} 상세 조회</title>
  <link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>

  <%-- 상세 내용 --%>
  <div class="content">${student.name}</div>

  <div class="btn-container">
    <div>
      <button id="goToList">목록으로</button>
    </div>
    
    <div>
      <button id="updateBtn">수정</button>
      <button id="deleteBtn">삭제</button>
    </div>
  </div>

  <%-- 학생 목록 출력 --%>
  <table id="studentList" border="1">
    <thead>
      <tr>
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

  <%-- session에 message가 있다면 --%>
<c:if test="${not empty sessionScope.message}">
	<script>
		alert("${message}");
	</script>
	
	<c:remove var="message" scope="session"/>
</c:if>

  <script src="/resources/js/detail.js"></script>
</body>
</html>