package com.KoreaIT.java.am.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.am.dto.Article;
import com.KoreaIT.java.am.util.DBUtil;
import com.KoreaIT.java.am.util.SecSql;

public class ArticleDao {
	
	private Connection conn;

	public ArticleDao(Connection conn) { // 받아온 conn 연결
		this.conn = conn;
	}

	public int getListTotalCount() {

		SecSql sql = SecSql.from("SELECT COUNT(id)");
		sql.append("FROM article");

		return DBUtil.selectRowIntValue(conn, sql);
	}
	
	public int getWrite(int loginedMemberId, String title, String body) {

		SecSql sql = SecSql.from("INSERT INTO article");
		
		sql.append("SET regDate = NOW()");
		sql.append(", memberId = ?", loginedMemberId);
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		
		return DBUtil.insert(conn, sql);
	}


	public List<Article> getArticles(int limitFrom, int itemsInAPage) {

		SecSql sql = SecSql.from("SELECT A.*, M.name AS writerName");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql); // 정제를 해서 넘김 

		List<Article> articles = new ArrayList<>();

		for(Map<String, Object> articleRow : articleRows) {
			articles.add(new Article(articleRow));
		}

		return articles;
	}

	public Map<String, Object> getArticleRow(int id) {
		
		SecSql sql = SecSql.from("SELECT A.*, M.name AS writerName");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?", id);

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql); // 요청받은 정보를 db에서 가져와
		return DBUtil.selectRow(conn, sql);
	}
	
	public Map<String, Object> getArticleRowMd(int id) {
		
		SecSql sql = SecSql.from("SELECT *");
		
		sql.append("FROM article ");
		sql.append("WHERE id = ?", id);
	
		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql); // 요청받은 정보를 db에서 가져와
		
		return DBUtil.selectRow(conn, sql); 
	}

	public int doDelete(int id) {
		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		
		sql = SecSql.from("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		int articleRow = DBUtil.delete(conn, sql); // 요청받은 정보를 db에서 가져와
		return DBUtil.delete(conn, sql);
		
	}


}