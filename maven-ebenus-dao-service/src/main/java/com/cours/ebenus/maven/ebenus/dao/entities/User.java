package com.cours.ebenus.maven.ebenus.dao.entities;
import java.util.Date;

public class User {
	
	 private static final long serialVersionUID = 1L;
	 private Integer idUser;
	 private String civilite;
	 private String identifiant;
	 private String password;
	 private String nickname;
	 private Date creationDate;
	 private Date updatedDate;
	 private Role role;
	 
	 
	 public User() {
			
	 }
	 
	 public User(Integer idUser, String civilite, String identifiant, String password, String nickname, Date creationDate, Date updatedDate, Role role) {
		 this.idUser = idUser;
		 this.civilite = civilite;
		 this.identifiant = identifiant;
		 this.password = password;
		 this.nickname = nickname;
		 this.creationDate = creationDate;
		 this.updatedDate = updatedDate;
		 this.role = role;
	 }
	 
	 public User(Integer idUser, String civilite, String identifiant, String password, String nickname, Role role) {
		 this(idUser, civilite, identifiant, password, nickname, null, null, role);
	 }
	 
	 public User(String civilite, String identifiant, String password, String nickname, Role role) {
		 this(null, civilite, identifiant, password, nickname, null, null, role);
	 }
	 
	 public User(String civilite, String identifiant, String password, String nickname) {
		 this(null, civilite, identifiant, password, nickname, null, null, null);
	 }
	 
	 public User(User user) {
		 this.idUser = user.idUser;
		 this.civilite = user.civilite;
		 this.identifiant = user.identifiant;
		 this.password = user.password;
		 this.nickname = user.nickname;
		 this.creationDate = user.creationDate;
		 this.updatedDate = user.updatedDate;
		 this.role = user.role;
	 }
	 
	 public Integer getIdUser() {
		 return (this.idUser);
	 }
	 
	 public void setIdUser(Integer idUser) {
		 this.idUser = idUser;
	 }
	 
	 public String getCivilite() {
		 return (this.civilite);
	 }
	 
	 public void setCivilite(String civilite) {
		 this.civilite = civilite;
	 }
	 
	 public String getIdentifiant() {
		 return (this.identifiant);
	 }
	 
	 public void setIdentifiant(String identifiant) {
		 this.identifiant = identifiant;
	 }
	 
	 public String getPassword() {
		 return (this.password);
	 }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 public String getNickname() {
		 return (this.nickname);
	 }
	 
	 public void setNickname(String nickname) {
		 this.nickname = nickname;
	 }
	 
	 public Date getCreationDate() {
		 return (this.creationDate);
	 }
	 
	 public void setCreationDate(Date creationDate) {
		 this.creationDate = creationDate;
	 }
	 
	 public Date getUpdatedDate() {
		 return (this.updatedDate);
	 }
	 
	 public void setUpdatedDate(Date updatedDate) {
		 this.updatedDate = updatedDate;
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
	        resultat +=  "\nIdUser : " + idUser;   
	        resultat +=  "\nCivilite : " + civilite;   
	        resultat +=  "\nIdentifiant : " + identifiant;
	        resultat +=  "\nPassword : " + password;
	        resultat +=  "\nNickname : " + nickname;
	        resultat +=  "\nCreation date : " + creationDate;
	        resultat +=  "\nUpdate date : " + updatedDate;
	        if(role != null) {
	        	 resultat +=  "\nRole : " + role;
	        }
	        return resultat ; 
	    }
	 

	    @Override
	    public boolean equals(Object o) {
	    	
	           if (!(o instanceof User)) {
	        	   return false;
	           }
	             
	           User other = (User) o;
	           if((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
	        	   return false;
	           }
	         return true;
	    }

	    @Override
	    public int hashCode() {
	    	int hashCode = 17;
	    	hashCode = 31 * hashCode + this.idUser;
	    	hashCode = 31 * hashCode + ((this.civilite == null) ? 0 : this.civilite.hashCode());
	    	hashCode = 31 * hashCode + ((this.identifiant == null) ? 0 : this.identifiant.hashCode());
	    	hashCode = 31 * hashCode + ((this.password == null) ? 0 : this.password.hashCode());
	    	hashCode = 31 * hashCode + ((this.nickname == null) ? 0 : this.nickname.hashCode());
	    	hashCode = 31 * hashCode + ((this.creationDate == null) ? 0 : this.creationDate.hashCode());
	    	hashCode = 31 * hashCode + ((this.updatedDate == null) ? 0 : this.updatedDate.hashCode());
	    	
	    	if (this.role != null) {
	    		hashCode = this.role.hashCode();
	    	}
	    	return hashCode;
	    }
}


