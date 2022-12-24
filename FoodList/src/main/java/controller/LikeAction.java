package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import like.LikeDao;
//import like.LikeDao;
import like.LikeDto;
import restaurant.RestaurantDao;
import restaurant.RestaurantDto;

/**
 * Servlet implementation class LikeAction
 */
//@WebServlet("/LikeAction")
public class LikeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id = request.getParameter("id");
		LikeDao likeDao = LikeDao.getInstance();		
		ArrayList<RestaurantDto> list = likeDao.myLikeRestaurant(id);

		
		if(list.size() > 0) {
			JSONArray json = new JSONArray(list);	
			System.out.println(json.toString());
			response.getWriter().append(json.toString());
		}else {
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
