package coding.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {
	@GetMapping("/join")
	public String joinPage() {
		return "join";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
