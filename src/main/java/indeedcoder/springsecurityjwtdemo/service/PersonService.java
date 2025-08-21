package indeedcoder.springsecurityjwtdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.Person;
import indeedcoder.springsecurityjwtdemo.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repository;

	@Autowired
	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}

	public Person create(Person person) {
		return repository.save(person);
	}

	public Person findById(int id) {
		return repository.findById(id).orElse(new Person(-1, "Not-found-by-id-"+id));
	}

	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person update(Person person) {
		if(repository.doesExistsById(person.getId())) {
			return repository.save(person);
		}
		return new Person(-1, "Not-found");
	}
}
