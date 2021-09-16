package com.arnavsaraf.testbasics.Employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequest {

    @Email(message = "Email must be valid")
    private String email;
    private String designation;
    private String department;
    @NotEmpty(message = "Name cannot be null")
    private String name;
    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Phone Number must be valid")
    private String phoneNumber;
}
