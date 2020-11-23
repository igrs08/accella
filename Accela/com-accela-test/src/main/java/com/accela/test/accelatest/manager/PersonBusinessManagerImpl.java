package com.accela.test.accelatest.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accela.test.accelatest.controller.ScreenController;
import com.accela.test.accelatest.dtoservice.PersonDTO;
import com.accela.test.accelatest.dtoservice.PersonDTOService;
import com.accela.test.accelatest.entity.PersonTableEntity;
import com.accela.test.accelatest.service.IPersonBusinessService;

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
		
		logger.info("Salving Person(s)");
		
		List<PersonTableEntity> personEntities = new ArrayList<PersonTableEntity>();
		
		try {
			
			logger.info("These are the people before converting : " + persons.get(0).getName() +" " + persons.get(0).getSurName());
			
			for(PersonDTO person : persons) {
				
				personEntities.add(personDTOService.convertDtoToEntity(person));
				
			}			
		
			logger.info("These are the people after converting : " + personEntities.get(0).getName() +" " + personEntities.get(0).getSurName());
					
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

}
