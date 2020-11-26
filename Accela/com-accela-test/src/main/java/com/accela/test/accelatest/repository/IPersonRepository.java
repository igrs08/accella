package com.accela.test.accelatest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.accela.test.accelatest.entity.PersonTableEntity;

public interface IPersonRepository extends JpaRepository<PersonTableEntity, Integer> {

	@Query("SELECT p FROM PersonTableEntity p WHERE p.name LIKE :data or p.surName LIKE :data")
	List<PersonTableEntity> findPerson(@Param("data") String data);

	@Query("SELECT p FROM PersonTableEntity p LEFT JOIN FETCH p.addresses")
	List<PersonTableEntity> findAllFetchAddress();
	
	@Query("SELECT p FROM PersonTableEntity p LEFT JOIN FETCH p.addresses WHERE p.id = :id")
	Optional<PersonTableEntity> findByIdFetchAddress(@Param("id")Integer id);


}
