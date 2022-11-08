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
		
		System.out.println("좋아요 목록 출력해 보자구!");
		
		String id = request.getParameter("id");
		
		System.out.println("id : " + id);
		
		LikeDao likeDao = LikeDao.getInstance();
		
		ArrayList<RestaurantDto> list = likeDao.myLikeRestaurant(id);
		
	
//		RestaurantDao restoDao = RestaurantDao.getInstance();
//		ArrayList<LikeDto> LikeList = likeDao.getLikeById(id); 
//		
//		ArrayList<RestaurantDto> list = new ArrayList<RestaurantDto>();
//		for(LikeDto like : LikeList) {
//			RestaurantDto temp = restoDao.getRestaurantByCode(like.getResto_code());
//			list.add(temp);
//		}
		
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
