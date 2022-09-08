package tw.hibernatedemo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.hibernatedemo.model.Member;
import tw.hibernatedemo.service.MemberService;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserLoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		
		request.setAttribute("errorMsgMap", errorMsgMap);
		
		HttpSession httpSession = request.getSession();
		
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		MemberService mService = new MemberService();
		Member tempMember = mService.checkLogin(username, pwd);
		
		if(tempMember!=null) {
			httpSession.setAttribute("loginMember", tempMember);
		}else {
			errorMsgMap.put("loginError", "帳號密碼錯誤，請重新輸入");
		}
		
		if(errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("loginOK.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

}
