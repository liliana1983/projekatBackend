package com.iktpreobuka.dataaccess.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
	
public UserEntity findByEmail(String email);
	
	public UserEntity findByFirstName(String name);
	public UserEntity deleteUserById(Integer id);

	public UserEntity findByUserName(String username);
	
	//public UserEntity Update(UserEntity user);
	
}
