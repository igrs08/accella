package com.accela.test.accelatest.controller;

import java.io.Console;
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
	 * Display the initial screen with options
	 * 
	 * @return
	 */
	private String displayOptionsScreen() {
		
		clear();

		System.out.println("(1) Create a new Person");
		System.out.println("(2) Search for a Person");
		System.out.println("(3) List Persons");		

		System.out.print("What would you like to do? ");
		String option = console.readLine();

		return option;

	}	

	private void listPersons(List <PersonDTO> persons) {
		
		logger.debug("Listing persons!");	
		
		clear();
		
		if(persons.isEmpty()) {
			
			System.out.println("There is no any person at the moment!\n");		
			System.out.println("(1) Create new person (Anything) To return to the Initial Screen");
			
			System.out.print("What would you like to do? : ");
			
			String option = console.readLine();
			
			switch (option) {

				case "1": // Create Person
		
					createNewPerson();		
					break;
		
				default : //Search
					
					start();		
					break;
			}
			
		}else {
			
			System.out.println("id  " +"|"+ "\tName " +"|"+ "\t\tSurname");
			for(PersonDTO person : persons) {
				
				System.out.println(person.getId() + "\t" +person.getName() + " \t\t" + person.getSurName());
				
			}		
			
			System.out.println("");			
			System.out.println("(1) Delete (2) Edit (Anything) To return to the Initial Screen");
			System.out.print("What would you like to do? : ");
			String option = console.readLine();
			
			switch (option) {

				case "1": // Delete
					
					logger.debug("Case 1: Deleting...");	
					
					deletePerson();
									
					break;
				case "2": //Edit					
					
					logger.debug("Editing...");
					
					editPerson(persons);							
					
					break;	
					
				default :
					
					start();								
					break;						
		
			}
		
		}			
		
	}

	private void editPerson(List<PersonDTO> persons) {
		
		System.out.print("Which person would you like to edit? Type the id: ");
		String id = console.readLine();		
						
		try {
			
			PersonDTO personEdit = null;
			
			for(PersonDTO person :persons) {
				
				if(person.getId() == Integer.valueOf(id)) {
												
					personEdit = person;
					
					break;							
				}
				
			}
			
			while(true) {
				
				if(!ObjectUtils.isEmpty(personEdit)) {
					
					System.out.print("Enter the new first name: ");
					String newName = console.readLine();
					
					if (!ObjectUtils.isEmpty(newName)) {

						personEdit.setName(newName);

					} else {
						
						System.out.println("Name cannot be empty... ");
						continue;

					}
					
					System.out.print("Enter the new second name: ");
					String newSurName = console.readLine();		
					
					if (!ObjectUtils.isEmpty(newSurName)) {

						personEdit.setSurName(newSurName);

					} else {
						
						System.out.println("Surname cannot be empty... ");
						continue;

					}			
					
					personBusinessManager.save(personEdit);
					
					listPersons(personBusinessManager.findAll());
					
					break;
					
				}else {
					
					//call the method again so the user can type an valid id
					System.out.println("Person id =" + id + " not found!");
					editPerson(persons);
					
				}
			
			}
			
		} catch (NumberFormatException e) {
			
			logger.debug(e.getMessage());
			
			//call the method again so the user can type an valid id
			editPerson(persons);			
			
		}
	}

	private void deletePerson() {
				
		while(true) {
			
			System.out.print("Type the id of the Person you would like to delete: ");
			String id = console.readLine();
			
			try {
				
				if(ObjectUtils.isEmpty(id)) {
					System.out.println("Type a valid id...");
					continue;
					
				}										
							
				PersonDTO personFound = personBusinessManager.findPersonById(Integer.valueOf(id));
				
				if(ObjectUtils.isEmpty(personFound.getId())) {
					
					System.out.println("Person id = " + id + " not found!");					
					continue;
					
				}
								
				personBusinessManager.delete(Integer.valueOf(id));				
				logger.debug("Person deleted with success!");	
				
				listPersons(personBusinessManager.findAll());		
				
				break;

				
			} catch (NumberFormatException e) {
				
				logger.debug(e.getMessage());
				System.out.println("Person id must be a number!");				
				
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
	
				createNewPerson();
	
				break;
	
			case "2": //Search
	
				break;
				
			case "3": //List
				
				List<PersonDTO> personsList = personBusinessManager.findAll();
				
				listPersons(personsList);			
				
				break;
	
			default:
				
				start();
	
				break;

		}

	}

	private void createNewPerson() {
		
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

			System.out.print("(Y) To create a new Person (Anything) To return to the Initial Screen: ");
			createNew = console.readLine().equalsIgnoreCase("Y") ? true : false;

			if (!createNew) {

				// Call to persist the list of created persons
				personBusinessManager.saveAll(persons);

				start();

			}

		}
	}

}
