-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 24 Avril 2017 à 19:11
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetstage`
--
CREATE DATABASE IF NOT EXISTS `projetstage` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `projetstage`;

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `ID` int(11) NOT NULL,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `mdp` text NOT NULL,
  `ville` text NOT NULL,
  `rue` text NOT NULL,
  `codepostal` text NOT NULL,
  `telephone` text NOT NULL,
  `mail` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`ID`, `Nom`, `Prenom`, `mdp`, `ville`, `rue`, `codepostal`, `telephone`, `mail`) VALUES
(69, 'Dragnir', 'Natsu', '21232f297a57a5a743894a0e4a801fc3', 'Fairy Tail', '3 rue Salamenders', '91150', '0123456789', 'natsudragnir@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

CREATE TABLE `candidature` (
  `ID` int(11) NOT NULL,
  `statut` text NOT NULL,
  `idOffreStage` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `candidature`
--

INSERT INTO `candidature` (`ID`, `statut`, `idOffreStage`, `idEtudiant`) VALUES
(81, 'En attente', 40, 1),
(85, 'En attente', 62, 1),
(86, 'En attente', 63, 1),
(87, 'En attente', 63, 8);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `Ville` text NOT NULL,
  `Nom` text NOT NULL,
  `Rue` text NOT NULL,
  `CodePostal` text NOT NULL,
  `Telephone` text NOT NULL,
  `Mail` text NOT NULL,
  `SecteurActivité` text NOT NULL,
  `RaisonSociale` text NOT NULL,
  `mdp` text NOT NULL,
  `ID` int(11) NOT NULL,
  `logo` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='Liste des entreprises';

--
-- Contenu de la table `entreprise`
--

INSERT INTO `entreprise` (`Ville`, `Nom`, `Rue`, `CodePostal`, `Telephone`, `Mail`, `SecteurActivité`, `RaisonSociale`, `mdp`, `ID`, `logo`) VALUES
('Paris', 'Google', '65 rue des Elysées', '75000', '0621359754', 'google@gmail.com', 'Informatique', 'CORP', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 125, 'logo-google.jpg'),
('Evry', 'Oracle', '5 rue des porcs', '91000', '0123456987', 'infomaster@gmail.com', 'Informatique', 'CORP', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 100, 'oracle.png'),
('Draveil', 'Guimex', '19 rue des Singes', '91520', '0651849732', 'guimex@gmail.com', 'Communication', 'CORP', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 102, 'guimex.png');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `ID` int(11) NOT NULL,
  `Nom` text NOT NULL,
  `Prenom` text NOT NULL,
  `mdp` text NOT NULL,
  `ville` text NOT NULL,
  `rue` text NOT NULL,
  `codepostal` text NOT NULL,
  `telephone` text NOT NULL,
  `mail` text NOT NULL,
  `accept` tinyint(1) NOT NULL,
  `message` tinyint(1) NOT NULL,
  `cv` text NOT NULL,
  `lettre` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`ID`, `Nom`, `Prenom`, `mdp`, `ville`, `rue`, `codepostal`, `telephone`, `mail`, `accept`, `message`, `cv`, `lettre`) VALUES
(1, 'Blanchis', 'Aurèle', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 'Yerres', '3 rue des dieux', '91330', '0687952416', 'aur09@gmail.com', 1, 0, 'CV.pdf', 'Lettre_Motivation.pdf'),
(8, 'Thoraval', 'Adlan', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 'Yerres', '45 rue des Petits Bouts', '91330', '0651973265', 'adlan.thor@gmail.com', 0, 0, '', ''),
(9, 'Desbois', 'Maxime', 'aa36dc6e81e2ac7ad03e12fedcb6a2c0', 'Draveil', '5 rue des Moutons', '91520', '0632159764', 'max.des@gmail.com', 0, 0, '', '');

-- --------------------------------------------------------

--
-- Structure de la table `offrestage`
--

CREATE TABLE `offrestage` (
  `ID` int(11) NOT NULL,
  `libelle` text NOT NULL,
  `description` text NOT NULL,
  `domaine` text NOT NULL,
  `datedebut` date NOT NULL,
  `duree` int(11) NOT NULL,
  `valide` tinyint(1) NOT NULL,
  `IDentreprise` int(11) NOT NULL,
  `changer` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `offrestage`
--

INSERT INTO `offrestage` (`ID`, `libelle`, `description`, `domaine`, `datedebut`, `duree`, `valide`, `IDentreprise`, `changer`) VALUES
(62, 'Ingénieur Réseaux', 'Vous réalisez des opérations d\'exploitation et de maintenance des systèmes informatiques. Et vous participez activement à la gestion des projets métiers.\nPlus précisément, vous êtes en charge :\n\n- De réaliser des opérations de maintenance des systèmes informatiques au siège et sur les autres sites. \n- D\'intervenir sur les installations, les configurations et les recettes de systèmes déployés ou en phase de déploiement\n- D\'assurer les diagnostics systèmes\n- De gérer l’assistance utilisateur, et la maintenance des réseaux \n- D\'installer des applications métiers pour les utilisateurs\n- D\'intervenir dans le cadre de la préparation et de l’installation physique de matériels et logiciels d’infrastructure (serveurs, logiciels OS, postes de travail, périphériques,…)\n- D\'assurer l’interface de premier niveau (hot-line) avec les collaborateurs.\n- De collaborer dans le but de régler rapidement les incidents rencontrés\n- D\'apporter satisfaction aux utilisateurs en leur apportant des solutions aux problèmes soumis\n- D\'intervention sur des sujets plus complexes comme les bases de données, la virtualisation de serveur sous VmWare,..\n- D\'intervenir en matière de réseaux, de sécurité informatique, de gestion et de mise en œuvre de projets informatiques ERP (progiciel métiers).', 'Réseau', '2017-05-21', 6, 1, 125, 0),
(40, 'WEB H/F', 'Au sein du Pôle Expertise de la Business Line Zodiac Data Systems, vous êtes rattaché(e) à l\'architecte logiciel.\n\nVous participerez à ce titre à l\'extension des accès du système de suivi d\'anomalies logicielles (Mantis) pour les clients « Maintien Condition Opérationnel » en effectuant la synchronisation de bases de faits techniques. \n\nMantis est un système de suivi d\'anomalies logicielles (bugs) basé sur une interface web. Il est écrit en PHP et requiert une base de données (MySQL, SQL Server, PostgreSQL ou DB2) supportée et un serveur web.\n\nAprès vous être familiarisé(e) avec les activités de l\'entreprise et ses processus, votre mission consiste à :\n\n-Faire une analyse de l\'existant ;\n-Mettre en place des plateformes de tests ;\n-Concevoir et réaliser des modules de synchronisation.', 'Développement', '2017-05-12', 4, 1, 100, 0),
(63, 'STAGE - - H/F', 'Dans le cadre de ses recherches sur l\'utilisation de grands corpus textuels\n(rapports de maintenance, réclamations clients...),\nle département ICAME souhaiterait mettre en place une chaine de traitement text mining basée sur des outils open source\n(Iramuteq, GATE, R, scikit learn...), afin de répondre aux différentes problématiques métier d\'EDF.\nUtilisant actuellement ces outils de manière séquentielle,\nla R&D d\'EDF souhaiterait explorer la possibilité d\'automatiser cette chaine de traitement,\nen intégrant au maximum ces outils dans un logiciel unique et en proposant une interface utilisateur lorsque l\'intervention humaine est nécessaire.\nIl s\'agira de faire un travail de développement logiciel avec intégration d\'outils open source.\nLe stage proposé s\'inscrit dans cette mission. Il s\'agira notamment :\nD\'optimiser les performances de traitement des outils\nD\'automatiser la chaine de traitement de text mining, en intégrant des outils open source\nDe proposer une interface utilisateur pour les étapes demandant l\'intervention d\'une/un data scientist\nDe rédiger la documentation', 'Développement', '2017-06-01', 2, 1, 125, 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `offrestage`
--
ALTER TABLE `offrestage`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;
--
-- AUTO_INCREMENT pour la table `candidature`
--
ALTER TABLE `candidature`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;
--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;
--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `offrestage`
--
ALTER TABLE `offrestage`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
