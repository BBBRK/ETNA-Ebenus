package com.cours.ebenus.maven.ebenus.idao;

import com.cours.ebenus.maven.ebenus.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ElHadji
 */
public class DriverManagerSingleton {

    private static final Log log = LogFactory.getLog(DriverManagerSingleton.class);
    private static Connection connect = null;

    public final static String className = DriverManagerSingleton.class.getName();
    // Url de connexion en base de donnée
    private static final String url = Constants.DATABASE_URL;

    // Utilisateur de la base de données
    private static final String user = Constants.DATABASE_USER;

    // Mot de passe de la base de données
    private static final String password = Constants.DATABASE_PASSWORD;

    // Drivers Jdbc
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    
    private DriverManagerSingleton() {

    }

    synchronized public static Connection getConnectionInstance() {
        if(connect != null) {
            return connect;
        } else {
            try {
                Class.forName(jdbcDriver);
                String lsURL = url;
                connect = DriverManager.getConnection(lsURL, user, password);
                return connect;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    } 
}
