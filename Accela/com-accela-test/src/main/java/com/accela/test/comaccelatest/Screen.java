package com.accela.test.comaccelatest;

import java.io.Console;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.comaccelatest.entity.Person;

@Component
public class Screen implements IScreen {

	private Console console = System.console();

	private String displayOptionsScreen() {

		System.out.println("\n(1) Create a new User. ");
		System.out.println("(2) Search for a User. ");
		System.out.println("(3) Delete a User.");
		System.out.println("(4) Edit a User.\n");

		System.out.print("What would you like to do?: ");
		String option = console.readLine();

		return option;

	}

	public void start() {

		String option = displayOptionsScreen();

		Boolean createNew = true;

		switch (option) {

		case "1":

			boolean newObject = true;
			Person person = null;
			String name = null;
			String surName = null;

			while (createNew) {

				if (newObject) {

					person = new Person();

				}

				if (ObjectUtils.isEmpty(person.getName())) {

					System.out.print("Enter the first name: ");
					name = console.readLine();
				}

				if (!ObjectUtils.isEmpty(name)) {

					person.setName(name);

				} else {

					newObject = false;
					continue;

				}

				if (ObjectUtils.isEmpty(person.getSurName())) {

					System.out.print("Enter the surname: ");
					surName = console.readLine();

				}

				if (!ObjectUtils.isEmpty(surName)) {

					person.setSurName(surName);

				} else {

					newObject = false;
					continue;

				}

				newObject = true;

				System.out.print("(Y) To create a new User (N) Return to the Initial Screen: ");
				createNew = console.readLine().equalsIgnoreCase("Y") ? true : false;

				if (!createNew) {

					displayOptionsScreen();

				}

			}

			break;

		case "2":

			break;

		default:

			break;

		}

	}	

}
