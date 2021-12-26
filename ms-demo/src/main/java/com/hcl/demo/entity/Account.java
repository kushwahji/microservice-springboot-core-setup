package com.hcl.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity(name = "account")
public class Account implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @Column(name = "ID", unique = true, nullable = false)
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Integer           accountId;

        @Column(name = "ACC_NO", unique = false, nullable = false, length = 100)
        private String            accountNumber;

        @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
        Employee  employee;
}
