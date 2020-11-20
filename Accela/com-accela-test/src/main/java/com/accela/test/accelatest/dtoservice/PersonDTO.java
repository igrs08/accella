package com.accela.test.accelatest.dtoservice;

/**
 * @author Igor
 *
 */
public class PersonDTO extends BaseDTOObject {
	
	private Integer id;
	private String name;
	private String surName;
		
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
	

}
