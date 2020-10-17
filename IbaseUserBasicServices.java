package com.Ibase.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Ibase.model.IbaseUser;
import com.Ibase.repository.IbaseUserRepository;

@Service
public class IbaseUserBasicServices {
	@Autowired
	IbaseUserRepository ibaseUserRepository;

	public ResponseEntity<Map<String, Object>> viewAllUsers(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
			Page<IbaseUser> page = ibaseUserRepository.findAll(pageable);
			response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("Total no of elements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	public ResponseEntity<Optional<IbaseUser>> viewUserById(String userid) {
		Optional<IbaseUser> user = ibaseUserRepository.findById(userid);
		try {
			if (user.isPresent()) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> deleteUserById(String userid) {
		try {
			ibaseUserRepository.deleteById(userid);
			return new ResponseEntity<>("User Deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		}
		
	}

	public ResponseEntity<IbaseUser> updateUserById(String userid, IbaseUser updateUser) {
		Optional<IbaseUser> oldUser = ibaseUserRepository.findById(userid);
		
		try {
			if (oldUser.isPresent()) {
				IbaseUser _user = oldUser.get();
				_user.setFirstName(updateUser.getFirstName());
				_user.setLastName(updateUser.getLastName());
				_user.setAddress(updateUser.getAddress());
				_user.setRoles(updateUser.getRoles());
				_user.setPhoneNumber(updateUser.getPhoneNumber());
				
				return new ResponseEntity<>(ibaseUserRepository.save(_user),HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}	
		
		
		
	}
	

}
