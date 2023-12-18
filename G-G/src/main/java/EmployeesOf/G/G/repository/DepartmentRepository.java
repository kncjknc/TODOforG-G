package EmployeesOf.G.G.repository;


import EmployeesOf.G.G.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
