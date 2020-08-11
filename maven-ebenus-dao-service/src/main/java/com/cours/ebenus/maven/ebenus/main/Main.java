package com.cours.ebenus.maven.ebenus.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.maven.ebenus.dao.entities.Channel;
import com.cours.ebenus.maven.ebenus.dao.entities.Message;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.dao.impl.ChannelDao;
import com.cours.ebenus.maven.ebenus.dao.impl.MessageDao;
import com.cours.ebenus.maven.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.maven.ebenus.dao.impl.UserDao;
import com.cours.ebenus.maven.ebenus.idao.IChannelDao;
import com.cours.ebenus.maven.ebenus.idao.IMessageDao;
import com.cours.ebenus.maven.ebenus.idao.IRoleDao;
import com.cours.ebenus.maven.ebenus.idao.IUserDao;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;

public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	
    	IServiceFacade serviceFacade = new ServiceFacade();
    	
    	IRoleDao rDao = new RoleDao();
    	IUserDao uDao = new UserDao();
    	IChannelDao cDao = new ChannelDao();
    	IMessageDao mDao = new MessageDao();
    	
    	List<User> users = new ArrayList<User>();
    	List<Role> roles = new ArrayList<Role>();
    	List<Channel> channels = new ArrayList<Channel>();
    	List<Message> messages = new ArrayList<Message>();
    	
    	User u = uDao.authenticate("admin@gmail.com", "azeaze");
    	
    	

    	System.out.println(u.getRole());
    	
    	
    	//Role r = rDao.findRoleById(1);
    	//User u = uDao.findUserById(3);
    	
    	//roles.add(rDao.deleteRole(r));
    	
    	//users.addAll(uDao.findAllUsers());
    	
    	//System.out.println(uDao.authenticate("admin@gmail.com", "azeaze"));

    
    }
}
