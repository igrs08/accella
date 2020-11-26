package com.accela.test.accelatest.dtoservice;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.accelatest.entity.AddressTableEntity;
import com.accela.test.accelatest.entity.PersonTableEntity;

/**
 * @author Igor
 *
 */
/**
 * @author Igor
 *
 */
@Component
public class AddressDTOService extends BaseDTOService<AddressTableEntity, AddressDTO> {

	@Override
	public AddressDTO convertEntityToDto(AddressTableEntity address) {
		
		if (Objects.isNull(address.getId())) {
			throw new DTOException("Address does not have a valid ID");
		}

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setCity(!ObjectUtils.isEmpty(address.getCity()) ? address.getCity() : "");		
		addressDTO.setStreet(!ObjectUtils.isEmpty(address.getStreet()) ? address.getStreet() : "");
		addressDTO.setState(!ObjectUtils.isEmpty(address.getState()) ? address.getState() : "");
		addressDTO.setPostalCode(!ObjectUtils.isEmpty(address.getPostalCode()) ? address.getPostalCode() : "");

		return addressDTO;
	}

	@Override
	public AddressTableEntity convertDtoToEntity(AddressDTO addressDTO) {
						
		AddressTableEntity address = new AddressTableEntity();
		
		if (Objects.nonNull(addressDTO.getId())) {
			
			address.setId(addressDTO.getId());
			
		}
		
		address.setStreet(!ObjectUtils.isEmpty(addressDTO.getStreet()) ? addressDTO.getStreet() : "");
		address.setCity(!ObjectUtils.isEmpty(addressDTO.getCity()) ? addressDTO.getCity() : "");
		address.setState(!ObjectUtils.isEmpty(addressDTO.getState()) ? addressDTO.getState() : "");
		address.setPostalCode(!ObjectUtils.isEmpty(addressDTO.getPostalCode()) ? addressDTO.getPostalCode() : "");
		
		return address;

	}

}