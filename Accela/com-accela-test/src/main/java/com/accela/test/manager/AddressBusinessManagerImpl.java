package com.accela.test.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accela.test.controller.ScreenController;
import com.accela.test.dtoservice.AddressDTO;
import com.accela.test.dtoservice.AddressDTOService;
import com.accela.test.dtoservice.PersonDTO;
import com.accela.test.dtoservice.PersonDTOService;
import com.accela.test.entity.AddressTableEntity;
import com.accela.test.entity.PersonTableEntity;
import com.accela.test.repository.IAddressRepository;
import com.accela.test.repository.IPersonRepository;
import com.accela.test.service.IAddressBusinessService;
import com.accela.test.service.IPersonBusinessService;

/**
 * @author Igor
 *
 */
@Component
public class AddressBusinessManagerImpl implements IAddressBusinessManager {

	private Logger logger = LoggerFactory.getLogger(AddressBusinessManagerImpl.class);

	@Autowired
	private IAddressBusinessService addressBusinessService;

	@Autowired
	private AddressDTOService addressDTOService;	
	
	@Override
	public void save(List<AddressDTO> adressess) {
		logger.debug("Salving Address(es)");

		List<AddressTableEntity> addressEntities = new ArrayList<AddressTableEntity>();

		try {

			for (AddressDTO address : adressess) {

				addressEntities.add(addressDTOService.convertDtoToEntity(address));

			}

			addressBusinessService.save(addressEntities);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public List<AddressDTO> findAll(Integer personId) {
		
		logger.debug("Findind Address(es)");
		
		List<AddressTableEntity> addressEntities = addressBusinessService.findAll(personId);
		
		return addressDTOService.convertEntityToDto(addressEntities);
		
	}

	@Override
	public void delete(Integer id) {
		
		addressBusinessService.delete(id);
		
	}

}
