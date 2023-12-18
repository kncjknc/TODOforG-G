package EmployeesOf.G.G.repository;

import EmployeesOf.G.G.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
}
