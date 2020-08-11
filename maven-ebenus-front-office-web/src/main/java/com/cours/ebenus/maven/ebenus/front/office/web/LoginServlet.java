package com.cours.ebenus.maven.ebenus.front.office.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade serviceFacade = null;
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASSWORD = "password";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
  	  
        if(user == null) {
            this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
        } else {
        	if(action == null) {
        		response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
        	}else if(action.equalsIgnoreCase("logout")){
           		logout(request, response);
        	}
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(CHAMP_EMAIL);
        String password = request.getParameter(CHAMP_PASSWORD);
        User u = serviceFacade.getUserDao().authenticate(email, password);
		
		if(u != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			response.sendRedirect(this.getServletContext().getContextPath() + "/TchatServlet");
			return;
		}else {
			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
        }
	}
	
	 private void logout(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
	        HttpSession session=request.getSession();  
	        session.invalidate();  
	          
	        this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
	 }

}
