package EmployeesOf.G.G.entityRepository;

import EmployeesOf.G.G.dto.DepartmentDto;
import EmployeesOf.G.G.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentEntityRepository {


    @Autowired
    private  EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;


    public List<String> getAllDepartmentWithEmployees(){

        String jpql = "SELECT d.departmentName FROM Department d ORDER BY 1";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        return query.getResultList();
    }

    public Department updateDepartment(Department department){
       return entityManager.merge(department);
    }

    public Department findById(int id){
       return entityManager.find(Department.class,1);
    }



}
