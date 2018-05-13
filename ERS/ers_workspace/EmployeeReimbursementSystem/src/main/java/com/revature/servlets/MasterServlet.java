package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MasterServlet extends HttpServlet {
   
	private static final long serialVersionUID = 6520317557229175093L;

	public MasterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		// Get a reference to the ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		// Write the POJO as JSON to the response
		response.getWriter().write(
				mapper.writeValueAsString(MasterDispatcher.process(request, response)));

	}

}