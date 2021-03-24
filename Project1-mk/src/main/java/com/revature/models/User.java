package com.revature.models;

import java.io.Serializable;

/**
 * Represents a User of the system.
 * Can be a CUSTOMER or EMPLOYEE.
 * Every user can have one or more accounts.
 */
public class User implements Serializable {
	/**Automatically generated universally unique identifier */
	private static final long serialVersionUID = -8630467975639842515L;
	
	public static enum userRole {
		MANAGER, EMPLOYEE
	}
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private userRole userRole;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public userRole getUserRole() {
		return userRole;
	}
	public void setUserRole(userRole userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}
}
