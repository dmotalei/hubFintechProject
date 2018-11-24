package br.com.hubfintech.projeto.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="br.com.hubfintech.projeto")
public class Main {

	public static void main(String[] args) throws Exception {
		
		try {
			 SpringApplication.run(Main.class, args);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
    }
}
