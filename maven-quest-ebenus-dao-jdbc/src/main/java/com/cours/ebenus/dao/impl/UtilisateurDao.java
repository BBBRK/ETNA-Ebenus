/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.cours.ebenus.dao.exception.EbenusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao /*extends AbstractDao<Utilisateur>*/ implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;

    private List<Utilisateur> request(ResultSet rs, List<Utilisateur> utilisateurs) {
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setIdentifiant(rs.getString("role.identifiant"));
                role.setDescription(rs.getString("role.description"));
                role.setVersion(rs.getInt("role.version"));

                Utilisateur user = new Utilisateur();
                user.setIdUtilisateur(rs.getInt("idUtilisateur"));
                user.setRole(role);
                user.setCivilite(rs.getString("civilite"));
                user.setPrenom(rs.getString("prenom"));
                user.setNom(rs.getString("nom"));
                user.setIdentifiant(rs.getString("utilisateur.identifiant"));
                user.setMotPasse(rs.getString("utilisateur.motPasse"));
                user.setDateNaissance(rs.getTimestamp("dateNaissance"));
                user.setDateCreation(rs.getTimestamp("dateCreation"));
                user.setDateModification(rs.getTimestamp("dateModification"));
                user.setActif(rs.getBoolean("actif"));
                user.setMarquerEffacer(rs.getBoolean("marquerEffacer"));
                user.setVersion(rs.getInt("version"));
                utilisateurs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }
    private Utilisateur request(ResultSet rs, Utilisateur utilisateur) {
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setIdentifiant(rs.getString("role.identifiant"));
                role.setDescription(rs.getString("role.description"));
                role.setVersion(rs.getInt("role.version"));

                utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
                utilisateur.setRole(role);
                utilisateur.setCivilite(rs.getString("civilite"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setIdentifiant(rs.getString("utilisateur.identifiant"));
                utilisateur.setMotPasse(rs.getString("utilisateur.motPasse"));
                utilisateur.setDateNaissance(rs.getTimestamp("dateNaissance"));
                utilisateur.setDateCreation(rs.getTimestamp("dateCreation"));
                utilisateur.setDateModification(rs.getTimestamp("dateModification"));
                utilisateur.setActif(rs.getBoolean("actif"));
                utilisateur.setMarquerEffacer(rs.getBoolean("marquerEffacer"));
                utilisateur.setVersion(rs.getInt("version"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    
    //public UtilisateurDao() {
    //    super(Utilisateur.class);
    //}
    
    @Override
    public List<Utilisateur> findAllUtilisateurs() {	   	
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM utilisateur "
    					 + "INNER JOIN role "
    					 + "ON utilisateur.idRole = role.idRole;";

    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		rs = preparedStatement.executeQuery(selectSQL);
    		
    		utilisateurList = request(rs, utilisateurList);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	
        return utilisateurList;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	Utilisateur found = new Utilisateur();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;

		try {
			
	    	String selectSQL = "SELECT * FROM utilisateur "
					 + "INNER JOIN role "
					 + "ON utilisateur.idRole = role.idRole "
					 + "WHERE utilisateur.idUtilisateur = ?;";

    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setInt(1, idUtilisateur);
    		rs = preparedStatement.executeQuery();

    		found = request(rs, found);
    		
            if(found.getIdentifiant() == null) {
            	found = null;
            }
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(preparedStatement, rs);
        }
        return found;
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	try{
 			String selectSQL = "SELECT * FROM utilisateur "
					 + "INNER JOIN role "
					 + "ON utilisateur.idRole = role.idRole "
					 + "WHERE utilisateur.prenom = ?;";
		

	    		preparedStatement = connection.prepareStatement(selectSQL);
	    		preparedStatement.setString(1, prenom);
	    		rs = preparedStatement.executeQuery();
	    		utilisateurList = request(rs, utilisateurList);
	    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM utilisateur "
				 + "INNER JOIN role "
				 + "ON utilisateur.idRole = role.idRole "
				 + "WHERE utilisateur.nom = ?;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setString(1, nom);
    		rs = preparedStatement.executeQuery();
    		utilisateurList = request(rs, utilisateurList);
    		
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM utilisateur "
				 + "INNER JOIN role "
				 + "ON utilisateur.idRole = role.idRole "
				 + "WHERE utilisateur.identifiant = ?;";

    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setString(1, identifiant);
    		rs = preparedStatement.executeQuery();
    		utilisateurList = request(rs, utilisateurList);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM utilisateur "
				 + "INNER JOIN role "
				 + "ON utilisateur.idRole = role.idRole "
				 + "WHERE utilisateur.idRole = ?;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setInt(1, idRole);
    		rs = preparedStatement.executeQuery();
    		
    		utilisateurList = request(rs, utilisateurList);
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return utilisateurList;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	List<Utilisateur> utilisateurList = new ArrayList<Utilisateur>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM utilisateur "
				 + "INNER JOIN role "
				 + "ON utilisateur.idRole = role.idRole "
				 + "WHERE role.identifiant = ?;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setString(1, identifiantRole);
    		rs = preparedStatement.executeQuery();
    		utilisateurList = request(rs, utilisateurList);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return utilisateurList;
    }
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	int isExecuted = 0;
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
        Date date = new Date();
        Timestamp dateCreation = new Timestamp(date.getTime());
        Timestamp dateModification = dateCreation;
        Timestamp dateNaissance = new Timestamp(user.getDateNaissance().getTime());
    	
        List<Utilisateur> IdentifiantUsed = findUtilisateurByIdentifiant(user.getIdentifiant());
        if (IdentifiantUsed.size() != 0) {
            throw new EbenusException("L'identifiant est déjà utilisé", -1);
        } else {
	        try {
	        	String createSQL = "INSERT INTO utilisateur "
	   				 + "(idRole, civilite, prenom, nom, identifiant, motPasse, dateNaissance, dateCreation, dateModification) "
	   				 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	       	
	        	preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);
	        	preparedStatement.setInt(1, user.getRole().getIdRole());
	        	preparedStatement.setString(2, user.getCivilite());
	        	preparedStatement.setString(3, user.getPrenom());
	        	preparedStatement.setString(4, user.getNom());
	        	preparedStatement.setString(5, user.getIdentifiant());
	        	preparedStatement.setString(6, user.getMotPasse());
	        	preparedStatement.setTimestamp(7, dateNaissance);
	        	preparedStatement.setTimestamp(8, dateCreation);
	       	    preparedStatement.setTimestamp(9, dateModification);
	       	    
	       	    isExecuted = preparedStatement.executeUpdate();
	       	    
		        rs = preparedStatement.getGeneratedKeys();
		        if(rs.next()) {
		        	int id = rs.getInt(1);
		        	user.setIdUtilisateur(id);
		        	user.setDateCreation(dateCreation);
		        	user.setDateModification(dateModification);
		        }

	        } catch(SQLException e) {
	        	 e.printStackTrace();
	        }finally {
	            lch.closeSqlResources(preparedStatement, rs);
	        }
		      
	        if(isExecuted == 0) {
	            return null;
	        }
	        else {
	            return user;
	        }
        }
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) throws EbenusException {
    	int isExecuted = 0;
    	PreparedStatement preparedStatement = null;
    	
        Date date = new Date();
        Timestamp dateModification = new Timestamp(date.getTime());
        Timestamp dateNaissance = new Timestamp(user.getDateNaissance().getTime());
    	

	    try {
	        String updateSQL = "UPDATE utilisateur "
	   			 + "SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateNaissance = ?, dateModification = ? "
	   			 + "WHERE utilisateur.IdUtilisateur = ?;";
	       	
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setInt(1, user.getRole().getIdRole());
	        preparedStatement.setString(2, user.getCivilite());
	        preparedStatement.setString(3, user.getPrenom());
	        preparedStatement.setString(4, user.getNom());
	        preparedStatement.setString(5, user.getIdentifiant());
	        preparedStatement.setString(6, user.getMotPasse());
	        preparedStatement.setTimestamp(7, dateNaissance);
	       	preparedStatement.setTimestamp(8, dateModification);
	       	preparedStatement.setInt(9, user.getIdUtilisateur());
	       	    
	       	user.setDateModification(dateModification);
	       	    
	       	isExecuted = preparedStatement.executeUpdate();

	        	
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
            lch.closeSqlResources(preparedStatement, null);
        }
	    
	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return user;
	    }
    }
    
    public Utilisateur updateUserWithoutPassword(Utilisateur user) throws EbenusException {
    	int isExecuted = 0;
    	PreparedStatement preparedStatement = null;
    	
        Date date = new Date();
        Timestamp dateModification = new Timestamp(date.getTime());
        Timestamp dateNaissance = new Timestamp(user.getDateNaissance().getTime());
    	

	    try {
	        String updateSQL = "UPDATE utilisateur "
	   			 + "SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, dateNaissance = ?, dateModification = ? "
	   			 + "WHERE utilisateur.IdUtilisateur = ?;";
	       	
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setInt(1, user.getRole().getIdRole());
	        preparedStatement.setString(2, user.getCivilite());
	        preparedStatement.setString(3, user.getPrenom());
	        preparedStatement.setString(4, user.getNom());
	        preparedStatement.setString(5, user.getIdentifiant());
	        preparedStatement.setTimestamp(6, dateNaissance);
	       	preparedStatement.setTimestamp(7, dateModification);
	       	preparedStatement.setInt(8, user.getIdUtilisateur());
	       	    
	       	user.setDateModification(dateModification);
	       	    
	       	isExecuted = preparedStatement.executeUpdate();

	        	
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
            lch.closeSqlResources(preparedStatement, null);
        }
	    
	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return user;
	    }
    }


    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	int isExecuted = 0;
    	
    	try {
        	String deleteSQL = "DELETE FROM utilisateur "
        		+ "WHERE idUtilisateur = ?;";
        	
        	PreparedStatement preparedStatement;
        	
        	preparedStatement = connection.prepareStatement(deleteSQL);
        	preparedStatement.setInt(1, user.getIdUtilisateur());
        	isExecuted = preparedStatement.executeUpdate();
        	
    	} catch(SQLException e) {
	        e.printStackTrace();
	    }

	    if(isExecuted == 0) {
	    	return false;
	    }else {
	    	return true;
	    }
    }

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la
     * base de données
     *
     * @param email L'email de l'utilisateur
     * @param password Le password de l'utilisateur
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
    	Utilisateur user = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	try {
    		String SelectSQL = "SELECT * FROM utilisateur, "
    				+ "role WHERE utilisateur.idRole "
            		+ "AND utilisateur.identifiant = ? "
            		+ "AND utilisateur.motPasse = ?;";
        	
        	preparedStatement = connection.prepareStatement(SelectSQL);
        	preparedStatement.setString(1, email);
        	preparedStatement.setString(2, password);
        	
        	rs = preparedStatement.executeQuery();
        	
    		while (rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setIdentifiant(rs.getString("role.identifiant"));
                role.setDescription(rs.getString("role.description"));
                role.setVersion(rs.getInt("role.version"));
                
    			user = new Utilisateur();
                user.setIdUtilisateur(rs.getInt("idUtilisateur"));
                user.setRole(role);
                user.setCivilite(rs.getString("civilite"));
                user.setPrenom(rs.getString("prenom"));
                user.setNom(rs.getString("nom"));
                user.setIdentifiant(rs.getString("utilisateur.identifiant"));
                user.setMotPasse(rs.getString("utilisateur.motPasse"));
                user.setDateNaissance(rs.getTimestamp("dateNaissance"));
                user.setDateCreation(rs.getTimestamp("dateCreation"));
                user.setDateModification(rs.getTimestamp("dateModification"));
                user.setActif(rs.getBoolean("actif"));
                user.setMarquerEffacer(rs.getBoolean("marquerEffacer"));
                user.setVersion(rs.getInt("version"));
    		}
    		rs.close();
    		preparedStatement.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
        return user;
    }
}
