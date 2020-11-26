package com.accela.test.accelatest.service;

import java.util.List;
import java.util.Optional;

import com.accela.test.accelatest.dtoservice.AddressDTO;
import com.accela.test.accelatest.entity.AddressTableEntity;
import com.accela.test.accelatest.entity.PersonTableEntity;


/**
 * @author Igor
 *
 */
public interface IAddressBusinessService {
	
	public void save(Iterable<AddressTableEntity> iterable);

	public List<AddressTableEntity> findAll(Integer personId);

	public void delete(Integer id);

}
