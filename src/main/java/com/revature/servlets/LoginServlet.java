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
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
		Boolean isManager = (Boolean)session.getAttribute("manager");
		
		OrgMember user = ReimbTableDAO.getOrgMember(username, true);
		
		if (loginVerify(req, resp, user, username, password) &&
				!isManager) {
			session.setAttribute("user",user);
			req.getServletContext().getRequestDispatcher("/menu.html").
				forward(req, resp);
		} else if (loginVerify(req, resp, user, username, password) &&
				isManager) {
			session.setAttribute("user",user);
			req.getServletContext().getRequestDispatcher("/menuEmp.html").
				forward(req, resp);
		}
		
	}
	
	private boolean loginVerify(HttpServletRequest req, 
			HttpServletResponse resp, OrgMember user,
			String username, String password) 
					throws IOException {
		PrintWriter pw = resp.getWriter();
		if (!user.getPassword().equals(password)) {
			pw.write("Wrong password.");
			return false;
		} else if (password.isEmpty()) {
			pw.write("Password can't be blank.");
			return false;
		} else if (username.isEmpty()) {
			pw.write("Username can't be blank.");
			return false;
		} else if (!user.getPassword().equals(password) 
				&& !user.getUsername().equals(username)) {
			pw.write("User doesn't even exist.");
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isManager(Boolean isManager) {
		if
	}
}
