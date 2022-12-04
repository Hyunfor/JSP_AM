package com.KoreaIT.java.am.service;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.am.dao.ArticleDao;
import com.KoreaIT.java.am.dto.Article;

public class ArticleService {

	private int itemsInAPage;
	private static ArticleDao articleDao;

	public ArticleService(Connection conn) { // 연결
		this.itemsInAPage = 10;
		this.articleDao = new ArticleDao(conn);
	}

	public int getListTotalPage() {

		int totalCount = articleDao.getListTotalCount();
		int totalPage = (int)Math.ceil((double)totalCount / itemsInAPage);

		return totalPage;
	}
	
	public static int getAttribute(int memberId, String title, String body) {
		
		int id = ArticleService.getAttribute(memberId, title, body);
		
		return articleDao.getAttribute(id, body, body);
	}

	public List<Article> getArticleRows(int page) {

		int limitFrom = (page - 1) * itemsInAPage;
		
		return articleDao.getArticles(limitFrom, itemsInAPage);
	}
	
	public Map<String, Object> getArticleRow(int id) {
		return articleDao.getArticleRow(id);
	}

}

