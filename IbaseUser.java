package com.Ibase.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class IbaseUser {
	@Id
	private String userId;
	private String userName;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private Date date;
	private Set<String> updateRole;
	@DBRef
	private Set<IbaseRole> roles = new HashSet<>();
	

	public IbaseUser(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public IbaseUser(String userName, String email, String password, String firstName, String lastName,
			String phoneNumber, String address, Date date) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.date = date;
		
	}
	
	public IbaseUser() {
		super();
	}
	
	

	public String getUserId() {
	    return userId;
	  }

	  public void setUserId(String id) {
	    this.userId = id;
	  }

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Set<IbaseRole> getRoles() {
		return roles;
	}


	public void setRoles(Set<IbaseRole> roles) {
		this.roles = roles;
	}

	public Set<String> getUpdateRole() {
		return updateRole;
	}

	public void setUpdateRole(Set<String> updateRole) {
		this.updateRole = updateRole;
	}

}
