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

import com.cours.ebenus.maven.ebenus.dao.entities.Channel;
import com.cours.ebenus.maven.ebenus.dao.entities.Message;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.idao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.idao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;

public class MessageDao implements IMessageDao {
    private static final Log log = LogFactory.getLog(RoleDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;
    
    private List<Message> request(ResultSet rs, List<Message> messages) {
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
                
                Channel channel = new Channel();
                channel.setIdChannel(rs.getInt("idChannel"));
            	channel.setName(rs.getString("channel.name"));
            	channel.setDescription(rs.getString("channel.description"));
            	channel.setCreator(rs.getInt("channel.creator"));
            	
            	Message message = new Message();
            	message.setIdMessage(rs.getInt("message.idMessage"));
            	message.setContentMessage(rs.getString("message.content"));
            	message.setDateMessage(rs.getTimestamp("message.date"));
            	message.setUserMessage(user);
            	message.setChannelMessage(channel);
            	messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
    
    private Message request(ResultSet rs, Message message) {
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
                
                Channel channel = new Channel();
                channel.setIdChannel(rs.getInt("idChannel"));
            	channel.setName(rs.getString("channel.name"));
            	channel.setDescription(rs.getString("channel.description"));
            	channel.setCreator(rs.getInt("channel.creator"));
 
            	message.setIdMessage(rs.getInt("message.idMessage"));
            	message.setContentMessage(rs.getString("message.content"));
            	message.setDateMessage(rs.getTimestamp("message.date"));
            	message.setUserMessage(user);
            	message.setChannelMessage(channel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }


	@Override
	public List<Message> findAllMessages() {
		List<Message> messages = new ArrayList<Message>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	String req = "SELECT * FROM message "
    			+ "INNER JOIN channel "
    			+ "ON message.idChannel = channel.idChannel "
    			+ "INNER JOIN user "
    			+ " ON message.idUser = user.idUser "
    			+ "INNER JOIN role "
    			+ "ON user.idRole = role.idRole;";
    	
    	try {
    		ps = connection.prepareStatement(req);
    		rs = ps.executeQuery(req);
    		
    		messages = request(rs, messages);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	
		return messages;
	}

	@Override
	public Message findMessageById(int idMessage) {
		Message found = new Message();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try {
			
			String req = "SELECT * FROM message "
	    			+ "INNER JOIN channel "
	    			+ "ON message.idChannel = channel.idChannel "
	    			+ "INNER JOIN user "
	    			+ "ON message.idUser = user.idUser "
	    			+ "INNER JOIN role "
	    			+ "ON user.idRole = role.idRole "
	    			+ "WHERE message.idMessage = ?;";

	    	ps = connection.prepareStatement(req);
	    	ps.setInt(1, idMessage);
    		rs = ps.executeQuery();

    		found = request(rs, found);
    		
            if(found.getIdMessage() == null) {
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
	public List<Message> findMessageByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findMessageByIdUser(int idUser) {
		List<Message> messages = new ArrayList<Message>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		String req = "SELECT * FROM message "
    			+ "INNER JOIN channel "
    			+ "ON message.idChannel = channel.idChannel "
    			+ "INNER JOIN user "
    			+ "ON message.idUser = user.idUser "
    			+ "INNER JOIN role "
    			+ "ON user.idRole = role.idRole "
    			+ "WHERE message.idUser = ?;";
    			
    	
    	try {
    		ps = connection.prepareStatement(req);
    		ps.setInt(1, idUser);
    		rs = ps.executeQuery();
    		
    		messages = request(rs, messages);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	
		return messages;
	}

	@Override
	public List<Message> findMessageByIdChannel(int idChannel) {
		List<Message> messages = new ArrayList<Message>();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		String req = "SELECT * FROM message "
    			+ "INNER JOIN channel "
    			+ "ON message.idChannel = channel.idChannel "
    			+ "INNER JOIN user "
    			+ "ON message.idUser = user.idUser "
    			+ "INNER JOIN role "
    			+ "ON user.idRole = role.idRole "
    			+ "WHERE message.idChannel = ? "
				+ "ORDER BY idMessage ASC;";
    	
    	try {
    		ps = connection.prepareStatement(req);
    		ps.setInt(1, idChannel);
    		rs = ps.executeQuery();
    		
    		messages = request(rs, messages);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        }
    	
		return messages;
	}

	@Override
	public Message createMessage(Message message) {
		int isExecuted = 0;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	 Date date = new Date();
         Timestamp dateCreation = new Timestamp(date.getTime());
         
         try {
 	        String req = "INSERT INTO message "
 	   			 + "(content, date, idUser, idChannel) "
 	   			 + "VALUES (?, ?, ?, ?);";
 	       	
 	        ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
 	        ps.setString(1, message.getContentMessage());
 	        ps.setTimestamp(2, dateCreation);
 	        ps.setInt(3, message.getUserMessage().getIdUser());
 	        ps.setInt(4, message.getChannelMessage().getIdChannel());
 	        isExecuted = ps.executeUpdate();
 	        
 	        rs = ps.getGeneratedKeys();
 	        if(rs.next()) {
 	        	int id = rs.getInt(1);
 	        	message.setIdMessage(id);
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
	        return message;
	    }
	}

	@Override
	public Message updateMessage(Message message) {
		int isExecuted = 0;
    	PreparedStatement ps = null;  	

	    try {
	        String req = "UPDATE message "
	   			 + "SET content = ? "
	   			 + "WHERE message.idMessage = ?;";
	       	
	        ps = connection.prepareStatement(req);
	        
	        ps.setString(1, message.getContentMessage());
	        ps.setInt(2, message.getIdMessage());
	       	    
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
	        return message;
	    }
	}

	@Override
	public boolean deleteMessage(Message message) {
		int isExecuted = 0;
    	
    	try {
        	String req = "DELETE FROM message "
        		+ "WHERE idMessage = ?;";
        	
        	PreparedStatement preparedStatement;
        	
        	preparedStatement = connection.prepareStatement(req);
        	preparedStatement.setInt(1, message.getIdMessage());
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
