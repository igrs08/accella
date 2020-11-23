package com.accela.test.accelatest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.test.accelatest.IPersonRepository;
import com.accela.test.accelatest.entity.PersonTableEntity;

/**
 * @author Igor
 *
 */
@Service
public class PersonBusinessServiceImpl implements IPersonBusinessService {
	
	private Logger logger = LoggerFactory.getLogger(PersonBusinessServiceImpl.class);
	
	@Autowired
	private IPersonRepository personRepository;
	
	public void saveAll(Iterable<PersonTableEntity> persons) {
		
		logger.info("Calling the repository to save Person(s)");
		
		personRepository.saveAll(persons);
		
	}

	@Override
	public List<PersonTableEntity> findAll() {
		
		return personRepository.findAll();
		
	}	

}
