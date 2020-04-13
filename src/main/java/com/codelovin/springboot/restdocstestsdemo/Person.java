package com.codelovin.springboot.restdocstestsdemo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

	private Long id;
	
	@NotNull(message = "First name should not be empty")
	@Size(min = 1, max = 50, message = "First name should be between 1 and 60 characters")
	private String firstName;
	
	@Size(min = 1, max = 50, message = "Last name should be between 1 and 60 characters")
	private String lastName;
	
	private String emailAddress;
	
	public Person() {}
	
	public Person(Long id, String firstName, String lastName, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
