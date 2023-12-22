package EmployeesOf.G.G.controller;

import EmployeesOf.G.G.dto.*;
import EmployeesOf.G.G.entityRepository.EmployeesEntityRepository;
import EmployeesOf.G.G.entityRepository.UserEntityRepository;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import EmployeesOf.G.G.services.CommonService;
import EmployeesOf.G.G.services.JwtService;
import EmployeesOf.G.G.services.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CommonController {

    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private EmployeesEntityRepository employeesEntityRepository;
    Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private  UserInfoService userInfoService;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private  CommonService commonService;
    @Autowired
    private  UsersRepository usersRepository;

    @GetMapping("/user/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable int id){
       UsersDto user = commonService.findByUserId(id);
       return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@PathVariable int id){
        return commonService.delete(id);
    }

    @GetMapping("/getUser/{id}")
    public UsersDto getUserID(@PathVariable int id){
        logger.info("getUser");
        return commonService.findById(id);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody Users users){
        return  userInfoService.addUsers(users);
    }

    @GetMapping("/getAll")
    public List<UsersDto> getAllUsers(){
       return commonService.findAll();
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeesDto> getEmployeeByIDAllDetails(@PathVariable int id){
        logger.info("employees method");
        EmployeesDto employeesDto= commonService.getEmployeeAllDetail(id);
        return new ResponseEntity<>(employeesDto, HttpStatus.OK);
    }

    @GetMapping("/getEmployeesCount")
    public List<DepartmentEmployeeCount> getEmployeesCount(){
        return commonService.getEmployeeesCount();
    }

    @GetMapping("/getGreaterSalary/{salary}")
    public List<String> getGreaterSalary(@PathVariable Long salary){
       return commonService.getGreaterSalary(salary);
    }

    @GetMapping("/getAllDepartmentAces")
    public List<String> getAllDepartmentAscending(){
       return commonService.getAllDepartmentAscending();
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
