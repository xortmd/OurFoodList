package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDao;
import like.LikeDao;
import review.ReviewDao;
import user.UserDao;
import user.UserDto;

/**
 * Servlet implementation class UserDeleteAction
 */
//@WebServlet("/UserDeleteAction")
public class UserDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteAction() {
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
		String password= request.getParameter("password");
		
		UserDao dao = UserDao.getInstance();
		
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("너 여기 왔니?");
		
		UserDto user = dao.getUserByIdPw(id, password);
		user.setPhone("");
		user.setPassword("****");
		dao.updateUser(user);
		
		// 회원 삭제 X, 회원 비밀번호를 ****로 바꿔서 탈퇴 아이디의 로그인을 막는다
		
		response.sendRedirect("index");
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
