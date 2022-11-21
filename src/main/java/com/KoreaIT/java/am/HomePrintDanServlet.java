package com.KoreaIT.java.am;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan") // 판단 후 연결
public class HomePrintDanServlet extends HttpServlet {

						// 연결 요청, 매칭 후 jsp로 떠넘기기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/home/printDan.jsp").forward(request, response); // forward로 request와 response를 줘야함.
		
	}

}
