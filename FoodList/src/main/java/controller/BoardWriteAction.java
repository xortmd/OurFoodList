package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDao;
import board.BoardDto;

/**
 * Servlet implementation class BoardWriteAction
 */
//@WebServlet("/BoardWriteAction")
public class BoardWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BoardDao dao = BoardDao.getInstance();
	
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_id = request.getParameter("user_id");
//		Timestamp reg_date = Timestamp.valueOf(request.getParameter("reg_date") + " 00:00:00") ;
		
		BoardDto board = new BoardDto(title, content, user_id);
		dao.createBoard(board);
		
//		request.getRequestDispatcher("boardForm").forward(request, response);
		response.sendRedirect("boardForm");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		doGet(request, response);
	}

}
