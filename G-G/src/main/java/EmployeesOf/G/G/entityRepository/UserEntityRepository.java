package EmployeesOf.G.G.entityRepository;

import EmployeesOf.G.G.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserEntityRepository {

    private final EntityManager entityManager;


    @Autowired
    public UserEntityRepository(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    public Users findByUserId(Integer id){
        String jpql = "SELECT u from Users u Join u.employees where u.id =:id";
      TypedQuery<Users> user = entityManager.createQuery(jpql, Users.class);
      user.setParameter("id",id);
     return user.getSingleResult();
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
    public String delete(Integer id){
        Users user = entityManager.find(Users.class,id);
        entityManager.remove(user);
        return "Delete "+id+" Records";
    }

}
