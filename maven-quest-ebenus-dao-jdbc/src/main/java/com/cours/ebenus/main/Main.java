/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.dao.impl.UtilisateurDao;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	/*
    	IServiceFacade serviceFacade = new ServiceFacade();
    	
    	IRoleDao rDao = new RoleDao();
    	IUtilisateurDao uDao = new UtilisateurDao();
    	
    	List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    	List<Role> roles = new ArrayList<Role>();
    	
    	Role r = rDao.findRoleById(1);
    	r.setIdentifiant("blmabla");
    	roles.add(rDao.createRole(r));
    	
    	Utilisateur u = uDao.findUtilisateurById(25);
    	
    	System.out.println(rDao.deleteRole(r));
    	*/
    	//utilisateurs.addAll(uDao.findUtilisateursByPrenom("Nicolas"));
    	
    	

    	
    	
    	//System.out.println(utilisateurs);
    	
    	
    	
    	//Utilisateur u = uDao.findUtilisateurById(21);
    	
    	
    	//u.setIdUtilisateur(21);
    	//u.setIdentifiant("azerty2@gmail.com");
    	
    /*	Utilisateur us = new Utilisateur();
    	us.setCivilite(u.getCivilite());
    	us.setPrenom(u.getPrenom());
    	us.setNom(u.getNom());
    	us.setMotPasse(u.getMotPasse());
    	us.*/
    			
    	
    	//Utilisateur test = uDao.authenticate("teqsdqst@gmail.com", "passw0rd");
    	
    	//System.out.println(test);
    }
}
