package indeedcoder.springsecurityjwtdemo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import indeedcoder.springsecurityjwtdemo.entity.Person;

@Component
public class PersonRepository extends HashMap<Integer, Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781631828228376594L;

	public Person save(Person person) {
		if (!containsKey(person.getId())) {
			person.setId(size());
		}
		put(person.getId(), person);
		return person;
	}

	public Person[] saveAll(Person... persons) {
		for (Person person : persons) {
			if (!containsKey(person.getId())) {
				person.setId(size());
			}
			put(person.getId(), person);
		}
		return persons;
	}

	public Optional<Person> findById(int id) {
		return Optional.ofNullable(get(id));
	}

	public Optional<Person> findByName(String name) {
		for(Entry<Integer, Person> e: entrySet()) {
			if(e.getValue().getName().equals(name)) {
				return Optional.of(e.getValue());
			}
		}
		return Optional.empty();
	}

	public List<Person> findAll() {
		return values().stream().toList();
	}

	public boolean doesExistsById(int id) {
		return containsKey(id);
	}

}
