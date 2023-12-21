package EmployeesOf.G.G.entityRepository;

import EmployeesOf.G.G.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserEntityRepository {

    @Autowired
    private EntityManager entityManager;


    public Users findByUserId(Integer id){
       return entityManager.find(Users.class,id);
      // return Optional.ofNullable(users);
    }

    @Transactional
    public List<Users> findAll(){
        String jpql = "SELECT u from Users u";
        TypedQuery<Users> query = entityManager.createQuery(jpql,Users.class);
        return query.getResultList();
    }

    @Transactional
    public Users update(Users users){
        return entityManager.merge(users);
    }

    @Transactional
    public Users delete(Integer id){
        return entityManager.find(Users.class,id);
    }

}
