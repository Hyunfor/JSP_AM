package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home/main")
public class MainPageServlet extends HttpServlet { // 사용자에게서 요청받음

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean isLogined = false;
		
		int loginedMemberId = -1;
		
		if(session.getAttribute("loginedMemberLoginId") != null) { // 로그인을 했을 시
			loginedMemberId = (int)session.getAttribute("loginedmemberId");
			isLogined = true;
		}
		
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isLoginedMemberId", loginedMemberId);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
