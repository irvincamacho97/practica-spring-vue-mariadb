package com.GestorUsuarios.services;

import java.util.List;


import com.GestorUsuarios.pojo.UserData;

public interface UserService {
	
	public List<UserData> getAllActiveUser();
	
	public String addNewUSer(UserData userData);
	
	public String deleteUser(int idUser);

	public UserData getActiveUserId(int idUser);
	
	public String updateActiveUser( UserData userData);
	
	public UserData loginUser( String email);
}
