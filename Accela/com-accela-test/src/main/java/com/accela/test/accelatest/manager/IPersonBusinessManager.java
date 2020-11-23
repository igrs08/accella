package com.accela.test.accelatest.manager;

import java.util.List;

import com.accela.test.accelatest.dtoservice.PersonDTO;

public interface IPersonBusinessManager {
	
	public void saveAll(List<PersonDTO> persons);

	public List<PersonDTO> findAll();

}
