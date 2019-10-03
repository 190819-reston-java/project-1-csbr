package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.OrgMember;
import com.revature.beans.ReimbReq;
import com.revature.dao.ReimbTableDAO;

public class ReimbTableServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
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
		System.out.println("Username: " + username 
				+"SplitURI: " + Arrays.deepToString(splitURI));
		// illustrates the array in question.
		if (tokens.length == 0) {
			resp.sendError(400, "Invalid directories");
			return;
		}

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		ReimbTableDAO m = new ReimbTableDAO();
		
		System.out.println("Tokens: "+Arrays.deepToString(tokens));
		// This displays the tokens to be used.
		System.out.println("Current Token: "+tokens[0]);
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
			ReimbTableDAO m) throws ServletException {
		String jsonReimbData = "";
		System.out.println(req.getMethod());
		System.out.println("heeey: "+tokens[1]);
		
		if (req.getMethod().equals("GET")) {
			if (tokens[1].equals("allreqs")) {
				JSONObjHandler(obm, 
					m.getReimbRequests(username,0,false), 
						prnt, jsonReimbData);
			}
			else if (tokens[1].equals("pending")) {
				JSONObjHandler(obm, 
					m.getReimbRequests(username,0,true), 
						prnt, jsonReimbData);
			}
			else if (tokens[1].equals("approved")) {
				JSONObjHandler(obm, 
					m.getReimbRequests(username,1,true), 
						prnt, jsonReimbData);
			}
			else if (tokens[1].equals("denied")) {
				JSONObjHandler(obm, 
					m.getReimbRequests(username,2,true), 
						prnt, jsonReimbData);
			}
			else {
				prnt.write("Not a valid action.");
			}
		} else if (req.getMethod().equals("POST")) {
			// Nothing here yet.
			// addReimbRequest goes here.
			try {
				OrgMember current = null;
				if (tokens[1].equals("updateContacts")) {
					current = obm.readValue(req.getReader(), OrgMember.class);
					if (!m.updateEmpContactInfo(current)) {
						resp.sendError(500, "Update unsucessful.");
					} else {
						prnt.write("Update sucessful.");
						redirect(req,resp,"/contact_info_view.html");
					}
				} else if (tokens[1].equals("newReimbReq")) {
					
				} else {
					prnt.write("Not a valid action.");
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	private static void redirect(HttpServletRequest req, 
			HttpServletResponse resp, String sitePath) {
		try {
			req.getServletContext()
			.getRequestDispatcher(sitePath)
			.forward(req,resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void JSONObjHandler(ObjectMapper obm, 
			ArrayList<ReimbReq> r, PrintWriter prnt, String JSONtoStr) {
		try {
			JSONtoStr = obm
				.writeValueAsString(r);
			System.out.println(JSONtoStr);
			prnt.write(JSONtoStr);
		} catch (JsonProcessingException ae) {
			ae.printStackTrace();
		}
	}
}
	
	


