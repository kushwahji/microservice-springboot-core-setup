package com.hcl.demo.service;

import com.hcl.demo.entity.Customer;
import com.hcl.demo.request.CustomerRequest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
	
    void deleteCustomer(Long id);

    Customer saveOrUpdate(CustomerRequest customer);

    Optional<Customer> getCustomer(Long id);

    List<Customer> getCustomers();

	List<Customer> statusTrue();

	List<Customer> findByCustNameNotLike(String custName);

	Customer findByPhone(String phone);

	List<Customer> findAllByCreatedLessThanEqualAndUpdatedGreaterThanEqual(Date date1, Date date2);

	List<Customer> findByCustNameLike(String custName);

	List<Customer> findByCustName(String custName);

	List<Customer> statusFalse();

	List<Customer> getCustomersContains(int pageNo, int pageSize);

}
