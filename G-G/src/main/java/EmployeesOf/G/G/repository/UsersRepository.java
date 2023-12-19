package EmployeesOf.G.G.repository;

import EmployeesOf.G.G.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUserName(String username);

    @Query(value = "SELECT * FROM employeesofgandg.users_table u WHERE u.user_name = :name", nativeQuery = true)
    Users findByName(@Param("name") String name);

}
