package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class JoinCheckAction
 */
//@WebServlet("/JoinCheckAction")
public class JoinCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinCheckAction() {
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

		UserDao dao = UserDao.getInstance();

		String duplId = request.getParameter("duplId");
		
		if(dao.getUserById(id) != null) {
			duplId = true + "";
		}else {
			duplId = false + "";
		}

		// ----------------------------------

		String phone = request.getParameter("phone");
		
		String duplPhone = request.getParameter("duplPhone");

		duplPhone = dao.getUserByPhone(phone) + "";
		
		response.setContentType("application/json");

		// create Json Object
		JSONObject json = new JSONObject();
		json.put("duplId", duplId);
		json.put("duplPhone", duplPhone);
		
		response.getWriter().append(json.toString());

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
