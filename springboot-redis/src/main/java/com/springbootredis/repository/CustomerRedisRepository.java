package com.springbootredis.repository;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootredis.dto.Customer;

@Repository
public interface CustomerRedisRepository extends CrudRepository<Customer, Long> {

}
