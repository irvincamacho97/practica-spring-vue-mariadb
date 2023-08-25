package com.GestorUsuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.GestorUsuarios.pojo.UserData;
import com.GestorUsuarios.services.UserService;



@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value ="/getAllActiveUser" , produces = "application/json; charset=utf-8")
	public List<UserData> getAllActiveUser() {		
	return userService.getAllActiveUser();
	}
	
    @PutMapping(value = "/addNewUser", produces = "application/json; charset=utf-8")
    @ResponseBody
	public String addNewUSer(@RequestBody UserData userData) {
		return userService.addNewUSer(userData);
	}
    
    @DeleteMapping(value="/deleteUser/{idUser}",produces="application/json; charset=utf-8")
	public String deleteUser(@PathVariable int idUser) {
		return userService.deleteUser(idUser);
    	
    }
    
	@GetMapping(value ="/getActiveUser/{idUser}" , produces = "application/json; charset=utf-8")
	public UserData getActiveUserId(@PathVariable int idUser) {		
	return userService.getActiveUserId(idUser);
	}
	
	@PostMapping(value ="/updateActiveUser" , produces = "application/json; charset=utf-8")
	public String updateActiveUser(@RequestBody UserData userData) {
		return userService.updateActiveUser(userData);
		
	}
	
	@PostMapping(value ="/loginUser/{email}" , produces = "application/json; charset=utf-8")
	public UserData loginUser(@PathVariable String email) {
		return userService.loginUser(email);
		
	}
}
