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
	private static List<User> users = null;
	private static List<Role> roles = null;
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
		 users = serviceFacade.getUserDao().findAllUsers();
		 request.setAttribute("users", users);
		 this.getServletContext().getRequestDispatcher("/pages/user/list.jsp").forward(request, response);
	 }
	    
	    
	    
	    private void createView(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
	    	roles = serviceFacade.getRoleDao().findAllRoles();
	    	request.setAttribute("roles", roles);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/createUpdateUser.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    private void create(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
	    	User u = new User();
	    	u.setIdentifiant(request.getParameter("identifiant"));
	    	u.setPassword(request.getParameter("password"));
	    	u.setNickname(request.getParameter("nickname"));
	    	u.setCivilite(request.getParameter("civilite"));
	    	Role r = serviceFacade.getRoleDao().findRoleById(Integer.parseInt(request.getParameter("role")));
	    	u.setRole(r);
	    	
	    	serviceFacade.getUserDao().createUtilisateur(u);
	    	list(request, response);
	    }
	    
	    
	    
	    private void updateView(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	User user = serviceFacade.getUserDao().findUserById(id);
	    	roles = serviceFacade.getRoleDao().findAllRoles();
	    	request.setAttribute("roles", roles);
	    	request.setAttribute("user", user);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/createUpdateUser.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    private void update(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	User u = serviceFacade.getUserDao().findUserById(id);
	    	u.setIdentifiant(request.getParameter("identifiant"));
	    	u.setPassword(request.getParameter("password"));
	    	u.setNickname(request.getParameter("nickname"));
	    	u.setCivilite(request.getParameter("civilite"));
	    	Role r = serviceFacade.getRoleDao().findRoleById(Integer.parseInt(request.getParameter("role")));
	    	u.setRole(r);
	    	serviceFacade.getUserDao().updateUserWithoutPassword(u);
	    	list(request, response);
	    }
	    
	    
	    
	    private void delete(HttpServletRequest request, HttpServletResponse response, int id) throws  IOException, ServletException {
	    	User user = serviceFacade.getUserDao().findUserById(id);
	    	serviceFacade.getUserDao().deleteUtilisateur(user);
	    	
	    	list(request, response);
	    }
}
