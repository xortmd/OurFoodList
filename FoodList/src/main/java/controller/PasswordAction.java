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
 * Servlet implementation class passwordAction
 */
@WebServlet("/passwordAction")
public class PasswordAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("아이디 연락처 서블릿 도착");
		
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
		
		String realPhone = request.getParameter("realPhone");
		UserDto user = dao.getUserById(id);
		
		realPhone = user.getPhone();
		System.out.println("핸드폰 번호 :" +realPhone);

		// ----------------------------------

		System.out.println("핸드폰 중복 검사");
		String duplPhone = request.getParameter("duplPhone");

		duplPhone = dao.getUserByPhone(duplPhone) + "";

		if (dao.getUserByPhone(duplPhone)) {
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
		json.put("realPhone", realPhone);
		json.put("duplPhone", duplPhone);
		
		System.out.println(json.toString());
		
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
