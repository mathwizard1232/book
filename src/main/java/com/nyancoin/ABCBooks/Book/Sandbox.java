package com.nyancoin.ABCBooks.Book;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Sandbox {
	public static void main(String args[]) throws Exception {
		final ApplicationContext app = SpringApplication.run(Sandbox.class, args);
		System.out.println("Greetings from ABC Books! Welcome to the sandbox!");
//		final Database d = app.getBean(Database.class);
//		System.out.println("Retrieved Database instance.");
		System.exit(0);
	}
}