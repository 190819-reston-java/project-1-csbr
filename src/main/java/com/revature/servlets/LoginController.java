package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import com.revature.beans.OrgMember;
//import com.revature.dao.ReimbTableDAO;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1533991339138370748L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (PrintWriter pw = resp.getWriter()) {

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String obj = req.getParameter("is_mgr");

			Boolean isManager = new Boolean(obj);

			System.out.println("Pre: " + isManager);

			System.out.println(username + " " + password);

			req.setAttribute("username", username);
			req.setAttribute("password", password);
			req.setAttribute("is_mgr", isManager);

			if (!(username == null || password == null)) {
				req.getRequestDispatcher("login_verify").forward(req, resp);
			} else {
				pw.write("Username and Password can't be empty.");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
