package com.cours.ebenus.maven.ebenus.dao.entities;

public class Channel {
	
	 private static final long serialVersionUID = 1L;
	 private Integer idChannel;
	 private String name;
	 private String description;
	 private Integer creator;
	 
	 public Channel() {
		 
	 }
	 
	 public Channel(Integer idChannel, String name, String description, Integer creator) {
		 this.idChannel = idChannel;
		 this.name = name;
		 this.description = description;
		 this.creator = creator;
	 }
	 
	 public Channel(String name, String description, Integer creator) {
		 this(null, name, description, creator);
	 }
	 
	 public Channel(Channel channel) {
		 this.idChannel = channel.idChannel;
		 this.name = channel.name;
		 this.description = channel.description;
	 }
	 
		public Integer getIdChannel() {
	        return (this.idChannel);
	    }

	    public void setIdChannel(Integer idRole) {
	        this.idChannel = idRole;
	    }
	    
	    public Integer getCreator() {
	    	return (this.creator);
	    }
	    
	    public void setCreator(Integer creator) {
	    	this.creator = creator;
	    }
	    
	    public String getName() {
	    	return (this.name);
	    }
	    
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    public String getDescription() {
	    	return (this.description);
	    }
	    
	    public void setDescription(String description) {
	    	this.description = description;
	    }
	    
	    @Override
	    public String toString() {
	    	
	    	String resultat = super.toString();
	    	resultat += "\nIdChannel : " + idChannel;
	    	resultat += "\nName : " + name;
	    	resultat += "\nDescription : " + description;
	    	resultat += "\nCreator : " + creator;
	    	
	    	return resultat;
	    }
	    
	    @Override
	    public boolean equals(Object o) {
	    	
	           if (!(o instanceof Channel)) {
	        	   return false;
	           }
	             
	           Channel other = (Channel) o;
	           if((this.idChannel == null && other.idChannel != null) || (this.idChannel!= null && !this.idChannel.equals(other.idChannel))) {
	        	   return false;
	           }
	         return true;
	    }
	    
	    @Override
	    public int hashCode() {
	    	int hashCode = 17;
	    	hashCode = 31 * hashCode + this.idChannel;
	    	hashCode = 31 * hashCode + ((this.name == null) ? 0 : this.name.hashCode());
	    	hashCode = 31 * hashCode + ((this.description == null) ? 0 : this.description.hashCode());
	    	hashCode = 31 * hashCode + this.creator;
	  
	    	return hashCode;
	    }
	    

}
