package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.OrgMember;
import com.revature.dao.ReimbTableDAO;

public class ReimbTableServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
		String[] splitURI = req.getRequestURI().split("/");
		
		String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);
		// This line above basically extracts the tokens that will be used for the 
		// helper functions below.
		
		//System.out.println(Arrays.toString(tokens));
		String username = (String) req.getSession().getAttribute("username");
		System.out.println(Arrays.deepToString(splitURI));
		// illustrates the array in question.
		if (tokens.length == 0) {
			resp.sendError(400, "Invalid directories");
			return;
		}

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		ReimbTableDAO m = new ReimbTableDAO();
		
		System.out.println("GG: "+Arrays.deepToString(tokens));
		// This displays the tokens to be used.
		System.out.println("hey: "+tokens[0]);
		switch (tokens[0]) {
		case "manager":
			ManagerHandler(req,resp,om,pw,tokens,username,m);
			break;
		case "employee":
			EmployeeHandler(req,resp,om,pw,tokens,username,m);
			break;
		case "reimb":
			break;
		default:
			resp.sendError(404, "Token not recognized: " + tokens[0]);
			break;
		}
		
	}
	private static void ManagerHandler(HttpServletRequest req, 
			HttpServletResponse resp, ObjectMapper obm, 
			PrintWriter prnt, String[] tokens, String username, 
			ReimbTableDAO m) {		
		String jsonReimbData = "";
		System.out.println(req.getMethod());
		System.out.println("heeey: "+tokens[1]);
		
		if (req.getMethod().equals("GET")) {
			if (tokens[1].equals("allemployees"))
				try {
					jsonReimbData = obm
						.writeValueAsString(m
							.getOrgMembers(false));
					System.out.println(jsonReimbData);
					prnt.write(jsonReimbData);
				} catch (JsonProcessingException ae) {
					ae.printStackTrace();
				}
		}
		
	}
	
	private static void EmployeeHandler(HttpServletRequest req, 
			HttpServletResponse resp, ObjectMapper obm, 
			PrintWriter prnt, String[] tokens, String username,
			ReimbTableDAO m) {
		String jsonReimbData = "";
		System.out.println(req.getMethod());
		System.out.println("heeey: "+tokens[1]);
		
		if (req.getMethod().equals("GET")) {
			if (tokens[1].equals("allreqs")) {
				try {
					jsonReimbData = obm
						.writeValueAsString(m
							.getReimbRequests(username,0,false));
					System.out.println(jsonReimbData);
					prnt.write(jsonReimbData);
				} catch (JsonProcessingException ae) {
					ae.printStackTrace();
				}
			}
			else if (tokens[1].equals("pending")) {
				try {
					jsonReimbData = obm
						.writeValueAsString(m
							.getReimbRequests(username,0,true));
					System.out.println(jsonReimbData);
					prnt.write(jsonReimbData);
				} catch (JsonProcessingException ae) {
					ae.printStackTrace();
				}
			}
			else if (tokens[1].equals("approved")) {
				try {
					jsonReimbData = obm
						.writeValueAsString(m
							.getReimbRequests(username,1,true));
					System.out.println(jsonReimbData);
					prnt.write(jsonReimbData);
				} catch (JsonProcessingException ae) {
					ae.printStackTrace();
				}
			}
			else if (tokens[1].equals("denied")) {
				try {
					jsonReimbData = obm
						.writeValueAsString(m
							.getReimbRequests(username,2,true));
					System.out.println(jsonReimbData);
					prnt.write(jsonReimbData);
				} catch (JsonProcessingException ae) {
					ae.printStackTrace();
				}
			}
			else {
				prnt.write("Not a valid action.");
			}
		} else if (req.getMethod().equals("POST")) {
			// Nothing here yet.
			// addReimbRequest goes here.
		} else {
			prnt.write("Invalid Request.");
		}
		//case "POST":
			//We'll read JSON from the request body
			/*player = om.readValue(req.getReader(), Player.class);
			//This should definitely be more informative.
			if(!playerService.createPlayer(player)) {
				resp.sendError(400, "Failed to create player");
			} else {
				pw.write("Successful creation");
			}*/
			//break;
		}
	}
	


