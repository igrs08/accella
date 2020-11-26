package com.accela.test.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accela.test.controller.ScreenController;
import com.accela.test.dtoservice.PersonDTO;
import com.accela.test.dtoservice.PersonDTOService;
import com.accela.test.entity.AddressTableEntity;
import com.accela.test.entity.PersonTableEntity;
import com.accela.test.service.IPersonBusinessService;

/**
 * @author Igor
 *
 */
@Component
public class PersonBusinessManagerImpl implements IPersonBusinessManager{
	
	private Logger logger = LoggerFactory.getLogger(PersonBusinessManagerImpl.class);
	
	@Autowired
	private IPersonBusinessService personBusinessService;
	
	@Autowired	
	private PersonDTOService personDTOService;
	
	public void saveAll(List<PersonDTO> persons) {
		
		logger.debug("Salving Person(s)");
		
		List<PersonTableEntity> personEntities = new ArrayList<PersonTableEntity>();
		
		try {
			
			logger.debug("These are the people before converting : " + persons.get(0).getName() +" " + persons.get(0).getSurName());
			
			for(PersonDTO person : persons) {
				
				personEntities.add(personDTOService.convertDtoToEntity(person));
				
			}			
		
			logger.debug("These are the people after converting : " + personEntities.get(0).getName() +" " + personEntities.get(0).getSurName());
					
			
			for(PersonTableEntity personTable : personEntities) {
				
				System.out.println(personTable.getName());
				
				for(AddressTableEntity address : personTable.getAddresses()) {
					
					System.out.println("This is the address" + address.getStreet());
					
				}
			}
			
			
			personBusinessService.saveAll(personEntities);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}		
		
	}

	@Override
	public List<PersonDTO> findAll() {
		
		List<PersonTableEntity> persons = personBusinessService.findAll();
		
		return personDTOService.convertEntityToDto(persons);		
				
	}

	@Override
	public void delete(Integer id) {
		
		personBusinessService.delete(id);
		
	}

	@Override
	public void save(PersonDTO personDto) {
		
		PersonTableEntity person = personDTOService.convertDtoToEntity(personDto);
		
		personBusinessService.save(person);
		
	}

	@Override
	public PersonDTO findPersonById(Integer id) {
					
		Optional<PersonTableEntity> personOptional = personBusinessService.findById(id);
		
		if (personOptional.isPresent()) {
			
			return personDTOService.convertEntityToDto(personOptional.get());
			
		}else {
			
			return new PersonDTO();
			
		}
		
	}

	@Override
	public List<PersonDTO> findPerson(String data) {
		
		List<PersonTableEntity> listPerson = personBusinessService.findPerson(data);
		
		if(!listPerson.isEmpty()) {
			return personDTOService.convertEntityToDto(listPerson);
		}
				
		return new ArrayList<PersonDTO>();
		
	}

}
