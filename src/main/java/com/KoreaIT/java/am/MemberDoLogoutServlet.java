package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/member/doLogout")
public class MemberDoLogoutServlet extends HttpServlet { // 사용자에게서 요청받음

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(); // 세션에 남은 기록을 지워서 로그아웃
		session.removeAttribute("loginedMemberId"); 
		session.removeAttribute("loginedMemberLoginId");
		
		response.getWriter().append(String.format("<script>alert('로그아웃 되었습니다.'); location.replace('../home/main'); </script>)"));
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
