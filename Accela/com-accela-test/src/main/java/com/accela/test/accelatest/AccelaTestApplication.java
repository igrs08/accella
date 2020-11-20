package com.accela.test.accelatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accela.test.accelatest.controller.IScreenController;

@SpringBootApplication
public class AccelaTestApplication implements ApplicationRunner {
	
	@Autowired
	private IScreenController screen;

	public static void main(String[] args) {
		SpringApplication.run(AccelaTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		screen.start();		
		
	}

	
}
