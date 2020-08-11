package com.cours.ebenus.maven.ebenus.factory;

import com.cours.ebenus.maven.ebenus.dao.impl.ChannelDao;
import com.cours.ebenus.maven.ebenus.dao.impl.MessageDao;
import com.cours.ebenus.maven.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.maven.ebenus.dao.impl.UserDao;
import com.cours.ebenus.maven.ebenus.idao.IChannelDao;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;

public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDao();
    }

	@Override
	public IUserDao getUserDao() {
		return new UserDao();
	}

	@Override
	public IMessageDao getMessageDao() {
		return new MessageDao();
	}

	@Override
	public IChannelDao getChannelDao() {
		return new ChannelDao();
	}
}
