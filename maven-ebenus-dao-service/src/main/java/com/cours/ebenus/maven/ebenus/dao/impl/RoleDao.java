package com.cours.ebenus.maven.ebenus.dao.impl;
import com.cours.ebenus.maven.ebenus.idao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.idao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RoleDao implements IRoleDao {
    private static final Log log = LogFactory.getLog(RoleDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;
    
    private List<Role> request(ResultSet rs, List<Role> roles) {
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getInt("idRole"));
                role.setName(rs.getString("role.name"));

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
                role.setName(rs.getString("role.name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
    

	@Override
	public List<Role> findAllRoles() {
		List<Role> roleList = new ArrayList<Role>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM role;";
		
    	try {
    		ps = connection.prepareStatement(req);
    		rs = ps.executeQuery(req);
    		roleList = request(rs, roleList);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
        return roleList;
	}

	@Override
	public Role findRoleById(int idRole) {
		Role found = new Role();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM role "
				+ "WHERE role.idRole = ?;";
		
		try {
    		ps = connection.prepareStatement(req);
    		ps.setInt(1, idRole);
    		rs = ps.executeQuery();
    		found = request(rs, found);
    		
            if(found.getIdRole() == null) {
            	found = null;
            }
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
    	
        return found;
	}

	@Override
	public List<Role> findByIdentifiant(String identifiantRole) {
		List<Role> roleList = new ArrayList<Role>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM role "
				+ "WHERE role.name = ?;";
		
		try {
    		ps = connection.prepareStatement(req);
    		ps.setString(1, identifiantRole);
    		rs = ps.executeQuery();
    		roleList = request(rs, roleList);

    		
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
	
		return roleList;
	}

	@Override
	public Role createRole(Role role) {
		int isExecuted = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
	        String req = "INSERT INTO role "
	   			 + "(name) "
	   			 + "VALUES (?);";
	       	
	        ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, role.getName());
	        isExecuted = ps.executeUpdate();
	        
	        rs = ps.getGeneratedKeys();
	        if(rs.next()) {
	        	int id = rs.getInt(1);
	        	role.setIdRole(id);
	        }
	        	
	    } catch(SQLException e) {
	    	 e.printStackTrace();
	    }finally {
            lch.closeSqlResources(ps, rs);
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
		PreparedStatement ps = null;
		
		 try {
		        String req = "UPDATE role "
		   			 + "SET name = ? "
		   			 + "WHERE role.idRole = ?;";
		       	
		        ps = connection.prepareStatement(req);
		        ps.setString(1, role.getName());
		        ps.setInt(2, role.getIdRole());

		        isExecuted = ps.executeUpdate();
		        	
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
		PreparedStatement ps = null;
		
		try {
	        String req = "DELETE FROM role "
	   			 + "WHERE role.idRole = ?;";
	       	
	        ps = connection.prepareStatement(req);
	        ps.setInt(1, role.getIdRole());
	        
	        isExecuted = ps.executeUpdate();
	        
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
