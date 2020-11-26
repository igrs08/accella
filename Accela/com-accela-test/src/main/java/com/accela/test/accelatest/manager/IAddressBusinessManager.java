package com.accela.test.accelatest.manager;

import java.util.List;

import com.accela.test.accelatest.dtoservice.AddressDTO;
import com.accela.test.accelatest.dtoservice.PersonDTO;

public interface IAddressBusinessManager {
	
	public void save(List<AddressDTO> adressess);

	public List<AddressDTO> findAll(Integer personId);

	public void delete(Integer id);
	
}
