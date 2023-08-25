package com.GestorUsuarios.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.GestorUsuarios.mapper.UserMapper;
import com.GestorUsuarios.pojo.UserData;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public List<UserData> getAllActiveUser() {
		return userMapper.getAllActiveUser();
	}

	@Override
	public String addNewUSer(UserData userData) {

		if (userData.getName() != null && userData.getEmail() != null) {
			
			System.out.println("Datos de usuario------>"+userData.getName()+userData.getMale()+userData.getProfilePicture()+userData.getEmail());
			
			userMapper.addNewUSer(userData.getName(), userData.getMale(), true, userData.getProfilePicture(),
					userData.getEmail());
			return "Usuario ingresado";
		} else {
			return "Error: Falta información importante";
		}
	}

	@Override
	public String deleteUser(int idUser) {
		
		userMapper.deleteUser(idUser);
		return "Usuario eliminado";
	}

	@Override
	public UserData getActiveUserId(int idUser) {
		
		return userMapper.getActiveUserId(idUser);
	}

	@Override
	public String updateActiveUser(UserData userData) {
		
		userMapper.updateActiveUser(userData);
		return "Usuario Actualizado";
	}

	@Override
	public UserData loginUser(String email) {
		
		UserData user = userMapper.loginUser(email);
	    if (user == null) {
	        return null;
	    } else {
	        return user;
	    }
		}

}
