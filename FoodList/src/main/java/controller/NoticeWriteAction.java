package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDao;
import notice.NoticeDto;

/**
 * Servlet implementation class NoticeWriteAction
 */
//@WebServlet("/NoticeWriteAction")
public class NoticeWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		NoticeDao dao = NoticeDao.getInstance();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_id = request.getParameter("user_id");
		int highlight = 0;
		if(request.getParameter("highlight") != null) {
			highlight = Integer.parseInt(request.getParameter("highlight"));
		} 
		NoticeDto notice = new NoticeDto(title, content, user_id, highlight);
		dao.createNotice(notice);
		
		response.sendRedirect("noticeForm");
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
