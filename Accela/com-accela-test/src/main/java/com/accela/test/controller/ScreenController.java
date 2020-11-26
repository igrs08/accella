package com.accela.test.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.dtoservice.AddressDTO;
import com.accela.test.dtoservice.PersonDTO;
import com.accela.test.manager.IPersonBusinessManager;

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

		clearConsole();

		System.out.println("(1) Create");
		System.out.println("(2) Search");
		System.out.println("(3) List Persons");

		System.out.print("What would you like to do? ");
		String option = console.readLine();

		return option;

	}

	private void listPersons(List<PersonDTO> persons, boolean clear) {

		logger.debug("Listing persons!");

		if (clear) {
			clearConsole();
		}

		if (persons.isEmpty()) {

			System.out.println("There is no any person at the moment!\n");
			System.out.println("(1) Create Person (2) Search Person (Anything)To return to the Initial Screen");

			System.out.print("What would you like to do? : ");

			String option = console.readLine();
			
			System.out.println("\n");

			switch (option) {

			case "1": // Create Person

				createNewPerson();
				break;

			case "2": // Search Person

				search();
				break;

			default:

				start();
				break;
			}

		} else {

			System.out.println("Id  " + "\tName " + "\tSurname");
			for (PersonDTO person : persons) {

				System.out.println(person.getId() + "\t" + person.getName() + " \t" + person.getSurName());

			}
			
			System.out.println("Total of Persons: " + persons.size());
			
			
			System.out.println("\n");
			System.out.println("(1) Delete Person (2) Edit Person (Anything) To return to the Initial Screen");
			System.out.print("What would you like to do? : ");
			String option = console.readLine();

			switch (option) {

			case "1": // Delete

				logger.debug("Case 1: Deleting...");

				deletePerson();

				break;
			case "2": // Edit

				logger.debug("Editing...");

				editPerson(persons);

				break;

			default:

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

			for (PersonDTO person : persons) {

				if (person.getId() == Integer.valueOf(id)) {

					personEdit = person;

					break;
				}

			}

			while (true) {

				if (!ObjectUtils.isEmpty(personEdit)) {

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

					System.out.print("Edit the Address? (Y)Yes (N)No :");
					Boolean editAddress = console.readLine().equalsIgnoreCase("Y") ? true : false;

					if (editAddress) {

						editAddress(personEdit, true);

					}

					personBusinessManager.save(personEdit);

					listPersons(personBusinessManager.findAll(), !editAddress);

					break;

				} else {

					// call the method again so the user can type an valid id
					System.out.println("Person id =" + id + " not found!");
					editPerson(persons);

				}

			}

		} catch (NumberFormatException e) {

			logger.debug(e.getMessage());

			// call the method again so the user can type an valid id
			editPerson(persons);

		}
		
	}

	private void editAddress(PersonDTO person, Boolean clear) {

		logger.debug("Listing Address(es)!");

		if (clear) {

			clearConsole();

		}

		if (ObjectUtils.isEmpty(person.getAddresses())) {

			System.out.println("There is no any Address for this person at the moment...");
			System.out.println("");
			System.out.println("(1) Add Address (Anything) To list persons");

			System.out.print("What would you like to do? ");
			String option = console.readLine();

			switch (option) {

			case "1":

				logger.debug("Case 1: Creating...");
				fillAddress(person);
				System.out.println("Address created with success!");
				return;

			default:

				return;

			}

		} else {

			// Header
			System.out.println("Id" + "\tStreet" + "\tCity" + "\tState" + "\tPostal Code");

			for (AddressDTO address : person.getAddresses()) {

				System.out.println(address.getId() + "\t" + address.getStreet() + "\t" + address.getCity() + "\t"
						+ address.getState() + "\t" + address.getPostalCode());

			}

			System.out.println("(1) Delete address (2) Update address (Anything) To return to the initial screen");

		}

		System.out.print("What would you like to do? ");
		String option = console.readLine();

		switch (option) {

		case "1": // Delete

			logger.debug("Case 1: Deleting...");

			deleteAddress(person);

			break;

		case "2": // Update

			logger.debug("Editing...");

			updateAddress(person);

			break;

		default:

			start();
			break;

		}

	}

	private void updateAddress(PersonDTO person) {

		while (true) {

			System.out.print("Which address you would like to update? Id : ");
			String id = console.readLine();

			try {

				if (ObjectUtils.isEmpty(id)) {

					System.out.println("Type a valid id...");
					continue;

				}

				List<AddressDTO> addresses = person.getAddresses();

				AddressDTO addressToBeUpdated = searchAddress(id, addresses);

				if (ObjectUtils.isEmpty(addressToBeUpdated)) {

					System.out.println("Address id not found...\n");
					continue;

				}

				validateAddress(addressToBeUpdated);

				System.out.println("Address updated with success!\n");

				break;

			} catch (NumberFormatException e) {

				logger.debug(e.getMessage());

			} catch (Exception e) {

				logger.debug(e.getMessage());

			}

		}

	}

	/**
	 * Validates the address(when the user is typing)
	 * 
	 * @param addressToBeUpdated
	 */
	private void validateAddress(AddressDTO addressToBeUpdated) {
		
		while (true) {
			
			System.out.println("\n");

			System.out.print("Enter the new street: ");
			String street = console.readLine();

			if (!ObjectUtils.isEmpty(street)) {

				addressToBeUpdated.setStreet(street);

			} else {

				System.out.println("Street cannot be empty... ");
				continue;

			}
			
			System.out.print("Enter the new city: ");
			String city = console.readLine();

			if (!ObjectUtils.isEmpty(city)) {

				addressToBeUpdated.setCity(city);

			} else {

				System.out.println("City cannot be empty... ");
				continue;

			}
			
			System.out.print("Enter the new state: ");
			String state = console.readLine();

			if (!ObjectUtils.isEmpty(state)) {

				addressToBeUpdated.setState(state);

			} else {

				System.out.println("State cannot be empty... ");
				continue;

			}
			
			System.out.print("Enter the new postal code: ");
			String postalCode = console.readLine();

			if (!ObjectUtils.isEmpty(postalCode)) {

				addressToBeUpdated.setPostalCode(postalCode);

			} else {

				System.out.println("Postal code cannot be empty... ");
				continue;

			}					
			
			System.out.println("\n");
			
			break;

		}
		
	}

	private AddressDTO searchAddress(String id, List<AddressDTO> addresses) {
		AddressDTO addressSearch = null;

		// FIXME: Change that to Java 8 Style
		for (AddressDTO address : addresses) {

			if (address.getId() == Integer.valueOf(id)) {

				addressSearch = address;
				break;

			}

		}
		return addressSearch;
	}

	private void deleteAddress(PersonDTO person) {

		while (true) {

			System.out.print("Which address you would like to delete? Id : ");
			String id = console.readLine();
			
			System.out.println("\n");

			try {

				if (ObjectUtils.isEmpty(id)) {

					System.out.println("Type a valid id...");
					continue;

				}

				List<AddressDTO> addresses = person.getAddresses();

				AddressDTO addressToBeRemoved = searchAddress(id, addresses);

				if (ObjectUtils.isEmpty(addressToBeRemoved)) {

					System.out.println("Address id not found...\n");
					continue;

				}

				person.getAddresses().remove(addressToBeRemoved);
				
				System.out.println("\n");

				System.out.println("Address deleted with success!\n");

				break;

			} catch (NumberFormatException e) {

				logger.debug(e.getMessage());

			} catch (Exception e) {

				logger.debug(e.getMessage());

			}

		}

	}

	private void deletePerson() {

		while (true) {

			System.out.print("Type the id of the Person you would like to delete: ");
			String id = console.readLine();
			
			System.out.println("\n");

			try {

				if (ObjectUtils.isEmpty(id)) {
					System.out.println("Type a valid id...");
					continue;

				}

				PersonDTO personFound = personBusinessManager.findPersonById(Integer.valueOf(id));

				if (ObjectUtils.isEmpty(personFound.getId())) {

					System.out.println("Person id = " + id + " not found!");
					continue;

				}

				personBusinessManager.delete(Integer.valueOf(id));
				System.out.println("Person deleted with success!");

				listPersons(personBusinessManager.findAll(), false);

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
	private void clearConsole() {

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

		case "2": // Search

			search();

			break;

		case "3": // List

			List<PersonDTO> personsList = personBusinessManager.findAll();

			listPersons(personsList, true);

			break;

		default:

			start();

			break;

		}

	}

	private void search() {

		while (true) {

			System.out.print("Type name or surname: ");
			String data = console.readLine();

			if (!ObjectUtils.isEmpty(data)) {

				listPersons(personBusinessManager.findPerson(data), true);
				break;

			} else {

				continue;

			}

		}

	}

	private void createNewPerson() {

		logger.debug("Creating Person(s)");

		Boolean createNew = true;
		Boolean newObject = true;
		PersonDTO person = null;
		String name = null;
		String surName = null;
		List<PersonDTO> persons = new ArrayList<PersonDTO>();

		validatePerson(createNew, newObject, person, name, surName, persons);
	}

	/**
	 * Validate Person data(When user is typing the data)
	 * 
	 * 
	 * @param createNew
	 * @param newObject
	 * @param person
	 * @param name
	 * @param surName
	 * @param persons
	 */
	private void validatePerson(Boolean createNew, Boolean newObject, PersonDTO person, String name, String surName, List<PersonDTO> persons) {
		
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

			if (ObjectUtils.isEmpty(person.getAddresses())) {

				person.setAddresses(new ArrayList<AddressDTO>());

				fillAddress(person);

			}

			newObject = true;// A new Person will be created on the next iteration of the loop

			// Add to list of created persons
			persons.add(person);

			System.out.print("(Y) To create a new Person (Anything) To return to the Initial Screen: ");
			createNew = console.readLine().equalsIgnoreCase("Y") ? true : false;
			
			System.out.println("\n");

			if (!createNew) {

				// Call to persist the list of created persons
				personBusinessManager.saveAll(persons);

				start();

			}

		}
		
	}

	private void fillAddress(PersonDTO person) {

		logger.debug("filling the adress...");

		AddressDTO address = null;
		Boolean newAddress = true;
		String street = null;
		String city = null;
		String state = null;
		String postalCode = null;
		Boolean createNewAddress = true;

		while (createNewAddress) {
			
			System.out.println("\n");

			if (newAddress) {

				address = new AddressDTO();

			}

			// Set Street
			if (ObjectUtils.isEmpty(address.getStreet())) {

				System.out.print("Enter the street address: ");
				street = console.readLine();
			}

			if (!ObjectUtils.isEmpty(street)) {

				address.setStreet(street);

			} else {

				newAddress = false;
				continue;

			}

			// Set City
			if (ObjectUtils.isEmpty(address.getCity())) {

				System.out.print("Enter the city address: ");
				city = console.readLine();
			}

			if (!ObjectUtils.isEmpty(city)) {

				address.setCity(city);

			} else {

				newAddress = false;
				continue;

			}

			// Set State
			if (ObjectUtils.isEmpty(address.getState())) {

				System.out.print("Enter the state address: ");
				state = console.readLine();
			}

			if (!ObjectUtils.isEmpty(state)) {

				address.setState(city);

			} else {

				newAddress = false;
				continue;

			}

			// Set Postal code
			if (ObjectUtils.isEmpty(address.getPostalCode())) {

				System.out.print("Enter the postal code: ");
				postalCode = console.readLine();
			}

			if (!ObjectUtils.isEmpty(postalCode)) {

				address.setPostalCode(postalCode);

			} else {

				newAddress = false;
				continue;

			}

			newAddress = true;
			street = null;
			city = null;
			state = null;
			postalCode = null;
			
			System.out.println("\n");

			person.getAddresses().add(address);

			System.out.print("Add more addresses? (Y) Yes (N) No : ");
			createNewAddress = console.readLine().equalsIgnoreCase("Y") ? true : false;

		}

		logger.debug("Leaving filling adress...");

	}

}
