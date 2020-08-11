/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.InputStream;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.google.gson.Gson;
;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "CrudUserServlet", urlPatterns = {"/CrudUserServlet"})
public class CrudUserServlet extends HttpServlet {

	 private static IServiceFacade serviceFacade = null;
	 private static List<Utilisateur> utilisateurs = null;
	 private static List<Role> roles = null;
    /**
     * Méthode d'initialisation de la Servlet
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
    	serviceFacade = new ServiceFacade();
    }

    /**
     * Méthode appelée lors d'une requête HTTP GET
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 HttpSession session = request.getSession();
    	 Utilisateur user = (Utilisateur) session.getAttribute("user");
    	if (user == null) {
    		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    	} else {
        	String action = request.getParameter("action");

        	System.out.println(action);

        	if(action == null) {
        		listUtilisateur(request, response);
        	}else if(action.equalsIgnoreCase("delete")) {
        		delete(request, response);
        	}else if(action.equalsIgnoreCase("edit")) {
        		editForm(request,response);
        	}else if(action.equalsIgnoreCase("add")) {
        		addForm(request,response);
        	}else if(action.equalsIgnoreCase("logout")) {
        		logout(request,response);
        	}else if(action.equalsIgnoreCase("xml")) {
        		exportXml(request,response);
        	}else if(action.equalsIgnoreCase("json")) {
        		exportJson(request,response);
        	}else if(action.equalsIgnoreCase("expjson")) {
        		Utilisateur u = null;
        		u = (Utilisateur) session.getAttribute("user");
        		u = serviceFacade.getUtilisateurDao().findUtilisateurById(u.getIdUtilisateur());
        		request.setAttribute("user", u);
            	request.setAttribute("utilisateurs", utilisateurs);
        		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUsers.jsp").forward(request, response);
        	}
    	}	
    }
    
    /**
     * Méthode appelée lors d'une requête HTTP POST
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	String action = request.getParameter("action");
    	System.out.println(action);

    	if(action == null) {
    		listUtilisateur(request, response);
    	}else if(action.equalsIgnoreCase("update")) {
    		update(request,response);
    	}else if(action.equalsIgnoreCase("add")) {
    		add(request,response);
    	}

    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        HttpSession session=request.getSession();  
        session.invalidate();  
          
        this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    }
    
    private void listUtilisateur(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	Utilisateur u = null;
    	HttpSession session = request.getSession();
    	utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		u = (Utilisateur) session.getAttribute("user");
		u = serviceFacade.getUtilisateurDao().findUtilisateurById(u.getIdUtilisateur());
		request.setAttribute("user", u);
    	request.setAttribute("utilisateurs", utilisateurs);
		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUsers.jsp").forward(request, response);
    }
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur user = serviceFacade.getUtilisateurDao().findUtilisateurById(id);
		
		serviceFacade.getUtilisateurDao().deleteUtilisateur(user);
		listUtilisateur(request, response);
    }
    
    private void editForm(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur user = serviceFacade.getUtilisateurDao().findUtilisateurById(id);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/crudUser/addUpdateUser.jsp");
		request.setAttribute("user", user);
        dispatcher.forward(request, response);

    }
    
    private void addForm(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/crudUser/addUpdateUser.jsp");
        dispatcher.forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur modifiedUser = new Utilisateur();
		
		modifiedUser.setPrenom(request.getParameter("firstname"));
		modifiedUser.setNom(request.getParameter("lastname"));
		modifiedUser.setIdentifiant(request.getParameter("email"));
		modifiedUser.setCivilite(request.getParameter("sex"));
		
		String dateNaissance = request.getParameter("dteNaiss");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        modifiedUser.setDateNaissance(date);
        
        int idRole = Integer.parseInt(request.getParameter("role"));
        Role role = null;
        role = serviceFacade.getRoleDao().findRoleById(idRole);
        
        modifiedUser.setRole(role);
        modifiedUser.setIdUtilisateur(id);
        
        serviceFacade.getUtilisateurDao().updateUserWithoutPassword(modifiedUser);

        listUtilisateur(request, response);
    }
    
    private void add(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {

		Utilisateur newUser = new Utilisateur();
		
		newUser.setPrenom(request.getParameter("firstname"));
		newUser.setNom(request.getParameter("lastname"));
		newUser.setIdentifiant(request.getParameter("email"));
		newUser.setCivilite(request.getParameter("sex"));
		newUser.setMotPasse(request.getParameter("password"));
		
		String dateNaissance = request.getParameter("dteNaiss");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newUser.setDateNaissance(date);
        
        int idRole = Integer.parseInt(request.getParameter("role"));
        Role role = null;
        role = serviceFacade.getRoleDao().findRoleById(idRole);
        
        newUser.setRole(role);
        
        serviceFacade.getUtilisateurDao().createUtilisateur(newUser);

        listUtilisateur(request, response);
    }
    
    private void exportXml(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
    	List<Utilisateur> utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
    	
    	/*XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("persons", PersonList.class);
        xstream.addImplicitCollection(PersonList.class, "list");

        PersonList list = new PersonList();
        list.add(new Person("ABC",12,"address"));
        list.add(new Person("XYZ",20,"address2"));

        String xml = xstream.toXML(list);
        
        response.sendRedirect("/maven-quest-ebenus-web/CrudUserServlet");*/

        }
    

    private void exportJson(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {

	
       /* try(FileWriter file = new FileWriter("/WEB-INF/utilisateurs.json")) {
        		file.write(json);
        		file.flush();

            } catch (IOException e) {
                    System.out.println(e.getMessage());
            }
        	
        	try {
        	      File myObj = new File("C:\\\\Users\\\\Jimmy\\\\Desktop\\\\Nouveau dossier (2)\\\\group-777918\\\\maven-quest-ebenus-web\\\\src\\\\main\\\\webapp\\\\WEB-INF/utilisateurs.json");
        	      if (myObj.createNewFile()) {
        	        System.out.println("File created: " + myObj.getName());
        	        try(FileWriter file = new FileWriter(myObj)) {      
                		file.write(json);
                		file.flush();
                    } catch (IOException e) {
                            System.out.println(e.getMessage());
                    }
        	      } else {
	        	        System.out.println("File already exists.");
	        	        if(myObj.delete()){  
		        	        System.out.println(myObj.getName() + " deleted");   //getting and printing the file name  
							try(FileWriter file = new FileWriter(myObj)) {
		                		file.write(json);
		                		file.flush();  
		                    } catch (IOException e) {
		                            System.out.println(e.getMessage());
		                    } 
	        	        }  
	        	        else{  
	        	        System.out.println("failed");  
	        	        } 
        	      }
        	    } catch (IOException e) {
        	      System.out.println("An error occurred.");
        	      e.printStackTrace();
        	    }
            response.sendRedirect("/maven-quest-ebenus-web/CrudUserServlet?action=expjson");
            */
		    	List<Utilisateur> utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
		    	
		    	String json = new Gson().toJson(utilisateurs);
		    	byte[] bytes = json.getBytes();
		    	
		    	System.out.println(bytes);
            
                
                try {
                    // create a new output stream
                    OutputStream os = new FileOutputStream("C:\\Users\\Jimmy\\Desktop\\Nouveau dossier (2)\\group-777918\\maven-quest-ebenus-web\\src\\main\\webapp\\WEB-INF/utilisateurs.json");

                    // craete a new input stream
                    FileInputStream is = new FileInputStream("C:\\Users\\Jimmy\\Desktop\\Nouveau dossier (2)\\group-777918\\maven-quest-ebenus-web\\src\\main\\webapp\\WEB-INF/utilisateurs.json");

                    // write something
                    os.write(bytes);

                    // read what we wrote
                    for (int i = 0; i < bytes.length; i++) {
                       System.out.print("" + (char) is.read());
                    }
                 } catch (Exception ex) {
                    ex.printStackTrace();
                 }
           
       }
       
    

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
