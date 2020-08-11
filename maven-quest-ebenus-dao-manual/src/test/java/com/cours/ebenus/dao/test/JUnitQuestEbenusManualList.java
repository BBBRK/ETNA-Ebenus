package com.cours.ebenus.dao.test;

import java.awt.List;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

import junit.framework.Assert;


public class JUnitQuestEbenusManualList extends JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenusManualList.class);

   // private static IServiceFacade serviceFacade;

    @BeforeClass
    public static void init() throws Exception {
    	serviceFacade = new ServiceFacade();
    	utilisateurs = serviceFacade.getUtilisateurDao().findAllUtilisateurs();
    	roles = serviceFacade.getRoleDao().findAllRoles();

    }
}








