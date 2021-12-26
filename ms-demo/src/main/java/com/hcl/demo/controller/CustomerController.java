package com.hcl.demo.controller;

import com.hcl.demo.entity.Customer;
import com.hcl.demo.request.CustomerRequest;
import com.hcl.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Santosh Kushwah
 */
@ApiResponse(description = "Demo Controller for fetching all type records, store, update & delete data")
@RestController
@RequestMapping(value = "/api")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody CustomerRequest customer){
        return customerService.saveOrUpdate(customer);
    }
    
    @Operation(description = "Get list of customer in the db ", tags = "getCustomers")
    @ApiResponses(value = { 
                @ApiResponse(responseCode = "200", description = "Success|OK"),
                @ApiResponse(responseCode = "401", description = "not authorized!"), 
                @ApiResponse(responseCode = "403", description = "forbidden!!!"),
                @ApiResponse(responseCode = "404", description = "not found!!!") })
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    
    @GetMapping("/customers/{pageNo},{pageSize}")
    public List<Customer> getCustomersContains(@PathVariable int pageNo, @PathVariable int pageSize){
        return customerService.getCustomersContains(pageNo,pageSize);
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable Long id){
         customerService.deleteCustomer(id);
         return "success";
    }
    
    @GetMapping("/status-true")
    public List<Customer> statusTrue(){
        return customerService.statusTrue();
    }
    
    @GetMapping("/status-false")
    public List<Customer> statusFalse(){
        return customerService.statusFalse();
    }
    
    @GetMapping("/search-by-name/{custName}")
    public List<Customer> findByCustName(@PathVariable String custName){
        return customerService.findByCustName(custName);
    }
    
    @GetMapping("/search-by-like/{custName}")
    public List<Customer> findByCustNameLike(@PathVariable String custName){
        return customerService.findByCustNameLike(custName);
    }
    
    @GetMapping("/search-by-NotLike/{custName}")
    public List<Customer> findByCustNameNotLike(@PathVariable String custName){
        return customerService.findByCustNameNotLike(custName);
    }

    @GetMapping("/search-by-phone/{phone}")
    public Customer findByPhone(@PathVariable String phone){
        return customerService.findByPhone(phone);
    }
    
    @GetMapping("/search/{date1},{date2}")
    public List<Customer> findAllByCreatedLessThanEqualAndUpdatedGreaterThanEqual(@PathVariable Date date1,@PathVariable Date date2){
        return customerService.findAllByCreatedLessThanEqualAndUpdatedGreaterThanEqual(date1,date2);
    }
    
    
}
