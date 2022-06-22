package br.com.matheus.FirstSteps;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatusController {

	private final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/getStatus")
	public Status GetStatus(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Status(counter.incrementAndGet(), String.format(template, name));
	}
}
