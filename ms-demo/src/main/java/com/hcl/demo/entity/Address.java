package com.hcl.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hcl.demo.request.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Entity(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String address;
    private String address2;
    private String city;
    private String district;
    private int pinCode;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_updated", nullable = false)
    @LastModifiedDate
    private Date lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    @JsonIgnore
    private Customer customer;

    @PreUpdate
    public void setUpdatedAt() {
        this.lastUpdated= new Date();
    }

    @PostUpdate
    public void setCreatedAt() {
        this.createdDate= new Date();
    }
}
