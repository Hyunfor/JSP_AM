package com.KoreaIT.java.am;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan")
public class HomeMainServlet4 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // HTML 텍스트로 처리
		
		String inputDan = request.getParameter("dan"); // parameter로 요청오는것은 문자열로 인식
		
		if(inputDan == null) {
			inputDan = "1";
		}
		
		String inputLimit = request.getParameter("limit");
		
		if(inputLimit == null) {
			inputLimit = "9";
		}
		
		String inputColor = request.getParameter("color"); 
		
		if(inputColor == null) {
			inputColor = "black";
		}
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		response.getWriter().append(String.format("<div style=\"color:%s;\">%d단</div>", inputColor, dan));
		for(int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style=\"color:%s;\">%d * %d = %d</div>", inputColor, dan, i , dan * i));
		}
	}

}
