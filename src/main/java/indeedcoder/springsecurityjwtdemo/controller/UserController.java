package indeedcoder.springsecurityjwtdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indeedcoder.springsecurityjwtdemo.entity.User;
import indeedcoder.springsecurityjwtdemo.service.UserService;

@RequestMapping("/person")
@RestController
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/person")
	public User create(User user) {
		return service.create(user);
	}

	@GetMapping("/person/{id}")
	public User findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/persons")
	public List<User> findAll() {
		return service.findAll();
	}

	@PutMapping("/person")
	public User update(User user) {
		return service.update(user);
	}
}
