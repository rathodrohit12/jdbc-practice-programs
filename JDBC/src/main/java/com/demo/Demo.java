package com.demo;

import com.demo.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Demo {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "mailto:alice@example.com", "1234567890", 60000, "New York"),
                new Employee(2, "Bob", "mailto:bob@example.com", "9876543210", 55000, "Los Angeles"),
                new Employee(3, "Charlie", "mailto:charlie@example.com", "5678901234", 70000, "Chicago"),
                new Employee(4, "David", "mailto:david@example.com", "6789012345", 50000, "Houston"),
                new Employee(5, "Emma", "mailto:emma@example.com", "7890123456", 75000, "San Francisco")
        );


//        employees.stream().filter(e->e.getAddress().startsWith("N")).forEach(System.out::println);
//        List<Employee> collect = employees.stream().filter(e->e.getAddress().startsWith("N")).collect(Collectors.toList());
//





        employees.stream().mapToDouble(t ->t.getSalary()).sequential().;





    }
}