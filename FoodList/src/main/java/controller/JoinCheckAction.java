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
		
		System.out.println("가입 중복 검사 서블릿 도착");
		
		System.out.println("아이디 중복 검사");
		String id = request.getParameter("id");
		System.out.println("id : " + id);

		UserDao dao = UserDao.getInstance();

		String duplId = request.getParameter("duplId");
		
		if(dao.getUserById(id) != null) {
			System.out.println("중복 아이디임");	
			duplId = true + "";
		}else {
			System.out.println("사용 가능");
			duplId = false + "";
		}

		System.out.println("duplId : " + duplId);

		// ----------------------------------

		System.out.println("핸드폰 중복 검사");
		String phone = request.getParameter("phone");
		System.out.println("중복 검사할 핸드폰 번호(서블릿) : " + phone);
		
		String duplPhone = request.getParameter("duplPhone");

		duplPhone = dao.getUserByPhone(phone) + "";

		if (dao.getUserByPhone(phone)) {
			System.out.println("중복 핸드폰 번호");
			
		} else {
			System.out.println("사용 가능");
		}
		System.out.println("duplPhone : " + duplPhone);
		
		
		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();

		// create Json Object
		JSONObject json = new JSONObject();
		json.put("duplId", duplId);
		json.put("duplPhone", duplPhone);
		
		System.out.println(json.toString());
		
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
