package EmployeesOf.G.G.entityRepository;

import EmployeesOf.G.G.dto.DepartmentEmployeeCount;
import EmployeesOf.G.G.dto.EmployeesDto;
import EmployeesOf.G.G.model.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeesEntityRepository {

    @Autowired
    private  EntityManager entityManager;
    @Autowired
    private  ModelMapper modelMapper;

    @Transactional
    public List<EmployeesDto> getAllEmployees(){
        String jpql = "SELECT e FROM Employees e";
        TypedQuery<EmployeesDto> employee = entityManager.createQuery(jpql, EmployeesDto.class);
        return employee.getResultList();
    }

    @Transactional
    public Employees getEmployeeAllDetail(Integer id){
        String jpql = "SELECT e FROM Employees e JOIN FETCH e.department JOIN FETCH e.users JOIN FETCH e.address WHERE e.employeeId=:employeeId";
        TypedQuery<Employees> employee = entityManager.createQuery(jpql, Employees.class);
        employee.setParameter("employeeId",id);
        return employee.getSingleResult();
    }

    public List<DepartmentEmployeeCount> getEmployeeesCount(){
        String jpql = "SELECT d.departmentManager, d.departmentName, COUNT(e) FROM Employees e LEFT JOIN e.department d group by d.departmentManager ,d.departmentName";
        TypedQuery<DepartmentEmployeeCount> query = entityManager.createQuery(jpql,DepartmentEmployeeCount.class);
        return query.getResultList();
    }

    public List<String> getGreaterSalary(Long salary){
        String jpql = "SELECT e.employeeName FROM Employees e WHERE e.employeeSalary > :employeeSalary";
        TypedQuery<String> names = entityManager.createQuery(jpql,String.class);
        names.setParameter("employeeSalary",salary);
        return names.getResultList();
    }


}
