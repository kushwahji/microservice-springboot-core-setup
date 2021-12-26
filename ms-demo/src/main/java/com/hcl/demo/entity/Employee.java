package com.hcl.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = -1798070786993154676L;
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer           employeeId;
    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String            firstName;
    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String            lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Account  account;
}
