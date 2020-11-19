package com.accela.test.comaccelatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccelaTestApplication implements ApplicationRunner {
	
	@Autowired
	private IScreen screen;

	public static void main(String[] args) {
		SpringApplication.run(AccelaTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		screen.start();		
		
	}

	
}
