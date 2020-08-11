/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapUtilisateurDao;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
        IServiceFacade serviceFacade = new ServiceFacade();

        //System.out.println(serviceFacade.getRoleDao().findAllRoles().size());

        
        IUtilisateurDao uDao = new ManualListUtilisateurDao();

        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        
        utilisateurs.addAll(uDao.findAllUtilisateurs());

        Utilisateur u = new Utilisateur(29, "Mr", "Jerome", "Cantin", "admisn@gmail.com", "passw0rd", new Date(System.currentTimeMillis()), new Role(1, "Administrateur", "Le rôle administrateur"));
        

        utilisateurs.add(uDao.createUtilisateur(u));
       
        
        System.out.println(utilisateurs.size());
        System.out.println(uDao.findAllUtilisateurs());
        

    }
}
