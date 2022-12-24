package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class JoinAction
 */
//@WebServlet("/JoinAction")
public class JoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id = request.getParameter("id");
		String password= request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		Timestamp birth = Timestamp.valueOf(request.getParameter("birth")+" 00:00:00");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		UserDto user = new UserDto(id, password, name, phone, birth, now);
		
		UserDao dao = UserDao.getInstance();
		
		dao.createUser(user);
		request.getRequestDispatcher("loginForm").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
