package EmployeesOf.G.G.services;

import EmployeesOf.G.G.dto.DepartmentDto;
import EmployeesOf.G.G.dto.DepartmentEmployeeCount;
import EmployeesOf.G.G.dto.EmployeesDto;
import EmployeesOf.G.G.dto.UsersDto;
import EmployeesOf.G.G.entityRepository.DepartmentEntityRepository;
import EmployeesOf.G.G.entityRepository.EmployeesEntityRepository;
import EmployeesOf.G.G.entityRepository.UserEntityRepository;
import EmployeesOf.G.G.exceptions.DepartmentNotFound;
import EmployeesOf.G.G.model.CustomRevisionEntity;
import EmployeesOf.G.G.model.Department;
import EmployeesOf.G.G.model.Employees;
import EmployeesOf.G.G.model.Users;
import EmployeesOf.G.G.repository.UsersRepository;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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


    public UsersDto updateUser(UsersDto usersDto) {
       Users users = modelMapper.map(usersDto, Users.class);
        Users user = userEntityRepository.update(users);
       return modelMapper.map(user,UsersDto.class);
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


    public List getHistory(int id){
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Users.class,true,true)
                .add(AuditEntity.property("id").eq(id));
        return auditQuery.getResultList();
    }
}
