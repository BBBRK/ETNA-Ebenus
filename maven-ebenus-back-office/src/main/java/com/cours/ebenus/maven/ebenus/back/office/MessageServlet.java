package com.cours.ebenus.maven.ebenus.back.office;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Channel;
import com.cours.ebenus.maven.ebenus.dao.entities.Message;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static List<Message> messages = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        serviceFacade = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
    	 User u = (User) session.getAttribute("user");
     	 
    	if (u == null) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    	} else {
        	String action = request.getParameter("action");
        	System.out.println(action);
        	
        	if(action == null) {
        		list(request,response);
        	}else if(action.equalsIgnoreCase("delete")) {
        		int id = Integer.parseInt(request.getParameter("id"));
        		delete(request, response, id);
        	}
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	 private void list(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 messages = serviceFacade.getMessageDao().findAllMessages();
		 request.setAttribute("messages", messages);
		 this.getServletContext().getRequestDispatcher("/pages/message/list.jsp").forward(request, response);
	 }
	    
	 private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {

	    list(request, response);
	 }

}
