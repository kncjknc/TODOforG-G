package EmployeesOf.G.G.services;

import EmployeesOf.G.G.dto.DepartmentDto;
import EmployeesOf.G.G.dto.DepartmentEmployeeCount;
import EmployeesOf.G.G.dto.EmployeesDto;
import EmployeesOf.G.G.dto.UsersDto;
import EmployeesOf.G.G.entityRepository.DepartmentEntityRepository;
import EmployeesOf.G.G.entityRepository.EmployeesEntityRepository;
import EmployeesOf.G.G.entityRepository.UserEntityRepository;
import EmployeesOf.G.G.exceptions.DepartmentNotFound;
import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Employees;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Autowired
    private  UserEntityRepository userEntityRepository;
    @Autowired
    private  EmployeesEntityRepository employeesEntityRepository;
    @Autowired
    private  UsersRepository usersRepository;
    @Autowired
    private  DepartmentEntityRepository departmentEntityRepository;
    @Autowired
    private  ModelMapper modelMapper;

    public UsersDto findByUserId(int id) {
        Users user = userEntityRepository.findByUserId(id);
       return modelMapper.map(user,UsersDto.class);
    }

    public String delete(int id) {
        return userEntityRepository.delete(id);
    }

    public List<UsersDto> findAll() {
        List<Users> user = userEntityRepository.findAll();
      return user.stream().map(users->modelMapper.map(user,UsersDto.class)).collect(Collectors.toList());
    }

    public UsersDto findById(int id) {
        Users user = usersRepository.findById(id).orElse(null);
       return modelMapper.map(user,UsersDto.class);

    }

    public EmployeesDto getEmployeeAllDetail(int id) {
        Employees employee = employeesEntityRepository.getEmployeeAllDetail(id);
        return modelMapper.map(employee,EmployeesDto.class);
    }

    public List<String> getGreaterSalary(Long salary) {
       return employeesEntityRepository.getGreaterSalary(salary);
    }


    public List<DepartmentEmployeeCount> getEmployeeesCount() {
       return employeesEntityRepository.getEmployeeesCount();
    }

    public List<String> getAllDepartmentAscending() {
        return departmentEntityRepository.getAllDepartmentWithEmployees();
    }


    public UsersDto updateUser(int id, UsersDto usersDto) {
        Users users = usersRepository.findById(id).orElse(null);
        if(users!=null){
            // Users user = modelMapper.map(usersDto,Users.class);
            users.setUserName(usersDto.getUserName());
            users.setPassword(usersDto.getPassword());
            users.setRoles(usersDto.getRoles());
           usersRepository.save(users);
           return usersDto;
        }
        else{
            throw new UsernameNotFoundException("The User Id"+usersDto.getId()+" not found");
        }


    }

    public DepartmentDto updateDepartment(int id, DepartmentDto departmentDto) {

        Department dep1 = departmentEntityRepository.findById(id);
        if(dep1!=null){
            dep1.setDepartmentName(departmentDto.getDepartmentName());
            dep1.setDepartmentManager(departmentDto.getDepartmentManager());
            departmentEntityRepository.updateDepartment(dep1);
            return departmentDto;
        }
        else{
            throw new DepartmentNotFound(departmentDto.getDepartmentName());
        }

    }

    public DepartmentDto getDepartmentById(Integer id) {
        Department department = departmentEntityRepository.getDepartmentById(id);
       return modelMapper.map(department,DepartmentDto.class);
    }
}
