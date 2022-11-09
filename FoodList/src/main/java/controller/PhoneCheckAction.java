package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class PhoneCheckAction
 */
@WebServlet("/PhoneCheckAction")
public class PhoneCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhoneCheckAction() {
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
		System.out.println("핸드폰 번호로 아이디 찾기 서블릿 도착");

		String phone = request.getParameter("phone");

		UserDao dao = UserDao.getInstance();
		String realName = request.getParameter("realName");
		UserDto user = dao.getUserDtoByPhone(phone);

		realName = user.getName();
		System.out.println("진짜 이름 : " + realName);
		String id = request.getParameter("id");
		id = user.getId();
		
		response.setContentType("application/json");	// 제이슨 타입으로 보낸다는 뜻 이거 없으면 undefined

		JSONObject json = new JSONObject();
		json.put("realName", realName);
		json.put("id", id);

		System.out.println(json.toString());

		response.getWriter().append(json.toString());

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
