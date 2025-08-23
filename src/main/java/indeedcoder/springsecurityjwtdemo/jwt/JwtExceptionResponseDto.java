package indeedcoder.springsecurityjwtdemo.jwt;

import java.time.Instant;

import lombok.Getter;

@Getter
public class JwtExceptionResponseDto {

	// Java 8 date/time type `java.time.Instant` not supported by default when using
	// `com.fasterxml.jackson.databind.ObjectMapper` for converting to JSON-String
	private String timestap;
	private int status;
	private String error;
	private String message;
	private String path;

	public JwtExceptionResponseDto(int status, String error, String message, String path) {
		this.timestap = Instant.now().toString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
