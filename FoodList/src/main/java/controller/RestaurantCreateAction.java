package controller;

import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.RestaurantDao;
import restaurant.RestaurantDto;

/**
 * Servlet implementation class RestaurantCreateAction
 */
//@WebServlet("/RestaurantCreateAction")
public class RestaurantCreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantCreateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RestaurantDao dao = RestaurantDao.getInstance();
		request.setCharacterEncoding("UTF-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		String res_name = request.getParameter("res_name");
		String address = request.getParameter("address");
		String res_phone = request.getParameter("res_phone");
		String kind = request.getParameter("kind");
		String image_url = request.getParameter("image_url");			
		if(image_url == null) {
			image_url = "";
		}
		double ave_grade = 0.0;
		int like_cnt = 0;
		int review_cnt = 0;
		
		dao.createRestaurant(new RestaurantDto(code, res_name, address, res_phone, kind, image_url, ave_grade, like_cnt, review_cnt));
		
		request.getRequestDispatcher("restaurantSearchForm").forward(request, response);
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
