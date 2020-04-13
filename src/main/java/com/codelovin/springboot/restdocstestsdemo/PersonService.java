package com.codelovin.springboot.restdocstestsdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	public Person savePerson(Person p) {
		// person is saved to db
		p.setId(1L);
		return p;
	}
	
	public Person updatePerson(Long id, Person p) {
		// person is updated in db
		return p;
	}	

	public Person getPerson(Long id) {
		Person p = new Person(id, "David", "Smith", "david@example.com");
		return p;
	}
	
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<>();
		Person p = new Person(1L, "David", "Smith", "david@example.com");
		persons.add(p);
		p = new Person(1L, "Jon", "Conway", "jon@example.com");
		persons.add(p);
		return persons;
	}
	
	public boolean deletePerson(Long id) {
		// delete the person from db
		return true;
	}
}
