/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.CustomException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualListUtilisateurDao /*extends AbstractListDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    private List<Utilisateur> utilisateursListDataSource = null;

    public ManualListUtilisateurDao() {
        //super(Utilisateur.class, DataSource.getInstance().getUtilisateursListDataSource());	
    	utilisateursListDataSource = DataSource.getInstance().getUtilisateursListDataSource();
    }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
    	
        return utilisateursListDataSource;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursListDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
  
    	for(int i = 0; i < utilisateursListDataSource.size(); i++) {
    		Utilisateur user = utilisateursListDataSource.get(i);
    		if(user.getIdUtilisateur() == idUtilisateur) {
    			
    			return user;
    		}
    	}
    	
    	return null;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++)
    	{
    		if(utilisateursListDataSource.get(i).getPrenom() == prenom){		
    			found.add(utilisateursListDataSource.get(i));
    		}
    	}
    	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++)
    	{
    		if(utilisateursListDataSource.get(i).getNom() == nom){		
    			found.add(utilisateursListDataSource.get(i));
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
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++)
    	{
    		if(utilisateursListDataSource.get(i).getIdentifiant() == identifiant){		
    			found.add(utilisateursListDataSource.get(i));
    		}
    	} 	
        return found;
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) throws CustomException  {
    	boolean exist = false;
    	boolean identifiantExist = false;
    	boolean idExist = false;
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++) {
    		
    		if(utilisateursListDataSource.get(i).getIdUtilisateur() == user.getIdUtilisateur()){
    			idExist = true;	
    		}
    		
    		if(utilisateursListDataSource.get(i).getIdentifiant() == user.getIdentifiant()){
    			identifiantExist = true;	
    		}

    		if(utilisateursListDataSource.get(i).equals(user)){
    			exist = true;
    		}
    	}
    		
    	if(exist) {
    		String msg = "Une erreur s’est produite, cet objet existe deja.";
            throw new CustomException(msg, -1); 
    	}
    	else {
    		Utilisateur u = new Utilisateur(user);
    		
    		if(user.getIdUtilisateur() == null) {
    			u.setIdUtilisateur(utilisateursListDataSource.size() + 1);
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
    		utilisateursListDataSource.add(u);
    		return u;
    		}
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        for (int i = 0; i < utilisateursListDataSource.size(); i++) {
            if(utilisateursListDataSource.get(i).getIdUtilisateur() == user.getIdUtilisateur()) {
            	utilisateursListDataSource.get(i).setIdentifiant(user.getIdentifiant());
            	utilisateursListDataSource.get(i).setCivilite(user.getCivilite());
            	utilisateursListDataSource.get(i).setPrenom(user.getPrenom());
            	utilisateursListDataSource.get(i).setNom(user.getNom());
            	utilisateursListDataSource.get(i).setIdentifiant(user.getIdentifiant());
            	utilisateursListDataSource.get(i).setDateNaissance(user.getDateNaissance());
            	utilisateursListDataSource.get(i).setDateCreation(user.getDateCreation());
            	utilisateursListDataSource.get(i).setDateModification(user.getDateModification());
            	utilisateursListDataSource.get(i).setActif(user.isActif());
            	utilisateursListDataSource.get(i).setMarquerEffacer(user.isMarquerEffacer());
            	utilisateursListDataSource.get(i).setVersion(user.getVersion());
            	utilisateursListDataSource.get(i).setRole(user.getRole());
            	
                return utilisateursListDataSource.get(i);
            }
        }
        return null;
    }
 

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursListDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	
        for (int i = 0; i < utilisateursListDataSource.size(); i++) {
            if(utilisateursListDataSource.get(i).equals(user)) {
            	utilisateursListDataSource.remove(i);
                return true;
            }
        }
        return false;
    }
    	


    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) { 	
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++)
    	{
    		if(utilisateursListDataSource.get(i).getRole().getIdRole() == idRole){		
    			found.add(utilisateursListDataSource.get(i));
    		}
    	} 	
        return found;
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {	
    	List<Utilisateur> found = new ArrayList<Utilisateur>();
    	
    	for(int i = 0; i < utilisateursListDataSource.size(); i++)
    	{
    		if(utilisateursListDataSource.get(i).getRole().getIdentifiant() == identifiantRole){		
    			found.add(utilisateursListDataSource.get(i));
    		}
    	} 	
        return found;
    }
}
