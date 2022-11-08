package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import restaurant.RestaurantDao;
import restaurant.RestaurantDto;

/**
 * Servlet implementation class RestaurantSearchAction
 */
//@WebServlet("/RestaurantSearchAction")
public class RestaurantSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String kind = request.getParameter("kind");
        String area = request.getParameter("area");
        String sort = request.getParameter("sort");
        
        RestaurantDao dao = RestaurantDao.getInstance();
        
        if(kind != null && area != null && sort != null) {
        	ArrayList<RestaurantDto> list = dao.searchRestaurant(kind, area);
        	ArrayList<RestaurantDto> sortList = dao.sortRestaurant(list, sort);
        	if(list.size() > 0) {
        		JSONArray result = new JSONArray(sortList);
        		response.getWriter().append(result.toString());
        	} else {
        		response.getWriter().append("null");
        	}
        } else {
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
