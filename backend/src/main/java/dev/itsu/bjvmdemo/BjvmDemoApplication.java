package dev.itsu.bjvmdemo;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BjvmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BjvmDemoApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("JST"));
	}

}
