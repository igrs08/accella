package com.accela.test.accelatest.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.accelatest.dtoservice.PersonDTO;
import com.accela.test.accelatest.manager.IPersonBusinessManager;

/**
 * @author Igor
 *
 */
@Component
public class ScreenController implements IScreenController {

	private Logger logger = LoggerFactory.getLogger(ScreenController.class);

	private Console console = System.console();

	@Autowired
	private IPersonBusinessManager personBusinessManager;

	/**
	 * Show the initial screen with options
	 * 
	 * @return
	 */
	private String displayOptionsScreen() {
		
		clear();

		System.out.println("(1) Create a new User");
		System.out.println("(2) Search for a User");
		System.out.println("(3) List Users");		

		System.out.print("What would you like to do? ");
		String option = console.readLine();

		return option;

	}
	
	/**
	 * List all the persons
	 * 
	 * @param persons
	 */
	private void displayListScreen(List <PersonDTO> persons) {
		
		clear();
		
		if(persons.isEmpty()) {
			
			System.out.println("There is no any person at the moment!");			
			
		}else {
			
			System.out.println("id  " +"|"+ "\tName " +"|"+ "\tSurname");
			for(PersonDTO person : persons) {
				
				System.out.println(person.getId() + "\t" +person.getName() + " \t" + person.getSurName());
				
			}		
			
			System.out.println("");			
			System.out.println("(1) Delete a User (2) Edit a User (3) Return to the Initial Screen");
			System.out.print("What would you like to do? : ");
			String option = console.readLine();
			
			switch (option) {

			case "1": // Delete
				
				Boolean waitUserToTypeCorrectly = true;
				
				while(waitUserToTypeCorrectly) {
					
					System.out.print("Type the id of the user you would like to delete: ");
					String id = console.readLine();
					
					try {
						
						Integer.valueOf(id);
						waitUserToTypeCorrectly = false;	
						
						System.out.println("User deleted with success!");	
						
						//call find all
						//call displayListScreen
			
						
					} catch (NumberFormatException e) {
						
						System.out.println("User id must be a number!");				
						
					}
					
				}
								
				break;
			case "2": //Edit					
				
				break;	
				
			case "3":
				
				displayOptionsScreen();								
				break;		
				
			default:
			
				break;
				
			}			
			
		}	
		
		
	}
	

	/**
	 * Clears the console
	 * 
	 * 
	 */
	private void clear() {
		 System.out.print("\033[H\033[2J");  
		 System.out.flush();  
	}

	/**
	 * Entry method
	 * 
	 *
	 */
	public void start() {

		String option = displayOptionsScreen();

		switch (option) {

		case "1": // Create Person

			logger.info("Creating Person(s)");

			Boolean createNew = true;
			Boolean newObject = true;
			PersonDTO person = null;
			String name = null;
			String surName = null;
			List<PersonDTO> persons = new ArrayList<PersonDTO>();

			while (createNew) {

				if (newObject) {

					person = new PersonDTO();

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

				// Add to list of created persons
				persons.add(person);

				System.out.print("(Y) To create a new User (N) Return to the Initial Screen: ");
				createNew = console.readLine().equalsIgnoreCase("Y") ? true : false;

				if (!createNew) {

					// Call to persist the list of created persons
					personBusinessManager.saveAll(persons);

					start();

				}

			}

			break;

		case "2":

			break;
			
		case "3":
			
			List<PersonDTO> personsList = personBusinessManager.findAll();
			
			displayListScreen(personsList);			
			
			break;

		default:

			break;

		}

	}

}
