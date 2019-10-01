package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.OrgMember;
import com.revature.dao.ReimbTableDAO;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2133730339858743837L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpSession session = req.getSession();
		doPost(req,resp);
		
		//doPost(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = req.getSession();
		String username = (String) req.getAttribute("username");
		System.out.println(username);
		String password = (String) req.getAttribute("password");
		Boolean isManager = (Boolean) req.getAttribute("manager");

		OrgMember user = ReimbTableDAO.getOrgMember(username, true);
		
		PrintWriter pw = resp.getWriter();
		
		if (verifyLogin(req, resp, user, username, password, pw)) {
			req.setAttribute("user",user);
			if (isManager(isManager,user) == 1) {
				req.getServletContext().getRequestDispatcher("/requests_manager.html").
				forward(req, resp);
			}
			else if (isManager(isManager,user) == 0) {
				req.getServletContext().getRequestDispatcher("/not_a_manager.html")
				.forward(req, resp);
			}
			else {
				req.getServletContext().getRequestDispatcher("/requests_employee.html")
				.forward(req, resp);
			}
		} else {
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(req,resp);
	}
	/**
	 * @param req not used
	 */
	private static boolean verifyLogin(HttpServletRequest req, 
			HttpServletResponse resp, OrgMember user,
			String username, String password, PrintWriter pw) throws IOException {
		if (!user.getPassword().equals(password)) {
				pw.write("Wrong password.");
				return false;
			} else if (password.isEmpty()) {
				pw.write("Password can't be blank.");
				return false;
			} else if (username.isEmpty()) {
				pw.write("Username can't be blank.");
				return false;
			} else if (!user.getPassword().equals(password) && 
					!user.getUsername().equals(username)) {
				pw.write("User doesn't even exist.");
				return false;
			} else {
				return true;
			}
	}

	private static int isManager(boolean isMgr, OrgMember user) {
		if (isMgr) {
			if (user.isDetermine() == true) {
				return 1;
			}
		} else if (!isMgr) {
			if (user.isDetermine() == false) {
				return 0;
			}
		} else {
			return -1;
		}
		return -1;
	}
}
