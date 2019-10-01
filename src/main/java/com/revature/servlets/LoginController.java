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

public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		
		System.out.println(username + " " + password);
		
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		
		if (!(username == null || password == null))
			req.getRequestDispatcher("login_verify").forward(req, resp);
		else
			pw.write("Username and Password can't be empty.");
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
}
