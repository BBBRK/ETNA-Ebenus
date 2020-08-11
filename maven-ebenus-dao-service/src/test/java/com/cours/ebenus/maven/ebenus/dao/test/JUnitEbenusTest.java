package com.cours.ebenus.maven.ebenus.dao.test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.maven.ebenus.idao.ConnectionHelper;
import com.cours.ebenus.maven.ebenus.idao.DriverManagerSingleton;
import com.cours.ebenus.maven.ebenus.dao.exception.EbenusException;
import com.cours.ebenus.maven.ebenus.dao.entities.Channel;
import com.cours.ebenus.maven.ebenus.dao.entities.Message;
import com.cours.ebenus.maven.ebenus.dao.entities.Role;
import com.cours.ebenus.maven.ebenus.dao.entities.User;
import com.cours.ebenus.maven.ebenus.service.ServiceFacade;
import com.cours.ebenus.maven.ebenus.utils.Constants;
import com.cours.ebenus.maven.ebenus.service.IServiceFacade;

public class JUnitEbenusTest {
	
    private static final Log log = LogFactory.getLog(JUnitEbenusTest.class);
    private static IServiceFacade serviceFacade = null;
    
    /* users */
    private static final int NB_USERS_LIST = 3;
    
    private static final int NB_USERS_FIND_BY_IDENTIFIANT = 1;
    private static final String USERS_FIND_BY_IDENTIFIANT = "admin@gmail.com";
    
    private static final int NB_USERS_FIND_BY_NICKNAME = 1;
    private static final String USERS_FIND_BY_NICKNAME = "Alfred";
    
    private static final int NB_USERS_FIND_BY_ID_ROLE_ADMINISTRATEUR = 1;
    private static final int USERS_FIND_BY_ID_ROLE_ADMININISTRATEUR = 1;
    
    private static final int NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR = 2;
    private static final int USERS_FIND_BY_ID_ROLE_UTILISATEUR = 2;
    
    /* roles */
    private static final int NB_ROLES_LIST = 2;
    
    private static final int NB_ROLES_FIND_BY_NAME = 1;
    private static final String ROLES_FIND_BY_NAME = "Administrateur";
    
    /* messages */
    private static final int NB_MESSAGES_LIST = 2;
    
    private static final int NB_MESSAGES_BY_ID_USER = 1;
    private static final int MESSAGES_BY_ID_USER = 1;
    
    private static final int NB_MESSAGES_BY_ID_CHANNEL = 2;
    private static final int MESSAGES_BY_ID_CHANNEL = 1;
    
    /* channels */
    private static final int NB_CHANNELS_LIST = 2;
    
    private static final int NB_CHANNELS_FIND_BY_NAME = 1;
    private static final String CHANNELS_FIND_BY_NAME = "Main";
    
	
    private static List<User> users = null;
    private static List<Role> roles = null;
    private static List<Channel> channels = null;
    private static List<Message> messages = null;

    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
        serviceFacade = new ServiceFacade();
        roles = serviceFacade.getRoleDao().findAllRoles();
        users = serviceFacade.getUserDao().findAllUsers();
        channels = serviceFacade.getChannelDao().findAllChannels();
        messages = serviceFacade.getMessageDao().findAllMessages();

    }
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;

        Connection connection = DriverManagerSingleton.getConnectionInstance();
        ConnectionHelper lch = new ConnectionHelper();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try (FileReader fr = new FileReader(scriptSqlPath); BufferedReader br = new BufferedReader(fr);) {

            StringBuilder lsbContenu = new StringBuilder();
            String lsLigne;

            while (br.ready()) {
                lsLigne = br.readLine();
                char lettre = lsLigne.charAt(lsLigne.length()-1);

                if (!lsLigne.isEmpty()) {
                    if(lettre == ';') {
                        lsbContenu.append(lsLigne);
                        lsbContenu.append("\n");
                        preparedStatement = connection.prepareStatement(lsbContenu.toString());
                        preparedStatement.executeUpdate();
                    } else {
                        lsbContenu.append(lsLigne);
                        lsbContenu.append("\n");
                    }
                } else {
                	preparedStatement = connection.prepareStatement(lsbContenu.toString());
                	preparedStatement.executeUpdate();
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        } finally {
            lch.closeSqlResources(preparedStatement, rs);
        }
    }
    
    public void verifyRoleData(Role role) {
        log.debug("Entree de la methode");
        if (role != null) {
            log.debug("idRole : " + role.getIdRole());
            Assert.assertNotNull(role.getIdRole());
            Assert.assertNotNull(role.getName());
        } else if (role == null) {
            Assert.fail("Role null");
        }
        log.debug("Sortie de la methode");
    }
    
    public void verifyUserDatas(User user) {
        log.debug("Entree de la methode");
        if (user != null) {
            log.debug("idUtilisateur : " + user.getIdUser());
            Assert.assertNotNull(user.getIdentifiant());
            Assert.assertNotNull(user.getPassword());
            Assert.assertNotNull(user.getNickname());
            Assert.assertNotNull(user.getRole());
            Assert.assertNotNull(user.getRole().getIdRole());
            Assert.assertNotNull(user.getRole().getName());
        } else if (user == null) {
            Assert.fail("Utilisateur null");
        }
        log.debug("Sortie de la methode");
    }
    
    public void verifyUsersDatas(List<User> users) {
        log.debug("Entree de la methode");
        if (users != null) {
            log.debug("utilisateurs.size(): " + users.size());
            for (User user : users) {
                verifyUserDatas(user);
            }
        } else if (users == null || users.isEmpty()) {
            Assert.fail("Aucun user n'a ete trouves dans votre liste");
        }
        log.debug("Sortie de la methode");
    }
    
/* ****************************  TESTS USERS  ********************************************* */
    @Test
    public void testFindAllUsers() {
        log.debug("Entree de la methode");
        log.debug("nombre de user : " + users);
        if (users != null) {
            log.debug("NB_USERS_LIST: " + NB_USERS_LIST + " , users.size(): " + users.size());
            Assert.assertEquals(NB_USERS_LIST, users.size());
            verifyUsersDatas(users);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindUserByIdentifiant() {
    	List<User> userByIdentifiant = serviceFacade.getUserDao().findUserByIdentifiant(USERS_FIND_BY_IDENTIFIANT);
        log.debug("Entree de la methode");
        log.debug("nombre de user : " + userByIdentifiant.size());
        if (users != null) {
            log.debug("NB_USERS_FIND_BY_IDENTIFIANT: " + NB_USERS_FIND_BY_IDENTIFIANT + " , userByIdentifiant.size(): " + userByIdentifiant.size());
            Assert.assertEquals(NB_USERS_FIND_BY_IDENTIFIANT, userByIdentifiant.size());
            verifyUsersDatas(userByIdentifiant);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindUserByNickname() {
    	List<User> userByNickname = serviceFacade.getUserDao().findUserByNickname(USERS_FIND_BY_NICKNAME);
        log.debug("Entree de la methode");
        log.debug("nombre de user : " + userByNickname.size());
        if (users != null) {
            log.debug("NB_USERS_FIND_BY_NICKNAME: " + NB_USERS_FIND_BY_NICKNAME + " , userByNickname.size(): " + userByNickname.size());
            Assert.assertEquals(NB_USERS_FIND_BY_NICKNAME, userByNickname.size());
            verifyUsersDatas(userByNickname);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindUserByIdRoleAdministrateur() {
    	List<User> userByRoleId = serviceFacade.getUserDao().findUserByIdRole(USERS_FIND_BY_ID_ROLE_ADMININISTRATEUR);
        log.debug("Entree de la methode");
        log.debug("nombre de user : " + userByRoleId.size());
        if (users != null) {
            log.debug("NB_USERS_FIND_BY_ID_ROLE_ADMINISTRATEUR: " + NB_USERS_FIND_BY_ID_ROLE_ADMINISTRATEUR + " , userByRoleId.size(): " + userByRoleId.size());
            Assert.assertEquals(NB_USERS_FIND_BY_ID_ROLE_ADMINISTRATEUR, userByRoleId.size());
            verifyUsersDatas(userByRoleId);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindUserByIdRoleUtilisateur() {
    	List<User> userByRoleIdUtilisateur = serviceFacade.getUserDao().findUserByIdRole(USERS_FIND_BY_ID_ROLE_UTILISATEUR);
        log.debug("Entree de la methode");
        log.debug("nombre de user : " + userByRoleIdUtilisateur.size());
        if (users != null) {
            log.debug("NB_USERS_FIND_BY_ID_ROLE_ADMINISTRATEUR: " + NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR + " , userByRoleIdUtilisateur.size(): " + userByRoleIdUtilisateur.size());
            Assert.assertEquals(NB_USERS_FIND_BY_ID_ROLE_UTILISATEUR, userByRoleIdUtilisateur.size());
            verifyUsersDatas(userByRoleIdUtilisateur);
        } else {
            Assert.fail("Aucun utilisateur n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteUser() {
    	log.debug("Entree de la methode");
    	
    	/* test create */ 
    	log.debug("**** CREATE ****");
    	Role r = serviceFacade.getRoleDao().findRoleById(2);
    	User userCRUD = new User(4,"Mrs", "user3@gmail.com", "test", "User3", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), r);
    	userCRUD = serviceFacade.getUserDao().createUtilisateur(userCRUD);
    	log.debug("created userCRUD : " + userCRUD);
    	userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getIdUser());
    	Assert.assertNotNull(userCRUD);
    	
    	/* test update */
    	log.debug("**** UPDATE ****");
    	userCRUD.setNickname("Test unitaire");
    	userCRUD = serviceFacade.getUserDao().updateUtilisateur(userCRUD);
    	Assert.assertNotNull(userCRUD);
    	userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getIdUser());
    	log.debug("Updated userCRUD : " + userCRUD);
    	Assert.assertEquals("Test unitaire", userCRUD.getNickname());
    	
    	/* test delete */
    	log.debug("**** DELETE ****");
    	Assert.assertTrue(serviceFacade.getUserDao().deleteUtilisateur(userCRUD));
    	userCRUD = serviceFacade.getUserDao().findUserById(userCRUD.getIdUser());
    	log.debug("deleted userCRUD : " + userCRUD);
    	Assert.assertNull(userCRUD);
    	
    	 // Cas des gestions des doublons d'identifiant (mail).
        userCRUD = new User(4, "Mrs", "admin@gmail.com", "test", "User3", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), r);
        try {
            userCRUD = serviceFacade.getUserDao().createUtilisateur(userCRUD);
            log.debug("Duplicate userCRUD : " + userCRUD.getIdentifiant());
        } catch (EbenusException e) {
            log.debug("Bravo la gestion des doublons d'identifiant marche parfaitement");
            Assert.assertEquals(Constants.EXCEPTION_CODE_USER_ALREADY_EXIST, e.getCode());
        }
        List<User> usersFinal = serviceFacade.getUserDao().findAllUsers();
        if (usersFinal != null) {
            Assert.assertEquals(NB_USERS_LIST, usersFinal.size());
            log.debug("usersFinal.size() : " + usersFinal.size() + " , NB_USERS_LIST: " + NB_USERS_LIST);
        }
    	log.debug("Sortie de la methode");
    }
    
    /* ****************************  TESTS ROLES  ********************************************* */
    
    @Test
    public void testFindAllRoles() {
        log.debug("Entree de la methode");
        log.debug("nombre de roles : " + roles);
        if (roles != null) {
            log.debug("NB_ROLES_LIST: " + NB_ROLES_LIST + " , roles.size(): " + roles.size());
            Assert.assertEquals(NB_ROLES_LIST, roles.size());
        } else {
            Assert.fail("Aucun Role n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindRoleByName() {
    	List<Role> RoleByName = serviceFacade.getRoleDao().findByIdentifiant(ROLES_FIND_BY_NAME);
        log.debug("Entree de la methode");
        log.debug("nombre de roles : " + RoleByName.size());
        if (users != null) {
            log.debug("NB_ROLES_FIND_BY_NAME: " + NB_ROLES_FIND_BY_NAME + " , RoleByName.size(): " + RoleByName.size());
            Assert.assertEquals(NB_ROLES_FIND_BY_NAME, RoleByName.size());
        } else {
            Assert.fail("Aucun Role n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteRole() {
    	log.debug("Entree de la methode");
    	
    	/* test create */ 
    	log.debug("**** CREATE ****");
    	Role roleCRUD = new Role(3, "Super user");
    	roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
    	log.debug("created roleCRUD : " + roleCRUD);
    	roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
    	Assert.assertNotNull(roleCRUD);
    	
    	/* test update */
    	log.debug("**** UPDATE ****");
    	roleCRUD.setName("Super test");
    	roleCRUD = serviceFacade.getRoleDao().updateRole(roleCRUD);
    	Assert.assertNotNull(roleCRUD);
    	roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
    	log.debug("Updated userCRUD : " + roleCRUD);
    	Assert.assertEquals("Super test", roleCRUD.getName());
    	
    	/* test delete */
    	log.debug("**** DELETE ****");
    	Assert.assertTrue(serviceFacade.getRoleDao().deleteRole(roleCRUD));
    	roleCRUD = serviceFacade.getRoleDao().findRoleById(roleCRUD.getIdRole());
    	log.debug("deleted roleCRUD : " + roleCRUD);
    	Assert.assertNull(roleCRUD);
    	
        List<Role> rolesFinal = serviceFacade.getRoleDao().findAllRoles();
        if (rolesFinal != null) {
            Assert.assertEquals(NB_ROLES_LIST, rolesFinal.size());
            log.debug("rolesFinal.size() : " + rolesFinal.size() + " , NB_ROLES_LIST: " + NB_ROLES_LIST);
        }
    	log.debug("Sortie de la methode");
    }
    
    /* ****************************  TESTS CHANNELS  ********************************************* */
    
    @Test
    public void testFindAllChannels() {
        log.debug("Entree de la methode");
        log.debug("nombre de channels : " + channels);
        if (channels != null) {
            log.debug("NB_CHANNELS_LIST: " + NB_CHANNELS_LIST + " , channels.size(): " + channels.size());
            Assert.assertEquals(NB_CHANNELS_LIST, channels.size());
        } else {
            Assert.fail("Aucun channels n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindChannelByName() {
    	List<Channel> ChannelByName = serviceFacade.getChannelDao().findChannelByName(CHANNELS_FIND_BY_NAME);
        log.debug("Entree de la methode");
        log.debug("nombre de channels : " + ChannelByName.size());
        if (users != null) {
            log.debug("NB_CHANNELS_FIND_BY_NAME: " + NB_CHANNELS_FIND_BY_NAME + " , ChannelByName.size(): " + ChannelByName.size());
            Assert.assertEquals(NB_CHANNELS_FIND_BY_NAME, ChannelByName.size());
        } else {
            Assert.fail("Aucun Channel n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteChannel() {
    	log.debug("Entree de la methode");
    	
    	/* test create */ 
    	log.debug("**** CREATE ****");
    	Channel channelCRUD = new Channel(3, "test", "Ceci est un channel de test unitaire", 1);
    	channelCRUD = serviceFacade.getChannelDao().createChannel(channelCRUD);
    	log.debug("created channelCRUD : " + channelCRUD);
    	channelCRUD = serviceFacade.getChannelDao().findChannelById(channelCRUD.getIdChannel());
    	Assert.assertNotNull(channelCRUD);
    	
    	/* test update */
    	log.debug("**** UPDATE ****");
    	channelCRUD.setName("Super test");
    	channelCRUD = serviceFacade.getChannelDao().updateChannel(channelCRUD);
    	Assert.assertNotNull(channelCRUD);
    	channelCRUD = serviceFacade.getChannelDao().findChannelById(channelCRUD.getIdChannel());
    	log.debug("Updated channelCRUD : " + channelCRUD);
    	Assert.assertEquals("Super test", channelCRUD.getName());
    	
    	/* test delete */
    	log.debug("**** DELETE ****");
    	Assert.assertTrue(serviceFacade.getChannelDao().deleteChannel(channelCRUD));
    	channelCRUD = serviceFacade.getChannelDao().findChannelById(channelCRUD.getIdChannel());
    	log.debug("deleted channelCRUD : " + channelCRUD);
    	Assert.assertNull(channelCRUD);
    	
    	
        List<Channel> channelsFinal = serviceFacade.getChannelDao().findAllChannels();
        if (channelsFinal != null) {
            Assert.assertEquals(NB_CHANNELS_LIST, channelsFinal.size());
            log.debug("channelsFinal.size() : " + channelsFinal.size() + " , NB_CHANNELS_LIST: " + NB_CHANNELS_LIST);
        }
    	log.debug("Sortie de la methode");
    }
    
    
    /* ****************************  TESTS MESSAGES  ********************************************* */
    
    @Test
    public void testFindAllMessages() {
        log.debug("Entree de la methode");
        log.debug("nombre de channels : " + messages);
        if (messages != null) {
            log.debug("NB_MESSAGES_LIST: " + NB_MESSAGES_LIST + " , messages.size(): " + messages.size());
            Assert.assertEquals(NB_MESSAGES_LIST, messages.size());
        } else {
            Assert.fail("Aucun channels n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    
    @Test
    public void testFindMessagesByIdUser() {
    	List<Message> messagesByIdUser = serviceFacade.getMessageDao().findMessageByIdUser(MESSAGES_BY_ID_USER);
        log.debug("Entree de la methode");
        log.debug("nombre de messages : " + messagesByIdUser.size());
        if (users != null) {
            log.debug("NB_MESSAGES_BY_ID_USER: " + NB_MESSAGES_BY_ID_USER + " , messagesByIdUser.size(): " + messagesByIdUser.size());
            Assert.assertEquals(NB_MESSAGES_BY_ID_USER, messagesByIdUser.size());
        } else {
            Assert.fail("Aucun message n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");	
    }
    
    @Test
    public void testFindMessagesByIdChannel() {
    	List<Message> messagesByIdChannel = serviceFacade.getMessageDao().findMessageByIdChannel(MESSAGES_BY_ID_CHANNEL);
        log.debug("Entree de la methode");
        log.debug("nombre de messages : " + messagesByIdChannel.size());
        if (users != null) {
            log.debug("NB_MESSAGES_BY_ID_USERS: " + NB_MESSAGES_BY_ID_CHANNEL + " , messagesByIdChannel.size(): " + messagesByIdChannel.size());
            Assert.assertEquals(NB_MESSAGES_BY_ID_CHANNEL, messagesByIdChannel.size());
        } else {
            Assert.fail("Aucun message n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");	
    }
    
    @Test
    public void testCreateUpdateDeleteMessage() {
    	log.debug("Entree de la methode");
    	
    	/* test create */ 
    	log.debug("**** CREATE ****");
    	User u = serviceFacade.getUserDao().findUserById(1);
    	Channel c = serviceFacade.getChannelDao().findChannelById(1);
    	
    	Message messageCRUD = new Message(3, "Ceci est un message de test unitaire", new Date(System.currentTimeMillis()), u, c);
    	messageCRUD = serviceFacade.getMessageDao().createMessage(messageCRUD);
    	log.debug("created messageCRUD : " + messageCRUD);
    	messageCRUD = serviceFacade.getMessageDao().findMessageById(messageCRUD.getIdMessage());
    	Assert.assertNotNull(messageCRUD);
    	
    	/* test update */
    	log.debug("**** UPDATE ****");
    	messageCRUD.setContentMessage("test");
    	messageCRUD = serviceFacade.getMessageDao().updateMessage(messageCRUD);
    	Assert.assertNotNull(messageCRUD);
    	messageCRUD = serviceFacade.getMessageDao().findMessageById(messageCRUD.getIdMessage());
    	log.debug("Updated messageCRUD : " + messageCRUD);
    	Assert.assertEquals("test", messageCRUD.getContentMessage());
    	
    	/* test delete */
    	log.debug("**** DELETE ****");
    	Assert.assertTrue(serviceFacade.getMessageDao().deleteMessage(messageCRUD));
    	messageCRUD = serviceFacade.getMessageDao().findMessageById(messageCRUD.getIdMessage());
    	log.debug("deleted messageCRUD : " + messageCRUD);
    	Assert.assertNull(messageCRUD);
    	
    	
        List<Message> messagesFinal = serviceFacade.getMessageDao().findAllMessages();
        if (messagesFinal != null) {
            Assert.assertEquals(NB_MESSAGES_LIST, messagesFinal.size());
            log.debug("usersFinal.messagesFinal() : " + messagesFinal.size() + " , NB_MESSAGES_LIST: " + NB_MESSAGES_LIST);
        }
    	log.debug("Sortie de la methode");
    }
    
    
    
    

}
