package indeedcoder.springsecurityjwtdemo.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException {
		int status = HttpServletResponse.SC_UNAUTHORIZED;
		String message = request.getAttribute("jwt_error").toString();
		if (message == null) {
			message = exception.getMessage();
		}
		String path = request.getAttribute("jwt_req_path").toString();
		if(path == null) {
			path = request.getRequestURI();
		}
		response.setStatus(status);
		response.setContentType("application/json");
		JwtExceptionResponseDto responseBody = new JwtExceptionResponseDto(status, "Unauthorized", message, path);
		response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        response.getWriter().flush();
	}
}
