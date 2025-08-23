package indeedcoder.springsecurityjwtdemo.dto;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {

	private String username;
	private String password;
	private List<String> authorities;
	private String fetchedBy;
	private String message;

	public AdminResponseDto(UserDetails userDetails, String fetchedBy) {
		username = userDetails.getUsername();
		password = userDetails.getPassword();
		authorities = userDetails.getAuthorities().stream().map(a-> a.getAuthority()).toList();
		this.fetchedBy = fetchedBy;
		this.message = "This is admin endpoint";
	}
}
