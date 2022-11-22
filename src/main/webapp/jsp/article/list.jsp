<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%                                                                                      // 키가들어오는 위치
List<Map<String, Object>> articleRows = (List<Map<String, Object>>)request.getAttribute("articleRows");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>

	<h1>게시물 리스트</h1>
	
	<table border="2" bordercolor="green">
		<colgroup>
			<col width="50"/>
			<col width="200"/>
		</colgroup>
		<tr> 
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
		</tr>	
	
	<% for(Map<String, Object> articleRow : articleRows) { %>
		<tr>
			<td><%= (int)articleRow.get("id") %></td>
			<td><%= (LocalDateTime)articleRow.get("regDate") %></td>
			<td><a href="detail?id=<%= (int)articleRow.get("id") %>"><%= (String)articleRow.get("title") %></a></td>
			<td><a href="doDelete?id=<%= (int)articleRow.get("id") %>">삭제하기</a></td>
		</tr>
		
		<% } %>
	</table>

	<div>
		<a href="../home/main">메인 페이지</a>
	</div>
	
</body>
</html>