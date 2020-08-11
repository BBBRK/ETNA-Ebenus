package com.cours.ebenus.maven.ebenus.idao;

import java.util.Date;
import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.Message;

public interface IMessageDao {
	
	public List<Message> findAllMessages();
	
	public Message findMessageById(int idMessage);
	
	public List<Message> findMessageByDate(Date date);
	
	public List<Message> findMessageByIdUser(int idUser);
	
	public List<Message> findMessageByIdChannel(int idChannel);
	
	public Message createMessage(Message message);
	
	public Message updateMessage(Message message);

	public boolean deleteMessage(Message message);
}
