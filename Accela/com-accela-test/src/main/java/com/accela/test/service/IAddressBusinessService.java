package com.accela.test.service;

import java.util.List;
import java.util.Optional;

import com.accela.test.dtoservice.AddressDTO;
import com.accela.test.entity.AddressTableEntity;
import com.accela.test.entity.PersonTableEntity;


/**
 * @author Igor
 *
 */
public interface IAddressBusinessService {
	
	public void save(Iterable<AddressTableEntity> iterable);

	public List<AddressTableEntity> findAll(Integer personId);

	public void delete(Integer id);

}
