package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.dao.exception.EbenusException;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.idao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.idao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;

public class UserDao implements IUserDao {
    private static final Log log = LogFactory.getLog(RoleDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;
    
    private List<User> request(ResultSet rs, List<User> users) {
        try {
            while (rs.next()) {
            	Role role = new Role();
            	role.setIdRole(rs.getInt("idRole"));
            	role.setName(rs.getString("role.name"));
            	
                User user = new User();
                user.setIdUser(rs.getInt("user.idUser"));
                user.setRole(role);
                user.setIdentifiant(rs.getString("user.email"));
                user.setPassword(rs.getString("user.password"));
                user.setNickname(rs.getString("user.nickname"));
                user.setCivilite(rs.getString("user.civilite"));
                user.setCreationDate(rs.getTimestamp("user.createdAt"));
                user.setUpdatedDate(rs.getTimestamp("user.updatedAt"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    private User request(ResultSet rs, User user) {
        try {
            while (rs.next()) {
            	Role role = new Role();
            	role.setIdRole(rs.getInt("idRole"));
            	role.setName(rs.getString("name"));
            	
                user.setIdUser(rs.getInt("user.idUser"));
                user.setRole(role);
                user.setIdentifiant(rs.getString("user.email"));
                user.setPassword(rs.getString("user.password"));
                user.setNickname(rs.getString("user.nickname"));
                user.setCivilite(rs.getString("user.civilite"));
                user.setCreationDate(rs.getTimestamp("user.createdAt"));
                user.setUpdatedDate(rs.getTimestamp("user.updatedAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

	@Override
	public List<User> findAllUsers() {
    	List<User> users = new ArrayList<User>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	String req = "SELECT * FROM user "
    			+ "INNER JOIN role "
    			+ "ON user.idRole = role.idRole;";
    	
    	try {
    		ps = connection.prepareStatement(req);
    		rs = ps.executeQuery(req);
    		
    		users = request(rs, users);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	
		return users;
	}

	@Override
	public User findUserById(int idUser) {
    	User found = new User();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
	    	String req = "SELECT * FROM user "
					 + "INNER JOIN role "
					 + "ON user.idRole = role.idRole "
					 + "WHERE user.idUser = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setInt(1, idUser);
    		rs = ps.executeQuery();

    		found = request(rs, found);
    		
            if(found.getIdentifiant() == null) {
            	found = null;
            }
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(ps, rs);
        }
		return found;
	}

	@Override
	public List<User> findUserByIdentifiant(String identifiant) {
    	List<User> users = new ArrayList<User>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
	    	String req = "SELECT * FROM user "
					 + "INNER JOIN role "
					 + "ON user.idRole = role.idRole "
					 + "WHERE user.email = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setString(1, identifiant);
    		rs = ps.executeQuery();

    		users = request(rs, users);
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(ps, rs);
        }
		return users;
	}

	@Override
	public List<User> findUserByNickname(String nickname) {
    	List<User> users = new ArrayList<User>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
	    	String req = "SELECT * FROM user "
					 + "INNER JOIN role "
					 + "ON user.idRole = role.idRole "
					 + "WHERE user.nickname = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setString(1, nickname);
    		rs = ps.executeQuery();

    		users = request(rs, users);
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(ps, rs);
        }
		return users;
	}

	@Override
	public List<User> findUserByIdRole(int idRole) {
    	List<User> users = new ArrayList<User>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
	    	String req = "SELECT * FROM user "
					 + "INNER JOIN role "
					 + "ON user.idRole = role.idRole "
					 + "WHERE user.idRole = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setInt(1, idRole);
    		rs = ps.executeQuery();

    		users = request(rs, users);
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(ps, rs);
        }
		return users;
	}

	@Override
	public List<User> findUserByIdentifiantRole(String identifiantRole) {
    	List<User> users = new ArrayList<User>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
	    	String req = "SELECT * FROM user "
					 + "INNER JOIN role "
					 + "ON user.idRole = role.idRole "
					 + "WHERE role.email = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setString(1, identifiantRole);
    		rs = ps.executeQuery();

    		users = request(rs, users);
    		
		}  catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	lch.closeSqlResources(ps, rs);
        }
		return users;
	}

	@Override
	public User createUtilisateur(User user) {
		int isExecuted = 0;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	 Date date = new Date();
         Timestamp dateCreation = new Timestamp(date.getTime());
         Timestamp dateModification = dateCreation;
         
         List<User> emailUsed = findUserByIdentifiant(user.getIdentifiant());
         if (emailUsed.size() != 0) {
             throw new EbenusException("L'identifiant est déjà utilisé", -1);
         } else {
 	        try {
 	        	String req = "INSERT INTO user "
 	   				 + "(email, password, nickname, civilite, createdAt, updatedAt, idRole) "
 	   				 + "VALUES (?, ?, ?, ?, ?, ?, ?);";
 	       	
 	        	ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
 	        	ps.setString(1, user.getIdentifiant());
 	        	ps.setString(2, user.getPassword());
 	        	ps.setString(3, user.getNickname());
 	        	ps.setString(4, user.getCivilite());
 	        	ps.setTimestamp(5, dateCreation);
 	        	ps.setTimestamp(6, dateModification);
 	        	ps.setInt(7, user.getRole().getIdRole());
 	       	    
 	       	    isExecuted = ps.executeUpdate();
 	       	    
 		        rs = ps.getGeneratedKeys();
 		        if(rs.next()) {
 		        	int id = rs.getInt(1);
 		        	user.setIdUser(id);
 		        	user.setCreationDate(dateCreation);
 		        	user.setUpdatedDate(dateModification);
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
 	            return user;
 	        }
        }
	}

	@Override
	public User updateUtilisateur(User user) throws EbenusException {
		int isExecuted = 0;
    	PreparedStatement ps = null;
    	
        Date date = new Date();
        Timestamp dateModification = new Timestamp(date.getTime());
    	

	    try {
	        String req = "UPDATE user "
	   			 + "SET email = ?, password = ?, nickname = ?, civilite = ?, updatedAt = ?, idRole = ? "
	   			 + "WHERE user.idUser= ?;";
	       	
	        ps = connection.prepareStatement(req);
	        
	        ps.setString(1, user.getIdentifiant());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getNickname());
	        ps.setString(4, user.getCivilite());
	        ps.setTimestamp(5, dateModification);
	        ps.setInt(6, user.getRole().getIdRole());
	        ps.setInt(7, user.getIdUser());
	       	    
	       	user.setUpdatedDate(dateModification);
	       	    
	       	isExecuted = ps.executeUpdate();

	        	
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
            lch.closeSqlResources(ps, null);
        }
	    
	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return user;
	    }
	}

	@Override
	public User updateUserWithoutPassword(User user) {
		int isExecuted = 0;
    	PreparedStatement ps = null;
    	
        Date date = new Date();
        Timestamp dateModification = new Timestamp(date.getTime());
    	

	    try {
	        String req = "UPDATE user "
	   			 + "SET email = ?, nickname = ?, civilite = ?, updatedAt = ?, idRole = ? "
	   			 + "WHERE user.idUser= ?;";
	       	
	        ps = connection.prepareStatement(req);
	        
	        ps.setString(1, user.getIdentifiant());
	        ps.setString(2, user.getNickname());
	        ps.setString(3, user.getCivilite());
	        ps.setTimestamp(4, dateModification);
	        ps.setInt(5, user.getRole().getIdRole());
	        ps.setInt(6, user.getIdUser());
	       	    
	       	user.setUpdatedDate(dateModification);
	       	    
	       	isExecuted = ps.executeUpdate();

	        	
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
            lch.closeSqlResources(ps, null);
        }
	    
	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return user;
	    }
	}

	@Override
	public boolean deleteUtilisateur(User user) {
	int isExecuted = 0;
    	
    	try {
        	String req = "DELETE FROM user "
        		+ "WHERE idUser = ?;";
        	
        	PreparedStatement preparedStatement;
        	
        	preparedStatement = connection.prepareStatement(req);
        	preparedStatement.setInt(1, user.getIdUser());
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

	@Override
	public User authenticate(String email, String password) {
    	User user = new User();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	String req = "SELECT * FROM user, "
    			+ "role WHERE user.idRole "
    			+ "AND user.email = ? "
    			+ "AND user.password = ?;";

    	
    	try {
    		ps = connection.prepareStatement(req);
    		ps.setString(1, email);
    		ps.setString(2, password);
    		rs = ps.executeQuery();
    		
    		user = request(rs, user);
    		rs.close();
    		ps.close();
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
    	
    	return user;
	}

}
