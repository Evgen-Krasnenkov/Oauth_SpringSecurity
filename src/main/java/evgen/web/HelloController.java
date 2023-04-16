package evgen.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello, " + "!";
	}

	@GetMapping("/")
	public String getHome(){
		return "This is a home page";
	}
}
