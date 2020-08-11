package com.cours.ebenus.maven.ebenus.service;

import com.cours.ebenus.maven.ebenus.idao.IChannelDao;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;

public interface IServiceFacade {

    public IUserDao getUserDao();

    public IRoleDao getRoleDao();
    
    public IMessageDao getMessageDao();
    
    public IChannelDao getChannelDao();
    
}
