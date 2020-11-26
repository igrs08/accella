package com.accela.test.accelatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.accelatest.entity.AddressTableEntity;

public interface IAddressRepository extends JpaRepository<AddressTableEntity, Integer> {

}
