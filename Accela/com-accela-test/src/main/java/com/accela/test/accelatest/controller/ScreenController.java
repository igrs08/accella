package com.accela.test.accelatest.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.accelatest.entity.Person;

/**
 * @author Igor
 *
 */
@Component
public class ScreenController implements IScreenController {

	private Console console = System.console();

	/**
	 * Show the initial screen with options
	 * 
	 * @return
	 */
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

		switch (option) {

		case "1":

			Boolean createNew = true; 
			boolean newObject = true;
			Person person = null;
			String name = null;
			String surName = null;
			List<Person> persons = new ArrayList<Person>();

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
				
				//Add to list of created persons
				persons.add(person);

				System.out.print("(Y) To create a new User (N) Return to the Initial Screen: ");
				createNew = console.readLine().equalsIgnoreCase("Y") ? true : false;

				if (!createNew) {

					displayOptionsScreen();

				}

			}
			
			//Call to persist the list of created persons

			break;

		case "2":

			break;

		default:

			break;

		}

	}	

}
