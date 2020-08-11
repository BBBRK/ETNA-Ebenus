package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static IServiceFacade serviceFacade = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        serviceFacade = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String action = request.getParameter("action");
       	System.out.println(action);
		
	   	if (user == null) {
	   		System.out.println("here");
	   		if(action == null) {
	   			System.out.println("heeere");
	   			response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
	   		}else if(action.equalsIgnoreCase("update")){
	       		updateUserView(request, response, user);
	   	}else if(action.equalsIgnoreCase("subscribe")){
       		System.out.println("test");
       		subscribeUserView(request, response);
    	} 
      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String action = request.getParameter("action");
    	System.out.println(action);
    	
    	if(action.equalsIgnoreCase("updateuser")) {
    		updateUser(request, response, user);
    	}else if(action.equalsIgnoreCase("subscribe")){
    		createUser(request, response);
    	}
	}
	
	 private void updateUserView(HttpServletRequest request, HttpServletResponse response, User user) throws  IOException, ServletException {
		 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/updateUser.jsp");
		 request.setAttribute("user", user);
	     dispatcher.forward(request, response);	 
	 }
	 
	 private void updateUser(HttpServletRequest request, HttpServletResponse response, User user) throws  IOException, ServletException {
		 user.setIdentifiant(request.getParameter("identifiant"));
		 user.setPassword(request.getParameter("password"));
		 user.setNickname(request.getParameter("nickname"));
		 user.setCivilite(request.getParameter("civilite"));
		 serviceFacade.getUserDao().updateUtilisateur(user);
		 
		 response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
		 return;
	 }
	 
	 private void subscribeUserView(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 System.out.println("test2");
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login/subscribe.jsp");
	     dispatcher.forward(request, response);	 
	 }
	 
	 private void createUser(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		 User newUser = new User();
		 newUser.setIdentifiant(request.getParameter("identifiant"));
		 newUser.setPassword(request.getParameter("password"));
		 newUser.setNickname(request.getParameter("nickname"));
		 newUser.setCivilite(request.getParameter("civilite"));
		 Role role = (Role) serviceFacade.getRoleDao().findRoleById(2);
		 newUser.setRole(role);

		 serviceFacade.getUserDao().createUtilisateur(newUser);

		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login/login.jsp");
	     dispatcher.forward(request, response);	 
	 }
}
