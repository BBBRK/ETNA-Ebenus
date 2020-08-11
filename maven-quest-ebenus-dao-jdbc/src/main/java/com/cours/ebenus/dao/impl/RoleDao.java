/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.EbenusException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class RoleDao /*extends AbstractDao<Role>*/ implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;
    
    private List<Role> request(ResultSet rs, List<Role> roles) {
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setIdentifiant(rs.getString("role.identifiant"));
                role.setDescription(rs.getString("role.description"));
                role.setVersion(rs.getInt("role.version"));

                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }
    
    private Role request(ResultSet rs, Role role) {
        try {
            while (rs.next()) {
                role.setIdRole(rs.getInt("idRole"));
                role.setIdentifiant(rs.getString("role.identifiant"));
                role.setDescription(rs.getString("role.description"));
                role.setVersion(rs.getInt("role.version"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    //public RoleDao() {
    //    super(Role.class);
    //}
    @Override
    public List<Role> findAllRoles() {
    	List<Role> roleList = new ArrayList<Role>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM role;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		rs = preparedStatement.executeQuery(selectSQL);
    		roleList = request(rs, roleList);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
        return roleList;
    }
    

    @Override
    public Role findRoleById(int idRole) {
    	Role found = new Role();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM role "
				 + "WHERE role.idRole = ?;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setInt(1, idRole);
    		rs = preparedStatement.executeQuery();
    		found = request(rs, found);
    		
            if(found.getIdRole() == null) {
            	found = null;
            }
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return found;
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
    	List<Role> found = new ArrayList<Role>();
    	PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String selectSQL = "SELECT * FROM role "
				 + "WHERE role.identifiant = ?;";
    	
    	try {
    		preparedStatement = connection.prepareStatement(selectSQL);
    		preparedStatement.setString(1, identifiantRole);
    		rs = preparedStatement.executeQuery();
    		found = request(rs, found);
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    	
        return found;
    }

    @Override
    public Role createRole(Role role) {
		int isExecuted = 0;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
    	
	    try {
	        String createSQL = "INSERT INTO role "
	   			 + "(identifiant, description) "
	   			 + "VALUES (?, ?);";
	       	
	        preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, role.getIdentifiant());
	        preparedStatement.setString(2, role.getDescription());
	        isExecuted = preparedStatement.executeUpdate();
	        
	        rs = preparedStatement.getGeneratedKeys();
	        if(rs.next()) {
	        	int id = rs.getInt(1);
	        	role.setIdRole(id);
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
	        return role;
	    }
    }

    @Override
    public Role updateRole(Role role) {
    	int isExecuted = 0;
    	PreparedStatement preparedStatement = null;
    	
	    try {
	        String updateSQL = "UPDATE role "
	   			 + "SET identifiant = ?, description = ?, version = ? "
	   			 + "WHERE role.idRole = ?;";
	       	
	        preparedStatement = connection.prepareStatement(updateSQL);
	        preparedStatement.setString(1, role.getIdentifiant());
	        preparedStatement.setString(2, role.getDescription());
	        preparedStatement.setInt(3, role.getVersion() + 1);
	        preparedStatement.setInt(4, role.getIdRole());
	        
	        isExecuted = preparedStatement.executeUpdate();
	        	
	    } catch(SQLException e) {
	    	 e.printStackTrace();
	    }
	      
	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return role;
	    }
    }

    @Override
    public boolean deleteRole(Role role) {
    	int isExecuted = 0;
        PreparedStatement preparedStatement = null;
        
	    try {
	        String deleteSQL = "DELETE FROM role "
	   			 + "WHERE role.idRole = ?;";
	       	
	        preparedStatement = connection.prepareStatement(deleteSQL);
	        preparedStatement.setInt(1, role.getIdRole());
	        
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
}
