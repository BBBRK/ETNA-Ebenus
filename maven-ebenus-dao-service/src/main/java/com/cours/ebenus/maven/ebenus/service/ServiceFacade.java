package com.cours.ebenus.maven.ebenus.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.idao.IUserDao;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.maven.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import com.cours.ebenus.maven.ebenus.idao.IChannelDao;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

public class ServiceFacade implements IServiceFacade {
	
    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Role ect ....)
    private IUserDao userDao = null;
    private IRoleDao roleDao = null;
    private IMessageDao messageDao = null;
    private IChannelDao channelDao = null;

    public ServiceFacade() {
        // mettre tous les DAO
        userDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
        messageDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getMessageDao();
        channelDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getChannelDao();
    }

    public ServiceFacade(FactoryDaoType daoType) {
        // mettre tous les DAO
    	userDao = AbstractDaoFactory.getFactory(daoType).getUserDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
        messageDao = AbstractDaoFactory.getFactory(daoType).getMessageDao();
        channelDao = AbstractDaoFactory.getFactory(daoType).getChannelDao();
    }

	@Override
	public IUserDao getUserDao() {
		return userDao;
	}

	@Override
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Override
	public IMessageDao getMessageDao() {
		return messageDao;
	}

	@Override
	public IChannelDao getChannelDao() {
		return channelDao;
	}

}
