package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("from Role");
        List<Role> roles = query.getResultList();
        return roles;
   }

    @Override
    public Role getRoleByName(String name) {
        Role role = (Role) entityManager.createQuery("from Role where name = :name")
                .setParameter("name", name).getSingleResult();
        return role;
    }

//    @Override
//    public void createIfNotExist(Role role) {
//        try {
//            entityManager.persist(role);
//        } catch (EntityExistsException exeption) {
//            role = getRoleByName(role.getName());
//        }
//
//    }
}
