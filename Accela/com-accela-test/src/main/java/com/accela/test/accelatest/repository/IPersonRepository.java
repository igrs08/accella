package com.accela.test.accelatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accela.test.accelatest.entity.PersonTableEntity;

public interface IPersonRepository extends JpaRepository<PersonTableEntity, Integer> {

	@Query("SELECT p FROM PersonTableEntity p WHERE p.name LIKE :data or p.surName LIKE :data")
	List<PersonTableEntity> findPerson(@Param("data") String data);

}
