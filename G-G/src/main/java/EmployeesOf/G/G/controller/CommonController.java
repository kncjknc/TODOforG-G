package EmployeesOf.G.G.controller;

import EmployeesOf.G.G.dto.AuthTokenclass;
import EmployeesOf.G.G.dto.Token;
import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Employees;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.DepartmentRepository;
import EmployeesOf.G.G.repository.EmployeesRepository;
import EmployeesOf.G.G.repository.UsersRepository;
import EmployeesOf.G.G.services.JwtService;
import EmployeesOf.G.G.services.UserInfoService;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/addEmployee")
    public Employees addEmployee(@RequestBody Employees employees){
        return employeesRepository.save(employees);
    }

    @RolesAllowed({"ROLE_HR,ROLE_ADMIN"})
    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody Department department){
       return departmentRepository.save(department);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody Users users){
      return  userInfoService.addUsers(users);
    }

    @PostMapping("/loginUsers")
    public Token authenticationTicket(@RequestBody AuthTokenclass authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassWord()));
            Users user = usersRepository.findByName(authRequest.getUserName());
            if (authentication.isAuthenticated()) {
                logger.info("inside If");
                String token = jwtService.generateToken(user.getId(), authRequest.getUserName());
                return new Token(token);
            } else {
                throw new UsernameNotFoundException("Invalid User !");
            }
        }
        catch(Exception e){
            throw new RuntimeException("Runtime Exception "+e);
        }

    }

}
