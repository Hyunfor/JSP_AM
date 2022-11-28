package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.KoreaIT.java.am.config.Config;
import com.KoreaIT.java.am.controller.ArticleController;
import com.KoreaIT.java.am.exception.SQLErrorException;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet { // 사용자에게서 요청받음

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String requestUri = request.getRequestURI(); // localhost:8084/JSP_AM/s
		String[] requestUriBits = requestUri.split("/"); // /article/list 2개로 나눔
		
		if(requestUriBits.length < 5) { // 올바른 요청시 5조각. 올바른 요청인지 체크
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}
		
		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());
			
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			String loginedMemberName = (String) session.getAttribute("loginedMemberName");

			if(session.getAttribute("loginedMemberLoginId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				isLogined = true;
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberName", loginedMemberName);
			
			String controllerName = requestUriBits[3]; // 컨트롤러
			String actionMethodName = requestUriBits[4]; // 컨트로러 내에서 
			
			if(controllerName.equals("article")) { // controllerName에 태워서 보냄 - 라우팅 역할
				ArticleController articleController = new ArticleController(request, response, conn);

				if(actionMethodName.equals("list")) {
					articleController.showList();
				}
			}

		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} catch(SQLErrorException e) { // DB에러를 java에서 같이 보여줌
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
