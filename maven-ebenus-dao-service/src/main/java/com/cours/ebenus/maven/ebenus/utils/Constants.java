package com.cours.ebenus.maven.ebenus.utils;

public class Constants {

    // Url de connexion en base de donnée
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/projet_libre_ebenus?useSSL=false";
    // Utilisateur de la base de données
    public static String DATABASE_USER = "application";
    // Mot de passe de la base de données
    public static String DATABASE_PASSWORD = "passw0rd";
    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static String SQL_JUNIT_PATH_FILE = "script_ebenus_projet_libre.sql";

    public static int EXCEPTION_CODE_USER_ALREADY_EXIST = -1;

}
