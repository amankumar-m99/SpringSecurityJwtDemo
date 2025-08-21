package indeedcoder.springsecurityjwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indeedcoder.springsecurityjwtdemo.entity.Person;
import indeedcoder.springsecurityjwtdemo.service.PersonService;

@RequestMapping("/person")
@RestController
public class PersonController {

	private PersonService service;

	@Autowired
	public PersonController(PersonService service) {
		this.service = service;
	}

	@PostMapping("/person")
	public Person create(Person person) {
		return service.create(person);
	}

	@GetMapping("/person/{id}")
	public Person findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/persons")
	public List<Person> findAll() {
		return service.findAll();
	}

	@PutMapping("/person")
	public Person update(Person person) {
		return service.update(person);
	}
}
