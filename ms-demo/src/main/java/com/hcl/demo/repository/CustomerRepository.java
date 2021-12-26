package com.hcl.demo.repository;

import com.hcl.demo.entity.Customer;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByCustName(String custName);

	List<Customer> findByCustNameContains(String custName);

	List<Customer> findByCustNameLike(String custName);

	List<Customer> findByCustNameNotLike(String custName);

	Customer findByPhone(String phone);

	List<Customer> findAllByCreatedDateLessThanEqualAndLastUpdatedGreaterThanEqual(Date date1,
			Date date2);

	List<Customer> findByStatusTrue();

	List<Customer> findByStatusFalse();

}
