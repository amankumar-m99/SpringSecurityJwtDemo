package indeedcoder.springsecurityjwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class HomeController {

	@GetMapping("/home")
	public String homePage() {
		return "This is home page";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "This is about page";
	}
}
