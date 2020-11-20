package com.accela.test.accelatest.dtoservice;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 8342803369807054264L;

	public abstract Object getId();

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
		// if deriving: appendSuper(super.hashCode()).
				append(getId()).toHashCode();
	}

}
