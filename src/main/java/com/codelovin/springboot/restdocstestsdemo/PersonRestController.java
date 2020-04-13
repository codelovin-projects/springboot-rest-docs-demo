package com.codelovin.springboot.restdocstestsdemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/api/persons/hello")
	public Map<String, Object> greeting() {
		return Collections.singletonMap("message", "Hello, World");
	}
	
	@GetMapping("/api/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Long id) {
		Person p = personService.getPerson(id);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@GetMapping("/api/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
	}
	
	@PostMapping("/api/persons")
	public ResponseEntity<Person> savePerson(@RequestBody @Valid Person person) {
		return new ResponseEntity<>(personService.savePerson(person), HttpStatus.OK);
	}
	
	@PutMapping("/api/persons/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person person, @PathVariable Long id) {
		return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/persons/{id}")
	public ResponseEntity<Map<String, String>> deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
		Map<String, String> res = new HashMap<String, String>();
		res.put("status", "Deleted");
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
