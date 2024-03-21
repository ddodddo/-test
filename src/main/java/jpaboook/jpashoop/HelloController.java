package jpaboook.jpashoop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hellos")
	public String hello() {
		return "home";
	}
}
