package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.entities.UserRoles;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@RestController
@RequestMapping(value = "project/users")
public class UserControllers {
	@Autowired
	 UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public UserEntity createUser(@RequestParam String name, @RequestParam String email, @RequestParam String lastName,
			@RequestParam String userName, @RequestParam UserRoles role, @RequestParam String password) {
		UserEntity user = new UserEntity();
		user.setEmail(email);
		user.setFirstName(name);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setUserRoles(role);
		user.setPassword(password);
		UserEntity retUser = userRepository.save(user);
		return retUser;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<UserEntity> getAll() {
		return (List<UserEntity>) userRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserEntity getOne(@PathVariable Integer id) {
		UserEntity getOne = userRepository.findById(id).get();

		return getOne;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserEntity updateUser(@RequestBody UserEntity user, @PathVariable Integer id) {
		if (!userRepository.existsById(id))
			return null;
		UserEntity userFound = userRepository.findById(id).get();
		if (user.getFirstName() != null) {
			userFound.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			userFound.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			userFound.setEmail(user.getEmail());
		}
		if (user.getUserName() != null) {
			userFound.setUserName(user.getUserName());
		}
		if (user.getPassword() != null) {
			userFound.setPassword(user.getPassword());
		}
		if (user.getUserRoles() != null) {
			userFound.setUserRoles(user.getUserRoles());
		}

		return userRepository.save(userFound);
	}

	@RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	public UserEntity deletedUser(@PathVariable Integer id) {
	
			UserEntity deleteUser = userRepository.findById(id).get();
			userRepository.delete(deleteUser);
		return deleteUser;
	
	}
@RequestMapping(method=RequestMethod.PUT,value= "/change/{id}/role/{role}")
public UserEntity changeUserRole(@PathVariable Integer id,@PathVariable UserRoles role) {
	UserEntity changeRoleUser= userRepository.findById(id).get();
	changeRoleUser.setUserRoles(role);
	return userRepository.save(changeRoleUser);
}
@RequestMapping(method=RequestMethod.PUT, value="/changePassword/{id}")
public UserEntity changePass (@PathVariable Integer id, @RequestParam String oldPass, @RequestParam String newPass) {
	UserEntity changePassUser =userRepository.findById(id).get();
	if(changePassUser.getPassword().equals(oldPass)) {
		changePassUser.setPassword(newPass);
	}
	return userRepository.save(changePassUser);
}
@RequestMapping(method=RequestMethod.GET,value="/by-userName/userName")
public UserEntity findByUserName(@RequestParam String username) {
	UserEntity user= userRepository.findByUserName(username);
	return user;
}
}
