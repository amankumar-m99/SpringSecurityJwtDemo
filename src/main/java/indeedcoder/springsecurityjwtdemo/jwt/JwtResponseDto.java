package indeedcoder.springsecurityjwtdemo.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponseDto {

	private String token;
	private String type;
	private String expiration;
}
