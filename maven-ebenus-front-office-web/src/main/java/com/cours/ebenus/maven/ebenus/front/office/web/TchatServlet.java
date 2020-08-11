package com.cours.ebenus.maven.ebenus.front.office.web;

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
import com.google.gson.Gson;


/**
 * Servlet implementation class TchatServlet
 */
@WebServlet("/TchatServlet")
public class TchatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static IServiceFacade serviceFacade = null;
	private static List<Channel> channels = null;
	private static List<Message> messagesByChannel = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TchatServlet() {
        super();
        serviceFacade = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
	   	if (user == null) {
	   		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
	   	} else {
	       	String action = request.getParameter("action");
	       	System.out.println(action);
	       	
	       	if(action == null) {
	       		listChannel(request, response);
	       	}else if(action.equalsIgnoreCase("logout")) {
	       		logout(request, response);
	       	}else if(action.equalsIgnoreCase("createview")) {
	       		createChannelView(request, response);
	       	}else if(action.equalsIgnoreCase("refresh")) {
	       		int idChannel = Integer.parseInt(request.getParameter("channel"));
	       		getMessages(request, response, idChannel);
	       	}else if(action.equalsIgnoreCase("delete")) {
	       		int idChannel = Integer.parseInt(request.getParameter("channel"));
	       		deleteMessageAndRefresh(request, response, idChannel);
	       	}else if(action.equalsIgnoreCase("deleteview")) {
	       		int idUser = Integer.parseInt(request.getParameter("iduser"));
	       		deleteChannelView(request, response, idUser);
	       	}else if(action.equalsIgnoreCase("updateview")) {
	       		int idUser = Integer.parseInt(request.getParameter("iduser"));
	       		updateChannelView(request, response, idUser);
	       	}else if(action.equalsIgnoreCase("updateviewform")) {
	       		int idChannel = Integer.parseInt(request.getParameter("channel"));
	       		updateChannelViewAjax(request, response, idChannel);
	       	}
       	}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
    	System.out.println(action);
    	
    	if(action.equalsIgnoreCase("createChannel")) {
    		createChannel(request, response);
       	}else if(action.equalsIgnoreCase("updateChannel")) {
       		int idChannel = Integer.parseInt(request.getParameter("channel"));
       		updateChannel(request, response, idChannel);
       	}else if(action.equalsIgnoreCase("deleteChannel")) {
       		deleteChannel(request, response);
       	}else if(action.equalsIgnoreCase("sendMessage")) {
       		int idChannel = Integer.parseInt(request.getParameter("channel"));
       		sendMessage(request, response, idChannel);
       	}
	 }
	
     private void logout(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        HttpSession session=request.getSession();  
        session.invalidate();  
          
        this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
     }
	
	 private void listChannel(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 HttpSession session = request.getSession();
		 User u = (User) session.getAttribute("user");
		 u = serviceFacade.getUserDao().findUserById(u.getIdUser());
		 channels = serviceFacade.getChannelDao().findAllChannels();
		 request.setAttribute("user", u);
		 request.setAttribute("channels", channels);
		 this.getServletContext().getRequestDispatcher("/pages/tchat/tchat.jsp").forward(request, response);
	 }
	 
	 private void createChannelView(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/tchat/createChannel.jsp");
	     dispatcher.forward(request, response);	 
	 }
	 
	 private void updateChannelView(HttpServletRequest request, HttpServletResponse response, int idCreator) throws  IOException, ServletException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/tchat/updateChannel.jsp");
		 channels = serviceFacade.getChannelDao().findChannelByCreator(idCreator);
		 request.setAttribute("channels", channels);
		 dispatcher.forward(request, response);	
	 }
	 
	 private void deleteChannelView(HttpServletRequest request, HttpServletResponse response, int idCreator) throws  IOException, ServletException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/tchat/deleteChannel.jsp");
		 channels = serviceFacade.getChannelDao().findChannelByCreator(idCreator);
		 request.setAttribute("channels", channels);
		 dispatcher.forward(request, response);	
	 }
	 
	 
	 private void createChannel(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 HttpSession session = request.getSession();
		 User u = (User) session.getAttribute("user");
		 u = serviceFacade.getUserDao().findUserById(u.getIdUser());
		 
		 Channel newChannel = new Channel();
		 newChannel.setName(request.getParameter("name"));
		 newChannel.setDescription(request.getParameter("description"));
		 newChannel.setCreator(u.getIdUser());
		 
		 serviceFacade.getChannelDao().createChannel(newChannel);

		 session.setAttribute("user", u);
		 response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
		 return;
	 }

	 
	 private void updateChannelViewAjax(HttpServletRequest request, HttpServletResponse response, int idChannel) throws  IOException, ServletException {
		 Channel channel = serviceFacade.getChannelDao().findChannelById(idChannel);
		 
		 String channelJson = new Gson().toJson(channel);
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 response.getWriter().write(channelJson);
	 }
	 
	 private void updateChannel(HttpServletRequest request, HttpServletResponse response, int idChannel) throws  IOException, ServletException {
		 HttpSession session = request.getSession();
		 User u = (User) session.getAttribute("user");
		 u = serviceFacade.getUserDao().findUserById(u.getIdUser());
		 
		 Channel updatedChannel = serviceFacade.getChannelDao().findChannelById(idChannel);
		 updatedChannel.setName(request.getParameter("name"));
		 updatedChannel.setDescription(request.getParameter("description")); 
		 serviceFacade.getChannelDao().updateChannel(updatedChannel);
		 
		 session.setAttribute("user", u);
		 response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
		 return;
	 }
	 
	 private void deleteChannel(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 HttpSession session = request.getSession();
		 User u = (User) session.getAttribute("user");
		 u = serviceFacade.getUserDao().findUserById(u.getIdUser());
		 
		 int idChannel = Integer.parseInt(request.getParameter("deletedChannel"));
		 Channel deletedChannel = serviceFacade.getChannelDao().findChannelById(idChannel);
		 serviceFacade.getChannelDao().deleteChannel(deletedChannel);
		 System.out.println("Chan deleted !");
		 
		 session.setAttribute("user", u);
		 response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
		 return;
	 }
	 
	
	 private void sendMessage(HttpServletRequest request, HttpServletResponse response, int idChannel) throws  IOException, ServletException {
		 HttpSession session = request.getSession();
		 User u = (User) session.getAttribute("user");
		 u = serviceFacade.getUserDao().findUserById(u.getIdUser());
		 String content = request.getParameter("content");
		 Channel c = serviceFacade.getChannelDao().findChannelById(idChannel);
		 Message newMessage = new Message();
		 newMessage.setContentMessage(content);
		 newMessage.setUserMessage(u);
		 newMessage.setChannelMessage(c);
		 
		 serviceFacade.getMessageDao().createMessage(newMessage);
	 }
	 
	 private void getMessages(HttpServletRequest request, HttpServletResponse response, int idChannel) throws  IOException, ServletException {
			 messagesByChannel = serviceFacade.getMessageDao().findMessageByIdChannel(idChannel);
			 String messagesJson = new Gson().toJson(messagesByChannel);

			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(messagesJson);
		 
	 }
	 
	 private void deleteMessageAndRefresh(HttpServletRequest request, HttpServletResponse response, int idChannel) throws  IOException, ServletException {
	     int idMessage = Integer.parseInt(request.getParameter("idMessage"));
	         deleteMessage(request, response, idMessage);

			 messagesByChannel = serviceFacade.getMessageDao().findMessageByIdChannel(idChannel);
			 String messagesJson = new Gson().toJson(messagesByChannel);

			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(messagesJson);
		 
	 }
	 
	 private void deleteMessage(HttpServletRequest request, HttpServletResponse response, int idMessage) throws  IOException, ServletException {
		 Message m = serviceFacade.getMessageDao().findMessageById(idMessage);
		 serviceFacade.getMessageDao().deleteMessage(m);
	 }
}
