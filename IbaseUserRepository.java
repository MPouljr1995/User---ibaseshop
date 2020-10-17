package com.Ibase.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Ibase.model.IbaseUser;

@Repository
public interface IbaseUserRepository extends MongoRepository<IbaseUser, String>{
	
	  Optional<IbaseUser> findByUserName(String userName);

	  Boolean existsByUserName(String userName);

	  Boolean existsByEmail(String email);
}
