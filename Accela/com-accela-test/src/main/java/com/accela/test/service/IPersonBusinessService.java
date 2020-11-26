package com.accela.test.service;

import java.util.List;
import java.util.Optional;

import com.accela.test.entity.PersonTableEntity;


/**
 * @author Igor
 *
 */
public interface IPersonBusinessService {
	
	public void saveAll(Iterable<PersonTableEntity> iterable);

	public List<PersonTableEntity> findAll();

	public void delete(Integer id);

	public void save(PersonTableEntity person);

	public Optional<PersonTableEntity> findById(Integer id);

	public List<PersonTableEntity> findPerson(String data);

}
