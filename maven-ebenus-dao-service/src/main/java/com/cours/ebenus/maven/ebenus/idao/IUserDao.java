package com.cours.ebenus.maven.ebenus.idao;

import java.util.List;

import com.cours.ebenus.maven.ebenus.dao.entities.User;

public interface IUserDao {
	
	public List<User> findAllUsers();
	
	public User findUserById(int idUser);
	
	public List<User> findUserByIdentifiant(String identifiant);
	
	public List<User> findUserByNickname(String nickname);
	
	public List<User> findUserByIdRole(int idRole);
	
	public List<User> findUserByIdentifiantRole(String identifiantRole);
	
    public User createUtilisateur(User user);

    public User updateUtilisateur(User user);
    
    public User updateUserWithoutPassword(User user);

    public boolean deleteUtilisateur(User user);

    public User authenticate(String email, String password);
	
	
}
