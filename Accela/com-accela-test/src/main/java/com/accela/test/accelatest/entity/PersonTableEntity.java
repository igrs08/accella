package com.accela.test.accelatest.entity;

import javax.persistence.Entity;

import com.accela.test.accelatest.dtoservice.BaseEntity;

@Entity
public class PersonTableEntity extends BaseEntity {	

	private static final long serialVersionUID = -1931357862245827659L;
	
	private Integer id;
	private String name;
	private String surName;
	
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
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

}
