package indeedcoder.springsecurityjwtdemo.jwt;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException {
		int status = HttpServletResponse.SC_FORBIDDEN;
		response.setStatus(status);
		response.setContentType("application/json");
		JwtExceptionResponseDto responseBody = new JwtExceptionResponseDto(status, "Forbidden",
				exception.getMessage(), request.getRequestURI());
		response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        response.getWriter().flush();
	}
}
