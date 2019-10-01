package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession session = req.getSession();
		
		ReimbTableDAO inst = new ReimbTableDAO();
		
		String username = (String) req.getAttribute("username");
		System.out.println("In LoginServlet: " + username);
		String password = (String) req.getAttribute("password");
		Boolean isMgr = (Boolean) req.getAttribute("is_mgr");
		
		System.out.println(isMgr);
		
		OrgMember user = inst.getOrgMember(username, true);
		System.out.println(user);
			
			PrintWriter pw = resp.getWriter();
			
			if (verifyLogin(req, resp, user, username, password, pw)) {
				//req.setAttribute("user",user);
				isManager(isMgr,user,req,resp);
			} else {
				pw.write("User doesn't exist or isn't found.");
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
	private static Boolean verifyLogin(HttpServletRequest req, 
			HttpServletResponse resp, OrgMember user,
			String username, String password, PrintWriter pw) 
					throws IOException {
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

	private static void isManager(Boolean isMgr, OrgMember user,
			HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		if (isMgr && user.isDetermine() == true) {
			req.getServletContext().getRequestDispatcher("/requests_manager.html").
			forward(req, resp);
		} else if ((isMgr && user.isDetermine() == false) ||
				(!isMgr && user.isDetermine() == true)) {
			req.getServletContext().getRequestDispatcher("/not_a_manager.html")
			.forward(req, resp);
		} else if (!isMgr && user.isDetermine() == false){
			req.getServletContext().getRequestDispatcher("/requests_employee.html")
			.forward(req, resp);
		} else
			System.out.println("sorry.");
	}
}
