package indeedcoder.springsecurityjwtdemo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import indeedcoder.springsecurityjwtdemo.entity.User;

@Component
public class UserRepository extends HashMap<Integer, User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781631828228376594L;

	public User save(User user) {
		if (!containsKey(user.getId())) {
			user.setId(size());
		}
		put(user.getId(), user);
		return user;
	}

	public User[] saveAll(User... persons) {
		for (User user : persons) {
			if (!containsKey(user.getId())) {
				user.setId(size());
			}
			put(user.getId(), user);
		}
		return persons;
	}

	public Optional<User> findById(int id) {
		return Optional.ofNullable(get(id));
	}

	public Optional<User> findByName(String name) {
		for(Entry<Integer, User> e: entrySet()) {
			if(e.getValue().getName().equals(name)) {
				return Optional.of(e.getValue());
			}
		}
		return Optional.empty();
	}

	public List<User> findAll() {
		return values().stream().toList();
	}

	public boolean doesExistsById(int id) {
		return containsKey(id);
	}

}
