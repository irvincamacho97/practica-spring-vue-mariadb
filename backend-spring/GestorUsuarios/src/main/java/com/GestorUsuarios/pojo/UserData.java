package com.GestorUsuarios.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	private int idUser;
	private String name;
	private String male;
	private String email;
	private boolean status;
	private String profilePicture;
	
}
