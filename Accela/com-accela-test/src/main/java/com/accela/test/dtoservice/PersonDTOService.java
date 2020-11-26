package com.accela.test.dtoservice;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.accela.test.entity.AddressTableEntity;
import com.accela.test.entity.PersonTableEntity;

/**
 * @author Igor
 *
 */
@Component
public class PersonDTOService extends BaseDTOService<PersonTableEntity, PersonDTO> {
		
	@Autowired	
	private AddressDTOService addressDTOService;

	@Override
	public PersonDTO convertEntityToDto(PersonTableEntity person) {
		
		if (Objects.isNull(person.getId())) {
			throw new DTOException("Person does not have a valid ID");
		}

		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setName(!ObjectUtils.isEmpty(person.getName()) ? person.getName() : "");
		personDTO.setSurName(!ObjectUtils.isEmpty(person.getSurName()) ? person.getSurName() : "");
		personDTO.setAddresses(new ArrayList<AddressDTO>());
		
		for(AddressTableEntity address : person.getAddresses()) {
			
			personDTO.getAddresses().add(addressDTOService.convertEntityToDto(address));			
			
		}

		return personDTO;
	}

	@Override
	public PersonTableEntity convertDtoToEntity(PersonDTO personDTO) {
						
		PersonTableEntity person = new PersonTableEntity();
		
		person.setAddresses(new ArrayList<AddressTableEntity>());
		
		if (Objects.nonNull(personDTO.getId())) {
			
			person.setId(personDTO.getId());
			
		}
		
		person.setName(!ObjectUtils.isEmpty(personDTO.getName()) ? personDTO.getName() :"");
		person.setSurName(!ObjectUtils.isEmpty(personDTO.getSurName()) ? personDTO.getSurName() : "");
		
		for(AddressDTO address : personDTO.getAddresses()) {			

			person.getAddresses().add(addressDTOService.convertDtoToEntity(address));
			
		}
				
		return person;

	}

}