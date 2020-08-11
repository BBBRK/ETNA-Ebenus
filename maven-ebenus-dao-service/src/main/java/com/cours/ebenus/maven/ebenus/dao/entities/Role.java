package com.cours.ebenus.maven.ebenus.dao.entities;


public class Role {
	
	 private static final long serialVersionUID = 1L;
	 private Integer idRole;
	 private String name;
	 
	 public Role() {
		 
	 }
	 
	 public Role(Integer idRole, String name) {
		 this.idRole = idRole;
		 this.name = name;
	 }
	 
	 public Role(Role role) {
		 this.idRole = role.idRole;
		 this.name = role.name;
	 }
	 
		public Integer getIdRole() {
	        return (this.idRole);
	    }

	    public void setIdRole(Integer idRole) {
	        this.idRole = idRole;
	    }
	    
	    public String getName() {
	    	return (this.name);
	    }
	    
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    @Override
	    public String toString() {
	    	
	    	String resultat = super.toString();
	    	resultat += "\nIdRole : " + idRole;
	    	resultat += "\nName : " + name;
	    	
	    	return resultat;
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	    	
	           if (!(o instanceof Role)) {
	        	   return false;
	           }
	             
	           Role other = (Role) o;
	           if((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
	        	   return false;
	           }
	          
	         return true;
	    }
	    
	    @Override
	    public int hashCode() {
	    	int hashCode = 17;
	    	hashCode = 31 * hashCode + this.idRole;
	    	hashCode = 31 * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
	  
	    	return hashCode;
	    }

}
