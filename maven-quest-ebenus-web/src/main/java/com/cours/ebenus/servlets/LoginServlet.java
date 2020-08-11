/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade serviceFacade = null;
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASSWORD = "password";
    @Override
    public void init() throws ServletException {
    	serviceFacade = new ServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	  HttpSession session = request.getSession();
    	  Utilisateur user = (Utilisateur) session.getAttribute("user");

          if(user == null) {
              this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
          } else {
              response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
          }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
	        String email = request.getParameter(CHAMP_EMAIL);
	        String password = request.getParameter(CHAMP_PASSWORD);
	
			Utilisateur u = serviceFacade.getUtilisateurDao().authenticate(email, password);
			
			if(u != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
				return;
			}else {
				this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
	        }
		}
    
    
    

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
