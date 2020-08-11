/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.entities;

import java.util.Date;

/**
 *
 * @author elhad
 */
public class Utilisateur {

    private static final long serialVersionUID = 1L;
    private Integer idUtilisateur;
    private String civilite;
    private String prenom;
    private String nom;
    private String identifiant;
    private String motPasse;
    private Date dateNaissance;
    private Date dateCreation;
    private Date dateModification;
    private Boolean actif = true;
    private Boolean marquerEffacer = false;
    private Integer version = 0;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Date dateCreation, Date dateModification, Boolean actif, Boolean marquerEffacer, Integer version, Role role) {
        this.idUtilisateur = idUtilisateur;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.identifiant = identifiant;
        this.motPasse = motPasse;
        this.dateNaissance = dateNaissance;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.actif = actif;
        this.marquerEffacer = marquerEffacer;
        this.version = version;
        this.role = role;
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Boolean actif, Boolean marquerEffacer, Integer version, Role role) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Role role) {
        this(idUtilisateur, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance, Role role) {
        this(null, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, role);
    }

    public Utilisateur(String civilite, String prenom, String nom, String identifiant, String motPasse, Date dateNaissance) {
        this(null, civilite, prenom, nom, identifiant, motPasse, dateNaissance, null, null, true, false, 0, null);
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(Utilisateur user) {
        this.idUtilisateur = user.idUtilisateur;
        this.civilite = user.civilite;
        this.prenom = user.prenom;
        this.nom = user.nom;
        this.identifiant = user.identifiant;
        this.motPasse = user.motPasse;
        this.dateNaissance = user.dateNaissance;
        this.dateCreation = user.dateCreation;
        this.dateModification = user.dateModification;
        this.actif = user.actif;
        this.marquerEffacer = user.marquerEffacer;
        this.version = user.version;
        this.role = user.role;
	}

	public Integer getIdUtilisateur() {
        return (this.idUtilisateur);
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCivilite() {
        return (this.civilite);
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return (this.prenom);
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return (this.nom);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return (this.identifiant);
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotPasse() {
        return (this.motPasse);
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Date getDateNaissance() {
        return (this.dateNaissance);
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateCreation() {
        return (this.dateCreation);
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return (this.dateModification);
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Boolean isActif() {
        return (this.actif);
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Boolean isMarquerEffacer() {
        return (this.marquerEffacer);
    }

    public void setMarquerEffacer(Boolean marquerEffacer) {
        this.marquerEffacer = marquerEffacer;
    }

    public Integer getVersion() {
        return (this.version);
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Role getRole() {
        return (this.role);
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
    	
        String resultat = super.toString(); 
        resultat +=  "\nidUtilisateur : " + idUtilisateur;   
        resultat +=  "\nCivilite : " + civilite;   
        resultat +=  "\nPrénom : " + prenom;
        resultat +=  "\nNom : " + nom;
        resultat +=  "\nIdentifiant : " + identifiant;
        resultat +=  "\nMot de passe : " + motPasse;
        resultat +=  "\nDate de naissance : " + dateNaissance;
        resultat +=  "\nDate de creation : " + dateCreation;
        resultat +=  "\nDate de Modification : " + dateModification;
        resultat +=  "\nActif : " + actif;
        resultat +=  "\nMarquer effacer : " + marquerEffacer;
        resultat +=  "\nVersion : " + version;
        resultat +=  "\nRole : " + role;
 
        return resultat ; 
    }
    
    @Override
    public boolean equals(Object o) {
   
    	Utilisateur utilisateur = (Utilisateur)o;
    	
           if (this == utilisateur)
             return true;
        
           if (utilisateur == null)
             return false;
           
           if (this.getIdUtilisateur() != utilisateur.getIdUtilisateur())
             return false;

           if (this.getCivilite() != utilisateur.getCivilite())
             return false;
           
           if (this.getPrenom() != utilisateur.getPrenom())
             return false;
           
           if (this.getNom() != utilisateur.getNom())
             return false;
           
           if (this.getIdentifiant() != utilisateur.getIdentifiant())
             return false;
           
           if (this.getMotPasse() != utilisateur.getMotPasse())
             return false;
           
           if (this.getDateNaissance() != utilisateur.getDateNaissance())
             return false;
           
           if (this.getDateCreation() != utilisateur.getDateCreation())
             return false;
           
           if (this.getDateModification() != utilisateur.getDateModification())
             return false;

           if (this.isActif() != utilisateur.isActif())
             return false;
           
           if (this.isMarquerEffacer() != utilisateur.isMarquerEffacer())
             return false;
           
           if (this.getVersion() != utilisateur.getVersion())
             return false;
           
           if (this.getRole() != utilisateur.getRole())
             return false;
         return true;
    }
    
    
    @Override
    public int hashCode() {
    	int hashCode = 17;
    	hashCode = 31 * hashCode + this.idUtilisateur;
    	hashCode = 31 * hashCode + ((this.civilite == null) ? 0 : this.civilite.hashCode());
    	hashCode = 31 * hashCode + ((this.prenom == null) ? 0 : this.prenom.hashCode());
    	hashCode = 31 * hashCode + ((this.nom == null) ? 0 : this.nom.hashCode());
    	hashCode = 31 * hashCode + ((this.identifiant == null) ? 0 : this.identifiant.hashCode());
    	hashCode = 31 * hashCode + ((this.motPasse == null) ? 0 : this.motPasse.hashCode());
    	hashCode = 31 * hashCode + ((this.dateNaissance == null) ? 0 : this.dateNaissance.hashCode());
    	hashCode = 31 * hashCode + ((this.dateCreation == null) ? 0 : this.dateCreation.hashCode());
    	hashCode = 31 * hashCode + ((this.dateModification == null) ? 0 : this.dateModification.hashCode());
    	hashCode = 31 * hashCode + ((this.dateNaissance == null) ? 0 : this.dateNaissance.hashCode());
    	hashCode = 31 * hashCode + (this.actif ? 1231 : 1237);
    	hashCode = 31 * hashCode + (this.marquerEffacer ? 1231 : 1237);
    	hashCode = 31 * hashCode + this.version;
    	
    	if (this.role != null) {
    		hashCode = this.role.hashCode();
    	}
    	
    	
    	return hashCode;
    }
}
