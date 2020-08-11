/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapRoleDao /*extends AbstractMapDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualMapRoleDao.class);
    private Map<Integer, Role> rolesListDataSource = DataSource.getInstance().getRolesMapDataSource();

    //public ManualMapRoleDao() {
    //    super(Role.class, DataSource.getInstance().getRolesMapDataSource());
    //}
    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
    	List<Role> listRole = new ArrayList<Role>();

    	
        for(Map.Entry<Integer, Role> entry : rolesListDataSource.entrySet()) {
            Role found = entry.getValue();
            listRole.add(found);
        }
    	
        return listRole;
    }

    /**
     * Méthode qui retourne le rôle d'id passé en paramètre de la database (ici
     * rolesListDataSource)
     *
     * @param idRole L'id du rôle à récupérer
     * @return Le rôle d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Role findRoleById(int idRole) {
    	Role found = null;
        for(Entry<Integer, Role> entry : rolesListDataSource.entrySet()) {
        	if(entry.getValue().getIdRole() == idRole) {
        		found = entry.getValue();
        	}
        }
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les rôles de la database (ici
     * rolesListDataSource) dont l'identifiant est égal au paramètre passé
     *
     * @param identifiantRole L'identifiant des rôles à récupérer
     * @return Une liste de tous les rôles dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {

    	List<Role> found = new ArrayList<Role>();
        for(Entry<Integer, Role> entry : rolesListDataSource.entrySet()) {
        	if(entry.getValue().getIdentifiant() == identifiantRole) {
        		found.add(entry.getValue());
        	}
        }
        return found;
    }

    /**
     * Méthode qui permet d'ajouter à rôle dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à ajouter
     * @return Le rôle ajouté ou null si échec
     */
    @Override
    public Role createRole(Role role) {
        boolean exist = false;
    	boolean idExist = false;
    	
        for(Map.Entry<Integer, Role> entry : rolesListDataSource.entrySet()) {
        	if(entry.getValue() == role) {
        		exist = true;
        		}

        	if(entry.getValue().getIdRole() == role.getIdRole()) {
        		idExist = true;
        	}
        }
          
    	if(exist) {
    		String msg = "Une erreur s’est produite, cet objet existe deja.";
            throw new CustomException(msg, -1); 
    	}
    	else {
    		Role r = new Role(role);
    		
    		if(role.getIdRole() == null) {
    			r.setIdRole(rolesListDataSource.size() + 1);
    		}
    		
    		if (idExist == true) {
                String msg = "Une erreur s’est produite, il existe déjà un role avec l'id ";
                msg += role.getIdRole();
                msg += " dans l’application";
                throw new CustomException(msg, -1); 
    		}
    		rolesListDataSource.put(r.getIdRole(), r);
    		return r;
    		}
    }

    /**
     * Méthode qui permet d'update un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à modifier
     * @return Le rôle modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Role updateRole(Role role) {
    	for(Map.Entry<Integer,Role> paireCleValeur: rolesListDataSource.entrySet()) {
            Role r = paireCleValeur.getValue();
            if(r.getIdRole() == role.getIdRole()) {
            	rolesListDataSource.replace(paireCleValeur.getKey(), r, role);
            	return role;
            }
        }

    	return null;
    }

    /**
     * Méthode qui permet de supprimer un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteRole(Role role) {
    	for(Map.Entry<Integer,Role> paireCleValeur: rolesListDataSource.entrySet()) {
            Role r = paireCleValeur.getValue();
            if(r.getIdRole() == role.getIdRole()) {
            	rolesListDataSource.remove(paireCleValeur.getKey());
            	return true;
            }
        }
    	
        return false;
    }

}
