package indeedcoder.springsecurityjwtdemo.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import indeedcoder.springsecurityjwtdemo.entity.User;
import indeedcoder.springsecurityjwtdemo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = repository.findByName(username);
		if (optional.isPresent()) {
			User user = optional.get();
			// password("{noop}" + person.getName().toLowerCase())
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getName())
					.password(user.getPassword()).roles(user.getRoles().toArray(new String[0]))
					.build();
		}
		throw new UsernameNotFoundException(username);
	}
}
