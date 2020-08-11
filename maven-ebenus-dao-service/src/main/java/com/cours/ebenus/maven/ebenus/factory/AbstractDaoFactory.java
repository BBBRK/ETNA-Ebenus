package com.cours.ebenus.maven.ebenus.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.idao.IChannelDao;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;

/**
 *
 * @author ElHadji
 */
public abstract class AbstractDaoFactory {

    public static String className = AbstractDaoFactory.class.getName();
    private static final Log log = LogFactory.getLog(AbstractDaoFactory.class);

    public enum FactoryDaoType {

        JDBC_DAO_FACTORY;
    }


    public abstract IRoleDao getRoleDao();
    public abstract IUserDao getUserDao();
    public abstract IMessageDao getMessageDao();
    public abstract IChannelDao getChannelDao();

    /**
     * Méthode pour récupérer une factory de DAO
     *
     * @param daoType
     * @return AbstractDaoFactory
     */
    public static AbstractDaoFactory getFactory(FactoryDaoType daoType) {
        AbstractDaoFactory factory = null;

        if(daoType == FactoryDaoType.JDBC_DAO_FACTORY) {
            factory = new DaoFactory();
        }

        return factory;
    }
}
