package com.arnavsaraf.testbasics.Employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


@Entity
@Data
@Table(name = "employee", uniqueConstraints= @UniqueConstraint(columnNames={"email"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Email(message = "Email must be valid")
    private String email;
    private String designation;
    private String department;
    @NotEmpty(message = "Name cannot be null")
    private String name;
    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Phone Number must be valid")
    private String phoneNumber;


}
