package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class LoginAction
 */
//@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
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

		String password = request.getParameter("password");

		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getUserByIdPw(id, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("log", user.getId());
		
		try {
			Thread.sleep(400);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		request.getRequestDispatcher("index").forward(request, response);


	
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
