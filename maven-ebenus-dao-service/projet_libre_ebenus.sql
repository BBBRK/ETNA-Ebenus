-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 16 juin 2020 à 18:19
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_libre_ebenus`
--

-- --------------------------------------------------------

--
-- Structure de la table `channel`
--

DROP TABLE IF EXISTS `channel`;
CREATE TABLE IF NOT EXISTS `channel` (
  `idChannel` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(400) NOT NULL,
  `creator` int(10) NOT NULL,
  PRIMARY KEY (`idChannel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `channel`
--

INSERT INTO `channel` (`idChannel`, `name`, `description`, `creator`) VALUES
(1, 'Main', 'In this channel you can discuss of everything you want', 1),
(2, 'Chan 2', 'On parle de trucs', 2);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idUser` int(11) NOT NULL,
  `idChannel` int(11) NOT NULL,
  PRIMARY KEY (`idMessage`),
  KEY `FK_Message_User` (`idUser`),
  KEY `FK_Message_Channel` (`idChannel`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`idMessage`, `content`, `date`, `idUser`, `idChannel`) VALUES
(1, 'Hello world ! =D', '2020-06-03 10:12:07', 1, 1),
(2, 'Goodbye world ! =(', '2020-06-03 10:12:45', 2, 1),
(69, 'test', '2020-06-13 19:17:16', 1, 1),
(70, 'c\'est moi l\'admin ici', '2020-06-13 19:17:44', 2, 2),
(71, 'Et moi je ne le suis pas =(', '2020-06-13 19:18:03', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `idRole` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `name`) VALUES
(1, 'Administrateur'),
(2, 'Utilisateur');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `civilite` varchar(10) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idRole` int(11) NOT NULL,
  PRIMARY KEY (`idUser`),
  KEY `FK_User_Role` (`idRole`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `email`, `password`, `nickname`, `civilite`, `createdAt`, `updatedAt`, `idRole`) VALUES
(1, 'admin@gmail.com', 'azeaze', 'Alfred', 'Mr', '2020-05-30 14:05:49', '2020-06-13 17:50:19', 1),
(2, 'user1@gmail.com', 'azeaze', 'User1', 'Mrs', '2020-05-30 14:05:49', '2020-06-03 12:47:03', 2),
(3, 'user2@gmail.com', 'azeaze', 'User2', 'Mrs', '2020-06-03 13:18:11', '2020-06-16 18:09:57', 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
