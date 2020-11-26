package com.accela.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accela.test.entity.AddressTableEntity;

public interface IAddressRepository extends JpaRepository<AddressTableEntity, Integer> {

}
