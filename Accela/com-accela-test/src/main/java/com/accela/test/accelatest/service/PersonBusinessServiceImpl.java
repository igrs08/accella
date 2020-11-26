package com.accela.test.accelatest.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.test.accelatest.entity.PersonTableEntity;
import com.accela.test.accelatest.repository.IPersonRepository;

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
		
		logger.info("Findind Person(s)");
		
		return personRepository.findAllFetchAddress();
		
	}

	@Override
	public void delete(Integer id) {
		
		logger.info("Deleting person id = " + id);
		
		personRepository.deleteById(id);
		
	}

	@Override
	public void save(PersonTableEntity person) {
			
		personRepository.save(person);
		
	}

	@Override
	public Optional<PersonTableEntity> findById(Integer id){				
					
		return personRepository.findById(id);
	}

	@Override
	public List<PersonTableEntity> findPerson(String data) {
		
		return personRepository.findPerson("%" +data + "%");
	}	

}
