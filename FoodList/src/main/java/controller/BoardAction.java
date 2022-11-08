package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import board.BoardDao;
import board.BoardDto;

/**
 * Servlet implementation class BoardAction
 */
@WebServlet("/BoardAction")
public class BoardAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("게시글 목록 출력해 보자구!");
		
		String id = request.getParameter("id");
		
		System.out.println("id : " + id);
		
		BoardDao dao = BoardDao.getInstance();
		
		ArrayList<BoardDto> list = dao.getBoardById(id);
		
		
		System.out.println("********여기");
		
		if(list.size() > 0) {
			System.out.println("제이슨 만든다");
			JSONArray json = new JSONArray(list);	
			System.out.println(json.toString());
			response.getWriter().append(json.toString());
		}else {
			System.out.println("값이 없다");
			response.getWriter().append("null");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
