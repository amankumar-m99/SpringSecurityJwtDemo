package indeedcoder.springsecurityjwtdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.User;
import indeedcoder.springsecurityjwtdemo.repository.UserRepository;
import io.jsonwebtoken.lang.Collections;

@Service
public class UserService {

	private UserRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public User create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public User findById(int id) {
		return repository.findById(id).orElse(new User(-1, "Not-found-by-id-"+id, "", Collections.emptyList()));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User update(User user) {
		if(repository.doesExistsById(user.getId())) {
			return repository.save(user);
		}
		return new User(-1, "Not-found", "", Collections.emptyList());
	}
}
