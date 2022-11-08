package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class UserUpdateAction
 */
//@WebServlet("/UserUpdateAction")
public class UserUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		System.out.println("회원정보 수정 페이지에 왔따!!");
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		String password = null;
		if(request.getParameter("passwordNew") == "") {
			System.out.println("새로운 패스워드 값이 없다!");
			password = request.getParameter("password");			
		}else {
			password = request.getParameter("passwordNew");	
		}
		System.out.println("password : " + password);
		String name = request.getParameter("name");
		System.out.println("name : " + name);
		String phone = request.getParameter("phone");
		System.out.println("phone : " + phone);
		
		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getUserById(id);
		Timestamp birth = user.getBirth();
		System.out.println("birth : " + birth);
		Timestamp reg_date = user.getReg_date();
		
		UserDto userNew = new UserDto(id, password, name, phone, birth, reg_date);
		
		dao.updateUser(userNew);
		System.out.println("수정 완료");
		
		request.getRequestDispatcher("myPageForm").forward(request, response);
	
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
