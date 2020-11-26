package com.accela.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.entity.PersonTableEntity;

public interface IPersonRepository extends JpaRepository<PersonTableEntity, Integer> {

	List<PersonTableEntity> findByNameLikeIgnoreCaseOrSurNameLikeIgnoreCase(String name, String surName);

}
