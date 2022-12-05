package com.KoreaIT.java.am.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.am.dto.Article;
import com.KoreaIT.java.am.service.ArticleService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArticleController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) { // 넘겨받아서 사용
		this.request = request;
		this.response = response;
		this.articleService = new ArticleService(conn);
	}
	
	public void Write() throws ServletException, IOException { // ServletException, IOException으로 예외처리
		
		HttpSession session = request.getSession();

		if(session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append(String.format("<script>alert('로그인 후 이용해주세요.'); location.replace('../member/login');</script>"));
			return;
		}
		
		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);
		
	}
	
	// doWrite는 로그인이 우선이므로 member 이전작업 전까지 보류
	public void doWrite() throws ServletException, IOException { // ServletException, IOException으로 예외처리
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		int id = articleService.getWrite(loginedMemberId, title, body);	
		
		request.getRequestDispatcher("/jsp/article/dowrite.jsp").forward(request, response); //jsp로 일을 넘겨받아서 꺼내옴
		response.getWriter().append(String.format("<script>alert('%d번 글이 생성 되었습니다.'); location.replace('list'); </script>)", id));
		
	}

	public void showList() throws ServletException, IOException { // ServletException, IOException으로 예외처리

		int page = 1;

		if (request.getParameter("page") != null && request.getParameter("page").length() != 0){
		  page = Integer.parseInt(request.getParameter("page"));
		}

		int totalPage = articleService.getListTotalPage(); // totalPage를 service에서 가져와서 실행

		List<Article> articles = articleService.getArticleRows(page);

		request.setAttribute("page", page);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("articles", articles);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}
	
	public void showDetail() throws ServletException, IOException { // ServletException, IOException으로 예외처리
		
		int id = Integer.parseInt(request.getParameter("id")); 
		
		Map<String, Object> articleRow = articleService.getArticleRow(id);

		request.setAttribute("articleRow", articleRow); // request 내에 속성 세팅 후 
		
		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response); //jsp로 일을 넘겨받아서 꺼내옴
	}


}