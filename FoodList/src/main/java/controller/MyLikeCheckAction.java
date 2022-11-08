package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import like.LikeDao;
import like.LikeDto;

/**
 * Servlet implementation class MyLikeCheckAction
 */
@WebServlet("/MyLikeCheckAction")
public class MyLikeCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeCheckAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		LikeDao dao = LikeDao.getInstance();
		HttpSession session = request.getSession();
		String log = null;
		int resto_code = Integer.parseInt(request.getParameter("resto_code"));
		if (session.getAttribute("log") != null) {
			log = (String) session.getAttribute("log");
			
			if(dao.myLikeExist(log, resto_code) != -1) {
				dao.restaurantLikeMinus(resto_code);
				dao.deleteLike(dao.myLikeExist(log, resto_code));
				System.out.println("싫어요!");
			} else {
				dao.restaurantLikePlus(resto_code);
				dao.createLike(new LikeDto(log, resto_code));
				System.out.println("좋아요!");
			}
			
			System.out.println(dao.myLikeExist(log, resto_code));
		}
		request.getRequestDispatcher("restaurantViewForm?code=" + resto_code).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
