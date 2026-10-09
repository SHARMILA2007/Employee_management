package com.example.employee_management;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    @PutMapping("/{id}")
public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
    Employee existingEmployee = employeeRepository.findById(id).orElseThrow();

    existingEmployee.setName(employee.getName());
    existingEmployee.setAge(employee.getAge());
    existingEmployee.setCity(employee.getCity());

    return employeeRepository.save(existingEmployee);
}

    @DeleteMapping("/{id}")
public String deleteEmployee(@PathVariable Long id) {
    employeeRepository.deleteById(id);
    return "Employee deleted successfully";
}



}
