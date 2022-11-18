package com.KoreaIT.java.am;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home/printDan9")
public class HomeMainServlet3 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("9dan\n");
		
		int dan = 9;
		for(int i = 1; i <= 9; i++) {
			response.getWriter().append(String.format("%d * %d = %d\n", dan, i , dan * i));
		}
	}

}
