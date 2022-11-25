package com.KoreaIT.java.am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.am.config.Config;
import com.KoreaIT.java.am.exception.SQLErrorException;
import com.KoreaIT.java.am.util.DBUtil;
import com.KoreaIT.java.am.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServist extends HttpServlet { // 사용자에게서 요청받음

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());
			
			int page = 1; //page 변수 만들어서 파라미터에 넣기
			if(request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int itemsInAPage = 10; // 10개씩 나오게
			
			int limitFrom = (page - 1) * itemsInAPage; 
			
			SecSql sql = SecSql.from("SELECT COUNT(id)");
			sql.append("FROM article");
			
			// 페이지 개수를 확인하기 위해 DB에 다녀와야함
			int totalCount = DBUtil.selectRowIntValue(conn, sql);
							// 나눠서 올림한 후에 int로 변환
			int totalPage = (int)Math.ceil((double)totalCount / itemsInAPage); // 나머지가 존재해야함
			
			sql = SecSql.from("SELECT A.*, M.name AS writerName");
			sql.append("FROM article AS A");
			sql.append("INNER JOIN `member` AS M");
			sql.append("ON A.memberId = M.id");
			sql.append("ORDER BY A.id DESC");
			sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);
			
			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql); // 요청받은 정보를 db에서 가져와
			
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("articleRows", articleRows); // request 내에 속성 세팅 후 
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response); //jsp로 일을 넘겨받아서 꺼내옴
			

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
