package EmployeesOf.G.G.controller;

import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Employees;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.DepartmentRepository;
import EmployeesOf.G.G.repository.EmployeesRepository;
import EmployeesOf.G.G.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/addEmployee")
    public Employees addEmployee(@RequestBody Employees employees){
        return employeesRepository.save(employees);
    }

    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody Department department){
       return departmentRepository.save(department);
    }

    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users users){
      return  usersRepository.save(users);
    }

    
}
