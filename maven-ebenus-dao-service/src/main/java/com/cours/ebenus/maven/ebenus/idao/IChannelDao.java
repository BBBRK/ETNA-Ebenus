package com.cours.ebenus.maven.ebenus.idao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Channel;

public interface IChannelDao {
	public List<Channel> findAllChannels();
	
	public Channel findChannelById(int idChannel);
	
	public List<Channel> findChannelByName(String name);
	
	public List<Channel> findChannelByCreator(int idCreator);
	
    public Channel createChannel(Channel channel);

    public Channel updateChannel(Channel channel);

    public boolean deleteChannel(Channel channel);
}
