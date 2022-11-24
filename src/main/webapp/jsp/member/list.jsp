<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%                                                                                      // 키가들어오는 위치
List<Map<String, Object>> memberRows = (List<Map<String, Object>>)request.getAttribute("memberRows");
int cPage = (int)request.getAttribute("page");
int totalPage = (int)request.getAttribute("totalPage");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>

	<h1>회원 리스트</h1>
	
	<table border="2" bordercolor="green">
		<colgroup>
			<col width="50"/>
			<col width="200"/>
		</colgroup>
		<tr> 
			<th>번호</th>
			<th>날짜</th>
			<th>아이디</th>
		</tr>	
	
	<% for(Map<String, Object> memberRow : memberRows) { %>
		<tr>
			<td><%= (int)memberRow.get("id") %></td>
			<td><%= (LocalDateTime)memberRow.get("regDate") %></td>
		</tr>
		
		<% } %>
	</table>
	
	<style type="text/css"> 
		.page > a.red {
			color:red;
		}
	</style>
	
	<div class="page">
	
	<%
	if(cPage > 1){
	%>
		<a href="list?page=1">◀◀</a>
	<%
	}	
	%>
		
		<%
		int pageSize = 5;
		int from = cPage - pageSize;
		if(from < 1){
			from = 1;
		}
		int end = cPage + pageSize;
		if(end > totalPage){
			end = totalPage;
		}
		
		for(int i = from; i <= end; i++){ %>
			<a class="<%= cPage == i ? "red" : "" %>" href="list?page=<%= i %>"><%= i %></a>
		<%} %>
		
	<%
	if(cPage < totalPage){
	%>
		<a href="list?page=<%= totalPage%>">▶▶</a>
	<%
	}	
	%>
		
		
			
	</div>

	<div>
		<a href="../home/main">메인 페이지</a>
	</div>
	
</body>
</html>