package com.arnavsaraf.testbasics;

import com.arnavsaraf.testbasics.Employee.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPracticeApp {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "anna", "brad", "chris");
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "alice@example.com", "Manager", "HR", "Alice", "9123456789"),
                new Employee(2L, "bob@example.com", "Recruiter", "HR", "Bob", "9123456790"),
                new Employee(3L, "charlie@example.com", "Engineer", "Engineering", "Charlie", "9123456791"),
                new Employee(4L, "david@example.com", "Engineer", "Engineering", "David", "9123456792"),
                new Employee(5L, "eve@example.com", "Sales Rep", "Sales", "Eve", "9123456793")
        );

        List<Integer> evenNumbers = numbers.stream().filter(num -> num % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(words.stream().map(word -> word.toUpperCase())
                        .collect(Collectors.toList()));

        System.out.println(numbers.stream().filter(num -> num%2==1).map(num -> num*num).reduce(0, Integer::sum));

        System.out.println(names.stream()
                .collect(Collectors.groupingBy(
                        name -> Character.toUpperCase(name.charAt(0)) // Key: first letter uppercased
                )));

        System.out.println(employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment, // Key: department
                                Collectors.mapping(Employee::getName, Collectors.toList()) // Value: list of names
                        )));


        System.out.println(evenNumbers);
    }
}
