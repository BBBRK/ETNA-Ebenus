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
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class ChannelServlet
 */
@WebServlet("/ChannelServlet")
public class ChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IServiceFacade serviceFacade = null;
	private static List<Channel> channels = null;
	private static List<User> users = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChannelServlet() {
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
        	}else if(action.equalsIgnoreCase("create")) {
        		createView(request, response);
        	}else if(action.equalsIgnoreCase("update")) {
        		int id = Integer.parseInt(request.getParameter("id"));
        		updateView(request, response, id);
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
    	String action = request.getParameter("action");
    	System.out.println(action);
    	
    	if(action.equalsIgnoreCase("create")) {
    		create(request, response);
    	}else if(action.equalsIgnoreCase("update")) {
    		int id = Integer.parseInt(request.getParameter("id"));
    		update(request, response, id);
    	}
	}
	
	 private void list(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 channels = serviceFacade.getChannelDao().findAllChannels();
		 request.setAttribute("channels", channels);
		 this.getServletContext().getRequestDispatcher("/pages/channel/list.jsp").forward(request, response);
	 }
	    
	    
	    
	    private void createView(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
	    	users = serviceFacade.getUserDao().findAllUsers();
	    	request.setAttribute("users", users);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/channel/createUpdateChannel.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    private void create(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
	    	Channel c = new Channel();
	    	c.setName(request.getParameter("name"));
	    	c.setDescription(request.getParameter("description"));
	    	c.setCreator(Integer.parseInt(request.getParameter("creator")));
	    	serviceFacade.getChannelDao().createChannel(c);
	    	
	    	list(request, response);
	    }
	    
	    
	    
	    private void updateView(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	Channel channel = serviceFacade.getChannelDao().findChannelById(id);
	    	users = serviceFacade.getUserDao().findAllUsers();
	    	request.setAttribute("users", users);
	    	request.setAttribute("channel", channel);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/channel/createUpdateChannel.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    private void update(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	Channel channel = serviceFacade.getChannelDao().findChannelById(id);
	    	channel.setName(request.getParameter("name"));
	    	channel.setDescription(request.getParameter("description"));
	    	channel.setCreator(Integer.parseInt(request.getParameter("creator")));
	    	serviceFacade.getChannelDao().updateChannel(channel);
	    	
	    	list(request, response);
	    }
	    
	    
	    
	    private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	Channel channel = serviceFacade.getChannelDao().findChannelById(id);
	    	serviceFacade.getChannelDao().deleteChannel(channel);
	    	
	    	list(request, response);
	    }

}
