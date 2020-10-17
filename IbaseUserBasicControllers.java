package com.Ibase.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ibase.model.IbaseERole;
import com.Ibase.model.IbaseRole;
import com.Ibase.model.IbaseUser;
import com.Ibase.repository.IbaseRoleRepository;
import com.Ibase.repository.IbaseUserRepository;
import com.Ibase.services.IbaseUserBasicServices;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/user")
public class IbaseUserBasicControllers {
	
	@Autowired
	IbaseUserBasicServices ibaseUserBaicServices;
	
	@Autowired
	IbaseRoleRepository roleRepository;
	
	@Autowired
	IbaseUserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> viewAllUsers(
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "20") int pageSize, 
    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		System.out.println("ViewAllUsers");
		return ibaseUserBaicServices.viewAllUsers(pageNo, pageSize, sortBy);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<Optional<IbaseUser>> viewUserById(@PathVariable String userid) {
		System.out.println("ViewUserById");
		return ibaseUserBaicServices.viewUserById(userid);
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteUserById(@PathVariable String userid) {
		System.out.println("DeleteUserById");
		return ibaseUserBaicServices.deleteUserById(userid);
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<IbaseUser> updateUserById(@PathVariable String userid, @RequestBody IbaseUser updateUser) {
		Set<String> strRoles = updateUser.getUpdateRole(); 
		Set<IbaseRole> roles = new HashSet<>();
		
		if (strRoles == null) {
			IbaseRole userRole = roleRepository.findByName(IbaseERole.USER_ROLE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "user":
					IbaseRole userRole = roleRepository.findByName(IbaseERole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
		
					break;
				case "shop":
					IbaseRole shopRole = roleRepository.findByName(IbaseERole.SHOP_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(shopRole);
		
					break;
				case "admin":
					IbaseRole adminRole = roleRepository.findByName(IbaseERole.ADDMIN_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
		
					break;
				default:
					IbaseRole userRoles = roleRepository.findByName(IbaseERole.USER_ROLE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRoles);
				}
			});
		}
	
		updateUser.setRoles(roles);
		
		System.out.println("UpdateUserById");
		return ibaseUserBaicServices.updateUserById(userid,updateUser);
	}

}
