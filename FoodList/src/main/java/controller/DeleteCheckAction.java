package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCheckAction
 */
//@WebServlet("/DeleteCheckAction")
public class DeleteCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html);charset=UTF-8");
		String check = request.getParameter("check");
		String question = "삭제하겠습니다.";
		
		if(check.equals(question)) {
			
		} else {
	
		}
		
		
		doGet(request, response);
	}

}
