package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.ReviewDao;
import review.ReviewDto;

/**
 * Servlet implementation class ReviewWriteAction
 */
//@WebServlet("/ReviewWriteAction")
public class ReviewWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		ReviewDao dao = ReviewDao.getInstance();
		
		// code == resto_code
		System.out.println("여기 오는게 맞니?");
		int code = Integer.parseInt(request.getParameter("code"));
		String coment = request.getParameter("coment");
		String user_id = request.getParameter("user_id");
		double give_grade = Double.parseDouble(request.getParameter("give_grade"));
		
	
		ReviewDto review = new ReviewDto(code, coment, user_id, give_grade);
		dao.createReview(review);
		System.out.println("착지");
//		request.getRequestDispatcher("restaurantViewForm").forward(request, response);
		response.sendRedirect("restaurantViewForm?code=" + code);
		
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
