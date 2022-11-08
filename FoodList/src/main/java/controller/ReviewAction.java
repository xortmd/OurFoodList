package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import restaurant.RestaurantDao;
import restaurant.RestaurantDto;
import review.ReviewDao;
import review.ReviewDto;
import util.DBManager;

/**
 * Servlet implementation class ReviewAction
 */
//@WebServlet("/ReviewAction")
public class ReviewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("리뷰 목록 출력해 보자구!");
		
		String id = request.getParameter("id");
		
		System.out.println("id : " + id);
		
		ReviewDao dao = ReviewDao.getInstance();
		RestaurantDao res_dao = RestaurantDao.getInstance(); 
		
		ArrayList<ReviewDto> list = dao.getReviewAllByUser_id(id);		// 이게 아니야
		// 글 번호, 상호명(여기서 이름만 가져와야함), 리뷰코멘트, 등록일, 평점
		
		ArrayList<ReviewDto> temp = new ArrayList<ReviewDto>();
		
		for(ReviewDto l : list) {
			int resto_code = l.getResto_code();
			RestaurantDto resto = res_dao.getRestaurantByCode(resto_code);
			ReviewDto dto = new ReviewDto(l.getNo(), l.getResto_code(), l.getComent(), resto.getRes_name(), l.getReg_date(), l.getGive_grade());
			
			temp.add(dto);
		}
		
		if(list.size() > 0) {
			System.out.println("제이슨 만든다");
			JSONArray json = new JSONArray(temp);	
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
