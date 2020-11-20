package com.accela.test.accelatest.service;

import org.springframework.stereotype.Service;

import com.accela.test.accelatest.entity.PersonTableEntity;

@Service
public interface IPersonBusinessService {
	
	public void save(PersonTableEntity person);
	

}
