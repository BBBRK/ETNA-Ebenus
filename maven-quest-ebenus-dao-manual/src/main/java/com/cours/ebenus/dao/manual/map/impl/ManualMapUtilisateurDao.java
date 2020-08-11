/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapUtilisateurDao /*extends AbstractMapDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualMapUtilisateurDao.class);
    private Map<Integer, Utilisateur> utilisateursMapDataSource = DataSource.getInstance().getUtilisateursMapDataSource();

    //public ManualMapUtilisateurDao() {
       //super(Utilisateur.class, DataSource.getInstance().getUtilisateursMapDataSource());
    //}
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
    	List<Utilisateur> listUser = new ArrayList<Utilisateur>();

    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
            Utilisateur found = entry.getValue();
            listUser.add(found);
        }
    	
        return listUser;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursMapDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	Utilisateur found = null;
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getIdUtilisateur() == idUtilisateur) {
        		found = entry.getValue();
        	}
        }
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getPrenom() == prenom) {
        		found.add(entry.getValue());
        	}
        }
    	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getNom() == nom) {
        		found.add(entry.getValue());
        	}
        }
    	 	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont l'identifiant est égal au paramètre
     * passé
     *
     * @param identifiant Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getIdentifiant() == identifiant) {
        		found.add(entry.getValue());
        	}
        }
    	 	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getRole().getIdRole() == idRole) {
        		found.add(entry.getValue());
        	}
        }
    	 	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue().getRole().getIdentifiant() == identifiantRole) {
        		found.add(entry.getValue());
        	}
        }
    	 	
        return found;
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        boolean exist = false;
    	boolean identifiantExist = false;
    	boolean idExist = false;
    	
        for(Map.Entry<Integer, Utilisateur> entry : utilisateursMapDataSource.entrySet()) {
        	if(entry.getValue() == user) {
        		exist = true;
        		}
        	if(entry.getValue().getIdentifiant() == user.getIdentifiant()) {
        		identifiantExist = true;
        	}
        	if(entry.getValue().getIdUtilisateur() == user.getIdUtilisateur()) {
        		idExist = true;
        	}
        }
          
    	if(exist) {
    		String msg = "Une erreur s’est produite, cet objet existe deja.";
            throw new CustomException(msg, -1); 
    	}
    	else {
    		Utilisateur u = new Utilisateur(user);
    		
    		
    		if(user.getIdUtilisateur() == null) {
    			u.setIdUtilisateur(utilisateursMapDataSource.size() + 1);
    		}
    		
    		if(identifiantExist == true) {
                String msg = "Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant ";
                msg += user.getIdentifiant();
                msg += " dans l’application";
                throw new CustomException(msg, -1); 
    		}
    		
    		if (idExist == true) {
                String msg = "Une erreur s’est produite, il existe déjà un utilisateur avec l'id ";
                msg += user.getIdUtilisateur();
                msg += " dans l’application";
                throw new CustomException(msg, -1); 
    		}
    		utilisateursMapDataSource.put(u.getIdUtilisateur(), u);
    		return u;
    		}
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	for(Map.Entry<Integer,Utilisateur> entry: utilisateursMapDataSource.entrySet()) {
    		Utilisateur u = entry.getValue();
            if(u.getIdUtilisateur() == user.getIdUtilisateur()) {
            	utilisateursMapDataSource.replace(entry.getKey(), u, user);
            	return user;
            }
        }
    	
    	return null;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursMapDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	for(Map.Entry<Integer,Utilisateur> paireCleValeur: utilisateursMapDataSource.entrySet()) {
    		Utilisateur u = paireCleValeur.getValue();
            if(u.getIdUtilisateur() == user.getIdUtilisateur()) {
            	utilisateursMapDataSource.remove(paireCleValeur.getKey());
            	return true;
            }
        }
    	
        return false;
    }
}
