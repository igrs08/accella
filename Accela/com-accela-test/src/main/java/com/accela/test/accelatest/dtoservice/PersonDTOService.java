package com.accela.test.accelatest.dtoservice;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

import com.accela.test.accelatest.entity.PersonTableEntity;

/**
 * @author Igor
 *
 */
public class PersonDTOService extends BaseDTOService<PersonTableEntity, PersonDTO> {

	@Override
	public PersonDTO convertEntityToDto(PersonTableEntity person) {
		
		if (Objects.isNull(person.getId())) {
			throw new DTOException("Class does not have a valid ID");
		}

		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(person.getId());
		personDTO.setName(ObjectUtils.isEmpty(person.getName()) ? person.getName() : "");
		personDTO.setSurName(ObjectUtils.isEmpty(person.getSurName()) ? person.getSurName() : "");

		return personDTO;
	}

	@Override
	public PersonTableEntity convertDtoToEntity(PersonDTO personDTO) {
						
		PersonTableEntity person = new PersonTableEntity();
		
		if (Objects.nonNull(personDTO.getId())) {
			
			person.setId(personDTO.getId());
			
		}
		
		person.setName(ObjectUtils.isEmpty(personDTO.getName()) ? personDTO.getName() : "");
		person.setSurName(ObjectUtils.isEmpty(person.getSurName()) ? person.getSurName() : "");
		
		return person;

	}

}