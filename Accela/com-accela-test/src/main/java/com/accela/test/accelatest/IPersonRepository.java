package com.accela.test.accelatest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.accelatest.entity.PersonTableEntity;

public interface IPersonRepository extends JpaRepository<PersonTableEntity, Integer> {

}
