package indeedcoder.springsecurityjwtdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.Person;
import indeedcoder.springsecurityjwtdemo.repository.PersonRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> optional = repository.findByName(username);
		if (optional.isPresent()) {
			Person person = optional.get();
			return User.builder().username(person.getName()).password("{noop}" + person.getName().toLowerCase())
					.build();
		}
		throw new UsernameNotFoundException(username);
	}

}
