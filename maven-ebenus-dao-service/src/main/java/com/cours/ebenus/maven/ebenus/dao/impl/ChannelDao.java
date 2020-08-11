package com.cours.ebenus.maven.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Channel;
import com.cours.ebenus.maven.ebenus.idao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.idao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.idao.IChannelDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChannelDao implements IChannelDao {
	
    private static final Log log = LogFactory.getLog(RoleDao.class);
    private static final Connection connection = DriverManagerSingleton.getConnectionInstance();
    private static ConnectionHelper lch;
    
    private List<Channel> request(ResultSet rs, List<Channel> channels) {
        try {
            while (rs.next()) {
            	Channel channel = new Channel();
            	channel.setIdChannel(rs.getInt("idChannel"));
            	channel.setName(rs.getString("channel.name"));
            	channel.setDescription(rs.getString("channel.description"));
            	channel.setCreator(rs.getInt("channel.creator"));
                channels.add(channel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return channels;
    }
    
    private Channel request(ResultSet rs, Channel channel) {
        try {
            while (rs.next()) {
            	channel.setIdChannel(rs.getInt("idChannel"));
            	channel.setName(rs.getString("channel.name"));
            	channel.setDescription(rs.getString("channel.description"));
            	channel.setCreator(rs.getInt("channel.creator"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return channel;
    }

	@Override
	public List<Channel> findAllChannels() {
		List<Channel> channels = new ArrayList<Channel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM channel;";
		
    	try {
    		ps = connection.prepareStatement(req);
    		rs = ps.executeQuery(req);
    		channels = request(rs, channels);
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
		return channels;
	}

	@Override
	public Channel findChannelById(int idChannel) {
		Channel found = new Channel();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM channel "
				+ "WHERE channel.idChannel = ?;";
		
    	try {
    		ps = connection.prepareStatement(req);
    		ps.setInt(1, idChannel);
    		rs = ps.executeQuery();
    		found = request(rs, found);
    		
            if(found.getIdChannel() == null) {
            	found = null;
            }
    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
    	
		return found;
	}

	@Override
	public List<Channel> findChannelByName(String name) {
		List<Channel> channels = new ArrayList<Channel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM channel "
				+ "WHERE channel.name = ?;";
		
		try {
    		ps = connection.prepareStatement(req);
    		ps.setString(1, name);
    		rs = ps.executeQuery();
    		channels = request(rs, channels);

    		
		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }

		return channels;
	}
	
	@Override
	public List<Channel> findChannelByCreator(int idCreator) {
		List<Channel> channels = new ArrayList<Channel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String req = "SELECT * FROM channel "
				+ "WHERE channel.creator = ?;";
		
    	try {
    		ps = connection.prepareStatement(req);
    		ps.setInt(1, idCreator);
    		rs = ps.executeQuery();
    		channels = request(rs, channels);

    		
    	} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            lch.closeSqlResources(ps, rs);
        }
    	
		return channels;
	}


	@Override
	public Channel createChannel(Channel channel) {
		int isExecuted = 0;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
	        String req = "INSERT INTO channel "
	   			 + "(name, description, creator) "
	   			 + "VALUES (?, ?, ?);";
	       	
	        ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, channel.getName());
	        ps.setString(2, channel.getDescription());
	        ps.setInt(3, channel.getCreator());
	        isExecuted = ps.executeUpdate();
	        
	        rs = ps.getGeneratedKeys();
	        if(rs.next()) {
	        	int id = rs.getInt(1);
	        	channel.setIdChannel(id);
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
	        return channel;
	    }
	}

	@Override
	public Channel updateChannel(Channel channel) {
		int isExecuted = 0;
		PreparedStatement ps = null;
		
		 try {
		        String req = "UPDATE channel "
		   			 + "SET name = ?, description = ?, creator = ? "
		   			 + "WHERE channel.idChannel = ?;";
		       	
		        ps = connection.prepareStatement(req);
		        ps.setString(1, channel.getName());
		        ps.setString(2, channel.getDescription());
		        ps.setInt(3, channel.getCreator());
		        ps.setInt(4, channel.getIdChannel());

		        isExecuted = ps.executeUpdate();
		        	
		    } catch(SQLException e) {
		    	 e.printStackTrace();
		    }


	    if(isExecuted == 0) {
	        return null;
	    }
	    else {
	        return channel;
	    }
	}

	@Override
	public boolean deleteChannel(Channel channel) {
	int isExecuted = 0;
    	
    	try {
        	String req = "DELETE FROM channel "
        		+ "WHERE idChannel = ?;";
        	
        	PreparedStatement preparedStatement;
        	
        	preparedStatement = connection.prepareStatement(req);
        	preparedStatement.setInt(1, channel.getIdChannel());
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
