package com.accela.test.accelatest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.test.accelatest.dtoservice.AddressDTO;
import com.accela.test.accelatest.entity.AddressTableEntity;
import com.accela.test.accelatest.repository.IAddressRepository;

@Service
public class AddressBusinessServiceImpl implements IAddressBusinessService {
	
	private Logger logger = LoggerFactory.getLogger(AddressBusinessServiceImpl.class);
	
	@Autowired
	private IAddressRepository addressRepository;

	@Override
	public void save(Iterable<AddressTableEntity> addresses) {
		
		logger.info("Calling the repository to save Person(s)");
		
		addressRepository.saveAll(addresses);

	}

	@Override
	public List<AddressTableEntity> findAll(Integer personId) {
		
		logger.info("Findind Address(s)");
		
		return addressRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		
		logger.info("Deleting address id = " + id);
		
		addressRepository.deleteById(id);

	}

}
