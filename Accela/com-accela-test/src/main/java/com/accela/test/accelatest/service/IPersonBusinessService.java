package com.accela.test.accelatest.service;

import com.accela.test.accelatest.entity.PersonTableEntity;


/**
 * @author Igor
 *
 */
public interface IPersonBusinessService {
	
	public void saveAll(Iterable<PersonTableEntity> iterable);	

}
