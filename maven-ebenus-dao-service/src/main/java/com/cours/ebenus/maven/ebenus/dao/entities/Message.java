package com.cours.ebenus.maven.ebenus.dao.entities;
import java.util.Date;

public class Message {
	
	 private static final long serialVersionUID = 1L;
	 private Integer idMessage;
	 private String content;
	 private Date date;
	 private User user;
	 private Channel channel;
	 
	 public Message(){
		 
	 }
	 
	public Message(Integer idMessage, String content, Date date, User user, Channel channel){
		 this.idMessage = idMessage;
		 this.content = content;
		 this.date = date;
		 this.user = user;
		 this.channel = channel;
	 }
	 
	 public Integer getIdMessage() {
		 return (this.idMessage);
	 }
	 
	 public void setIdMessage(Integer idMessage) {
		 this.idMessage = idMessage;
	 }
	 
	 public String getContentMessage() {
		 return (this.content);
	 }
	 
	 public void setContentMessage(String content) {
		 this.content = content;
	 }
	 
	 public Date getDateMessage() {
		 return (this.date);
	 }
	 
	 public void setDateMessage(Date date) {
		 this.date = date;
	 }
	 
	 public User getUserMessage() {
		 return (this.user);
	 }
	 
	 public void setUserMessage(User user) {
		 this.user = user;
	 }
	 
	 public Channel getChannelMessage() {
		 return (this.channel);
	 }
	 
	 public void setChannelMessage(Channel channel) {
		 this.channel = channel;
	 }
	 
	 @Override
	    public String toString() {
	    	
	        String resultat = super.toString(); 
	        resultat +=  "\nIdMessage : " + idMessage;   
	        resultat +=  "\nContent : " + content;   
	        resultat +=  "\nDate : " + date;
	        if(user != null) {
	        	 resultat +=  "\nRole : " + user;
	        }
	        if(channel != null) {
	        	 resultat +=  "\nRole : " + channel;
	        }
	        return resultat ; 
	    }
	 
	    @Override
	    public boolean equals(Object o) {
	    	
	           if (!(o instanceof Message)) {
	        	   return false;
	           }
	             
	           Message other = (Message) o;
	           if((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
	        	   return false;
	           }
	         return true;
	    }
	    
	    @Override
	    public int hashCode() {
	    	int hashCode = 17;
	    	hashCode = 31 * hashCode + this.idMessage;
	    	hashCode = 31 * hashCode + ((this.content == null) ? 0 : this.content.hashCode());
	    	hashCode = 31 * hashCode + ((this.date == null) ? 0 : this.date.hashCode());
	    	if (this.user != null) {
	    		hashCode = this.user.hashCode();
	    	}
	    	if (this.channel != null) {
	    		hashCode = this.channel.hashCode();
	    	}
	    	return hashCode;
	    }
}
