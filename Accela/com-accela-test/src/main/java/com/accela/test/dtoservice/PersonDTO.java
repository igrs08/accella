package com.accela.test.dtoservice;

import java.util.List;

/**
 * @author Igor
 *
 */
public class PersonDTO extends BaseDTOObject {
	
	private Integer id;
	private String name;
	private String surName;
	
	private List<AddressDTO> addresses;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}		

}
