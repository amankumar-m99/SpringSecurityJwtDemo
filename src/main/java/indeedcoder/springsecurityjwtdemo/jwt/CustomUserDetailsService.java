package indeedcoder.springsecurityjwtdemo.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.Person;
import indeedcoder.springsecurityjwtdemo.repository.PersonRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private PersonRepository repository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public CustomUserDetailsService(PersonRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> optional = repository.findByName(username);
		if (optional.isPresent()) {
			Person person = optional.get();
			// password("{noop}" + person.getName().toLowerCase())
			String password = person.getName().toLowerCase();
			return User.builder().username(person.getName()).password(passwordEncoder.encode(password)).roles((person.getId()%2==0)?"ADMIN":"USER")
					.build();
		}
		throw new UsernameNotFoundException(username);
	}
}
