package EmployeesOf.G.G.services;

import EmployeesOf.G.G.dto.DepartmentEmployeeCount;
import EmployeesOf.G.G.dto.EmployeesDto;
import EmployeesOf.G.G.dto.UsersDto;
import EmployeesOf.G.G.entityRepository.DepartmentEntityRepository;
import EmployeesOf.G.G.entityRepository.EmployeesEntityRepository;
import EmployeesOf.G.G.entityRepository.UserEntityRepository;
import EmployeesOf.G.G.model.Employees;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


}
