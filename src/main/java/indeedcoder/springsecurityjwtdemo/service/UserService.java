package indeedcoder.springsecurityjwtdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.User;
import indeedcoder.springsecurityjwtdemo.repository.UserRepository;

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
		return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No user found with id " + id));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User update(User user) {
		if (!repository.doesExistsById(user.getId())) {
			throw new UsernameNotFoundException("No user found with id " + user.getId());
		}
		return repository.save(user);
	}
}
