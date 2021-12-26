package com.hcl.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.hcl.demo.request.CustomerRequest;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity(name = "customer")
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;

    @NotBlank
    private String custName;

    @Email
    private String email;

    @NotBlank
    private String phone;

    private String age;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_updated", nullable = false)
    @LastModifiedDate
    private Date lastUpdated;

    private boolean status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
    private List<Address> address;


    public Customer(CustomerRequest request, Customer cust) {
    	Optional.ofNullable(cust);
        Optional.ofNullable(request.getCustomer());
        custId = request.getCustomer().getCustId();
        this.custName = Optional.ofNullable(request.getCustomer().getCustName()).orElse(cust.getCustName());
        this.email = Optional.ofNullable(request.getCustomer().getEmail()).orElse(cust.getEmail());
        this.phone = Optional.ofNullable(request.getCustomer().getPhone()).orElse(cust.getPhone());
        this.age = Optional.ofNullable(request.getCustomer().getAge()).orElse(cust.getAge());
        this.createdDate = Optional.ofNullable(request.getCustomer().getCreatedDate()).orElse(cust.getCreatedDate());
        this.lastUpdated = Optional.ofNullable(request.getCustomer().getLastUpdated()).orElse(cust.getLastUpdated());
        this.status = Optional.ofNullable(request.getCustomer().isStatus()).orElse(cust.isStatus());
		this.address= Optional.ofNullable(request.getCustomer().getAddress()).orElse(cust.getAddress());
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.lastUpdated = new Date();
    }

    @PostUpdate
    public void setCreatedAt() {
       // this.createdDate = new Date();
    }
}
