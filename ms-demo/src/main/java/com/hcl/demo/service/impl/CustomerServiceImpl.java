package com.hcl.demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.demo.request.CustomerRequest;
import com.hcl.demo.entity.Customer;
import com.hcl.demo.repository.CustomerRepository;
import com.hcl.demo.service.CustomerService;
import com.hcl.ms.core.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Customer saveOrUpdate(CustomerRequest request) {
		if((request.getCustomer().getCustId() !=null || request.getCustomer().getCustId()>0) && customerRepository.existsById(request.getCustomer().getCustId())) {
			Customer cust = customerRepository.findById(request.getCustomer().getCustId()).get();
			if (null == cust) {
				throw new ApplicationException(HttpStatus.NOT_FOUND, "Record not found", "record not found for id :" + request.getCustomer().getCustId());
			}
			return customerRepository.save(new Customer(request,cust));
		}
		return customerRepository.save(request.getCustomer());
	}

	@Override
	public Optional<Customer> getCustomer(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> statusTrue() {
		return customerRepository.findByStatusTrue();
	}

	@Override
	public List<Customer> findByCustNameNotLike(String custName) {
		return customerRepository.findByCustNameNotLike(custName);
	}

	@Override
	public Customer findByPhone(String phone) {
		return customerRepository.findByPhone(phone);
	}

	@Override
	public List<Customer> findAllByCreatedLessThanEqualAndUpdatedGreaterThanEqual(Date date1, Date date2) {
		return customerRepository.findAllByCreatedDateLessThanEqualAndLastUpdatedGreaterThanEqual(date1, date2);
	}

	@Override
	public List<Customer> findByCustNameLike(String custName) {
		return customerRepository.findByCustNameLike(custName);
	}

	@Override
	public List<Customer> findByCustName(String custName) {
		return customerRepository.findByCustName(custName);
	}

	@Override
	public List<Customer> statusFalse() {
		return customerRepository.findByStatusFalse();
	}

	@Override
	public List<Customer> getCustomersContains(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize,
				org.springframework.data.domain.Sort.by(Direction.ASC, "CustName"));
		return customerRepository.findAll(pageable).getContent();
	}
}
