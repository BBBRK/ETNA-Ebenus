/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.entities;

/**
 *
 * @author elhad
 */
public class Role {

    private static final long serialVersionUID = 1L;
    private Integer idRole;
    private String identifiant;
    private String description;
    private Integer version = 0;

    public Role() {
    }

    public Role(Integer idRole, String identifiant, String description) {
        this.idRole = idRole;
        this.identifiant = identifiant;
        this.description = description;
    }

    public Role(String identifiant, String description) {
        this(null, identifiant, description);
    }

    public Role(Integer idRole) {
        this(idRole, null, null);
    }

    public Role(Role role) {
		this.idRole = role.idRole;
		this.identifiant = role.identifiant;
		this.description = role.description;
		this.version = role.version;
	}

	public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getIdentifiant() {
        return (this.identifiant);
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @Override
    public String toString() {
    	
        String resultat = super.toString(); 
        resultat +=  "\nIdRole : " + idRole;   
        resultat +=  "\nIdentifiant : " + identifiant;   
        resultat +=  "\nDescription : " + description;
        resultat +=  "\nVersion : " + version;
 
        return resultat ; 
    }
    
    @Override
    public boolean equals(Object o) {
   
    	Role role = (Role)o;
    	
           if (o == this)
             return true;
        
           if (role == null || o.getClass() != this.getClass())
             return false; 
           
           if (this.getIdRole() != role.getIdRole())
               return false;
           
           if (this.getIdentifiant() != role.getIdentifiant())
             return false;

           if (this.getDescription() != role.getDescription())
             return false;
           
           if (this.getVersion() != role.getVersion())
             return false;
                      
         return true;
    }
    
    
    @Override
    public int hashCode() {
    	int hashCode = 17;
    	hashCode = 31 * hashCode + this.idRole;
    	hashCode = 31 * hashCode + ((this.identifiant == null) ? 0 : this.identifiant.hashCode());
    	hashCode = 31 * hashCode + ((this.description == null) ? 0 : this.description.hashCode());
    	hashCode = 31 * hashCode + this.version;
    	
    	return hashCode;
    }
}
