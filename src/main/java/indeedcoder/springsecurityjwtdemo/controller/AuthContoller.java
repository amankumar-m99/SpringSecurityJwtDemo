package indeedcoder.springsecurityjwtdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import indeedcoder.springsecurityjwtdemo.dto.AdminResponseDto;
import indeedcoder.springsecurityjwtdemo.dto.LoginRequestDto;
import indeedcoder.springsecurityjwtdemo.jwt.CustomUserDetailsService;
import indeedcoder.springsecurityjwtdemo.jwt.JwtUtils;

@RestController
public class AuthContoller {

	private AuthenticationManager authenticationManager;

	private CustomUserDetailsService userDetailsService;

	private JwtUtils jwtUtils;

	@Autowired
	public AuthContoller(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService,
			JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {
		String token = null;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
			token = jwtUtils.generateToken(user);
		} catch (Exception e) {
			// handles BadCredentialsException as well
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
	@GetMapping("/admin-annotation")
	public ResponseEntity<AdminResponseDto> adminWithAnnotation(@AuthenticationPrincipal UserDetails userDetails) {
		AdminResponseDto dto = new AdminResponseDto(userDetails, "@AuthenticationPrincipal annotation");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/admin-context")
	public ResponseEntity<?> adminWithContext() {
		String currentUser = "";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userdetails) {
            	AdminResponseDto response = new AdminResponseDto(userdetails, "SecurityContextHolder");
        		return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
            	currentUser = principal.toString();
            }
        }
        String dto = "This is admin endpoint. Current user fetched by SecurityContextHolder is " + currentUser;
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
